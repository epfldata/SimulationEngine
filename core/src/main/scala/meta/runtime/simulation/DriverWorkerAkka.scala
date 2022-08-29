package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Await
import scala.concurrent.duration._

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}

import java.util.Collections
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._

import akka.NotUsed
import akka.actor.typed.{ActorRef, ActorSystem, Terminated, PostStop, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout

import akka.cluster.typed.Cluster
import com.typesafe.config.ConfigFactory
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}
import com.fasterxml.jackson.databind.annotation.JsonDeserialize


object Driver {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeAgentMap], name = "initializeAgentMap")))
    sealed trait DriverEvent
    final case class InitializeAgentMap(workerId: Int, agentIds: Seq[Long]) extends DriverEvent with JsonSerializable
    final case class InitializeWorkers() extends DriverEvent with NoSerializationVerificationNeeded
    final case object RoundStart extends DriverEvent with NoSerializationVerificationNeeded
    final case class RoundEnd(elapsedTime: Int) extends DriverEvent with NoSerializationVerificationNeeded

    val serviceKeyInitAgentMap = ServiceKey[InitializeAgentMap]("InitializeAgentMap")
}

class Driver {
    import Driver._

    private var totalWorkers: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0
    // worker x, a list of workers which x expects messages from
    private var workerReceiveFrom: Map[Int, Set[Int]] = Map[Int, Set[Int]]()
    private var workerIdMap: ConcurrentHashMap[Int, ActorRef[Worker.ExpectedReceives]] = new ConcurrentHashMap[Int, ActorRef[Worker.ExpectedReceives]]()
    private var registeredWorkers: AtomicInteger = new AtomicInteger(0)
    private var workersStop: Set[ActorRef[Worker.Stop]] = Set()
    private var workersStart: Set[ActorRef[Worker.ExpectedReceives]] = Set()
    private val agentNameMap: AgentNameMap = new AgentNameMap()

    def apply(workers: Int, maxTurn: Int, messages: List[Message]): Behavior[DriverEvent] = Behaviors.setup {ctx =>
        ctx.system.receptionist ! Receptionist.Register(serviceKeyInitAgentMap, ctx.self)

        totalWorkers = workers
        totalTurn = maxTurn
        currentTurn = 0 

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case Worker.WorkerStopServiceKey.Listing(workers) =>
                ctx.log.debug(f"Worker stop keys registered! Total ${workers.size}")

                if (workers.size == totalWorkers) {
                    ctx.log.debug(f"Recorded all workers that need to be stopped!")
                    workersStop = workers
                } 
                InitializeWorkers()

            case Worker.WorkerStartServiceKey.Listing(workers) =>
                ctx.log.debug(f"Worker start keys registered! Total ${workers.size}")

                if (workers.size == totalWorkers) {
                    ctx.log.debug(f"Recorded all workers that need to be stopped!")
                    workersStart = workers
                } 
                InitializeWorkers()
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(Worker.WorkerStartServiceKey, workerSub)
        ctx.system.receptionist ! Receptionist.Subscribe(Worker.WorkerStopServiceKey, workerSub)
        driver()
    }

    def driver(): Behavior[DriverEvent] = 
        Behaviors.receive[DriverEvent] { (ctx, message) => 
            message match { 
                case InitializeWorkers() =>
                    if (!workersStart.isEmpty && !workersStop.isEmpty){
                        ctx.log.debug("All workers are initialized! Start round.")
                        ctx.self ! RoundStart
                    } 
                    Behaviors.same

                case InitializeAgentMap(workerId, agentIds) =>
                    ctx.log.debug(f"Add to agent name map for worker ${workerId}")
                    agentNameMap.update(workerId, agentIds)
                    val currentRegistered = registeredWorkers.addAndGet(1)
                    if (currentRegistered == totalWorkers) {
                        ctx.self ! RoundStart
                    }
                    driver()

                case RoundStart => {
                    ctx.log.warn(f"Round ${currentTurn}")
                    ctx.log.debug(f"Driver sends expected receives to all workers!  ${workerReceiveFrom}")
                    ctx.spawnAnonymous(
                        Aggregator[Worker.SendTo, RoundEnd](
                            sendRequests = { replyTo =>
                                workersStart.map(a => {
                                    a ! Worker.ExpectedReceives(workerReceiveFrom, replyTo)
                                })
                                workerReceiveFrom = Map()
                            },
                            expectedReplies = totalWorkers,
                            ctx.self,
                            aggregateReplies = replies => {
                                var passedRounds: Int = 1
                                for (r <- replies) {
                                    for (k <- r.sendTo) {
                                        workerReceiveFrom = workerReceiveFrom.updated(k, workerReceiveFrom.getOrDefault(k, Set()).union(Set(r.workerId)))
                                    }
                                    if (r.elapsedTime > passedRounds){
                                        passedRounds = r.elapsedTime
                                    }
                                }
                                RoundEnd(passedRounds)
                            },
                            timeout=100.seconds))
                    ctx.log.debug(f"Driver receives notifications from all workers! New receive from ${workerReceiveFrom}")
                    driver()
                }

                case RoundEnd(elapsedTime: Int) =>
                    if (currentTurn + elapsedTime >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.debug(f"Simulation completes! Stop the driver")
                            workersStop.foreach(a => a ! Worker.Stop())
                        }
                    } else {
                        currentTurn += elapsedTime
                        ctx.self ! RoundStart
                        driver()
                    }
            }
        }
}

// Each worker and driver maintains a copy of the agent name map
class AgentNameMap {
    private val agentNameMap: ConcurrentHashMap[Int, ListBuffer[Long]] = new ConcurrentHashMap()

    def update(workerId: Int, agentIds: Seq[Long]): Unit = synchronized {
        val x: ListBuffer[Long] = new ListBuffer[Long]()
        x.addAll(agentIds)
        agentNameMap.update(workerId, x)
    }

    // Replace with better indexing, hash-based lookup
    def getWorker(agentId: Long): Int = synchronized {
        var ans: Int = -1
        for (i <- agentNameMap) {
            if (i._2.contains(agentId)) {
                if (ans != -1){
                    throw new Exception(f"Agent ${agentId} found in both workers ${i._1} and ${ans}!")
                }
                ans = i._1
            } 
        }
        if (ans == -1){
            throw new Exception(f"Agent ${agentId} not found in the name map!")
        }
        ans
    }

    def isRegistered(workerId: Int): Boolean = {
        agentNameMap.contains(workerId)
    }

    def migrate(oldWorker: Int, newWorker: Int, agentId: Long): Unit = synchronized {
        agentNameMap(oldWorker) -= agentId
        agentNameMap(newWorker) += agentId
    }

    def clear(): Unit = synchronized {
        agentNameMap.clear()
    }
}

object Worker {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[SendTo], name = "SendTo")))
    sealed trait WorkerEvent
    final case class Prepare() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class RegisterAgentMap(driver: ActorRef[Driver.InitializeAgentMap]) extends WorkerEvent with NoSerializationVerificationNeeded
    final case class AgentsCompleted(indexedMessages: Map[Int, MutMap[Long, List[Message]]]) extends WorkerEvent with NoSerializationVerificationNeeded
    final case class Stop() extends WorkerEvent with JsonSerializable
    final case class ReceiveMessages(workerId: Int, messages: Map[Long, List[Message]]) extends WorkerEvent with JsonSerializable
    final case class ReceiveAgentMap(workerId: Int, agentIds: Seq[Long], replyTo: ActorRef[ReceiveMessages]) extends WorkerEvent with JsonSerializable
    final case class SendTo(workerId: Int, sendTo: Set[Int], elapsedTime: Int) extends WorkerEvent with JsonSerializable
    final case class ExpectedReceives(ids: Map[Int, Set[Int]], sendTo: ActorRef[SendTo]) extends WorkerEvent with JsonSerializable

    val WorkerStartServiceKey = ServiceKey[ExpectedReceives]("WorkerStart")
    val WorkerStopServiceKey = ServiceKey[Stop]("WorkerStop")
    val WorkerUpdateAgentMapServiceKey = ServiceKey[ReceiveAgentMap]("Update peer workers")
}

class Worker {
    import Worker._
    private var sims: Seq[Actor] = Seq[Actor]()
    private var simIds: Seq[Long] = Seq[Long]()
    private var workerId: Int = 0
    private var totalWorkers: Int = 0
    private val nameMap: AgentNameMap = new AgentNameMap()
    private var local_agents: Seq[(Long, ActorRef[LocalAgent.AgentEvent])] = Seq[(Long, ActorRef[LocalAgent.AgentEvent])]()
    private val peer_workers: ConcurrentHashMap[Int, ActorRef[Worker.ReceiveMessages]] = new ConcurrentHashMap[Int, ActorRef[Worker.ReceiveMessages]]() 
    private val collectedMessages: MutMap[Long, List[Message]] = MutMap[Long, List[Message]]()
    private val receivedMessages: ConcurrentHashMap[Long, List[Message]] = new ConcurrentHashMap[Long, List[Message]]()
    private val receivedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()

    // private var tscontroller: ActorRef[TimeseriesController.LogWorker] = null

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int): Behavior[WorkerEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
        ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(WorkerUpdateAgentMapServiceKey, ctx.self)

        // obtain the rest of worker references to support direct messaging
        this.sims = sims
        this.simIds = sims.map(_.id)
        this.totalWorkers = totalWorkers
        workerId = id
        ctx.log.warn(f"Worker ${workerId} has agents ${simIds}")

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case Worker.WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, simIds, ctx.self)
                    })
                }
                Prepare()

            case Driver.serviceKeyInitAgentMap.Listing(drivers) =>
                if (drivers.size > 0){
                    RegisterAgentMap(drivers.head)
                }
                Prepare()

            // case TimeseriesController.tsControllerServiceKey.Listing(tscontrollers) =>
            //     if (drivers.size > 0){
            //         tscontroller = tscontrollers.head
            //     }
            //     Prepare()
        } 

        // Creating an actor for each Sim
        local_agents = sims.map(a => (a.id, ctx.spawn((new LocalAgent).apply(a), f"simAgent${a.id}")))
        ctx.system.receptionist ! Receptionist.Subscribe(Driver.serviceKeyInitAgentMap, workerSub)
        ctx.system.receptionist ! Receptionist.Subscribe(WorkerUpdateAgentMapServiceKey, workerSub)
        // ctx.system.receptionist ! Receptionist.Subscribe(TimeseriesController.tsControllerServiceKey, workerSub)
        nameMap.update(workerId, simIds)
        worker()
    }

    // Consider replacing receivedWorkers with a total workers
    private def worker(): Behavior[WorkerEvent] =
        Behaviors.receive[WorkerEvent] { (ctx, message) =>
            message match {
                case Prepare() => 
                    worker()
                case RegisterAgentMap(driver) => 
                    driver ! Driver.InitializeAgentMap(workerId, simIds)
                    Behaviors.same
                case ReceiveAgentMap(wid, nameIds, reply) => 
                    if (!nameMap.isRegistered(wid)){
                        nameMap.update(wid, nameIds)
                        peer_workers.update(wid, reply)
                    }
                    Behaviors.same
                case ReceiveMessages(wid, messages) =>
                    if (!receivedWorkers.contains(wid)){
                        receivedWorkers.add(wid)
                    }
                    ctx.log.debug(f"Worker ${workerId} receives messages from worker ${wid} ${messages}")
                    for (m <- messages) {
                        receivedMessages.update(m._1, receivedMessages.getOrElse(m._1, List()) ::: m._2)
                    }
                    worker()
                case ExpectedReceives(receive_map, replyTo) => 
                    ctx.log.warn(f"Worker ${workerId} expects to receive from ${receive_map}; Received workers ${receivedWorkers}")
                    // If worker has received messages from all above workers, then resume agents
                    if (receive_map == null || receive_map.get(workerId).isEmpty || receivedWorkers.toSet.diff(receive_map(workerId)).isEmpty){
                        ctx.log.warn(f"Worker ${workerId} starts!")
                        receivedWorkers.clear()
                        if (local_agents.size > 0){
                            ctx.spawnAnonymous(
                                Aggregator[LocalAgent.MessagesAdded, AgentsCompleted](
                                sendRequests = { replyTo =>
                                    local_agents.map(a => {
                                        if (receivedMessages.get(a._1) == null){
                                            a._2 ! LocalAgent.AddMessages(collectedMessages.remove(a._1).getOrElse(List()), replyTo)  
                                        } else {
                                            a._2 ! LocalAgent.AddMessages(receivedMessages.remove(a._1) ::: collectedMessages.remove(a._1).getOrElse(List()), replyTo)
                                        }                                    
                                    })
                                },
                                expectedReplies = local_agents.size,
                                ctx.self,
                                aggregateReplies = replies => {
                                    var passedRounds: Int = 1
                                    for (r <- replies) {
                                        r.messages.groupBy(m => m.receiverId).foreach(i => {
                                            collectedMessages.update(i._1, collectedMessages.getOrElse(i._1, List()) ::: i._2) 
                                        })
                                        
                                        if (r.elapsedTime > passedRounds){
                                            passedRounds = r.elapsedTime
                                        }
                                    }

                                    val ans = collectedMessages.groupBy(i => nameMap.getWorker(i._1)).filterNot(i => i._1 == workerId)
                                    println("Aggregator completed!")
                                    println(f"Worker ${workerId} sends to driver whom it contacted ${ans.keys.toSet}")
                                    replyTo ! SendTo(workerId, ans.keys.toSet, passedRounds)
                                    AgentsCompleted(ans)
                                },
                                timeout=100.seconds))
                        } else {
                            // If the worker has no agent, then it notifies the driver that it is ready
                            replyTo ! SendTo(workerId, Set(), 1)
                            AgentsCompleted(Map())
                        }
                        ctx.log.debug(f"Worker ${workerId} completes!")
                    } 
                    worker()
                    
                case AgentsCompleted(indexedMessages) =>
                    ctx.log.debug(f"Local agents in worker ${workerId} have completed!")
                    indexedMessages.foreach(i => {
                        ctx.log.debug(f"Worker ${workerId} sends to peer worker ${i._1}")
                        peer_workers.get(i._1) ! ReceiveMessages(workerId, i._2.toMap)
                        collectedMessages --= i._2.map(_._1)
                    })
                    Behaviors.same
                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    local_agents.foreach(a => ctx.stop(a._2))
                    Behaviors.stopped
            }
        }
}

/**
  * Local agents communicate only with the workers.
  */
object LocalAgent {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "addToMessageMap"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "messagesAdded")
    ))
    trait AgentEvent 
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent with JsonSerializable
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int) extends AgentEvent with JsonSerializable
}

class LocalAgent {
    import LocalAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = Behaviors.setup { ctx =>
        this.sim = sim
        simAgent()
    }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case AddMessages(messages, replyTo) => 
                    ctx.log.debug(f"Agent ${sim.id} receives ${messages.size} messages")
                    val agentAPI = sim.run(messages)
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    replyTo ! MessagesAdded(sentMessages, elapsedTime)
                    Behaviors.same
            }
        }.receiveSignal {
            case (context, PostStop) =>
                context.log.debug(f"Agent ${sim.id} stopped")
                Behaviors.stopped
        }
}

// object TimeseriesController {
//     @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
//     @JsonSubTypes(
//     Array(
//         new JsonSubTypes.Type(value = classOf[Start], name = "startTimeseriesController"),
//         new JsonSubTypes.Type(value = classOf[AgentsAdded], name = "agentsAdded")
//     ))
//     trait TimeseriesControllerEvent 
//     final case class Start() extends TimeseriesControllerEvent with JsonSerializable
//     final case class LogWorker(agents: List[Actor], elapsedTime: Int) extends AgentEvent with JsonSerializable

//     val tsControllerServiceKey = ServiceKey[Start]("Start the timeseries controller")
// }

// class TimeseriesController {
//     import TimeseriesControllerEvent._

//     private var sim: Actor = null

//     def apply(sim: Actor): Behavior[TimeseriesControllerEvent] = Behaviors.setup { ctx =>
//         ctx.system.receptionist ! Receptionist.Register(Start, ctx.self)
//         timeseriesController()
//     }

//     private def timeseriesController(): Behavior[TimeseriesControllerEvent] =
//         Behaviors.receive[TimeseriesControllerEvent] { (ctx, message) =>
//             message match {
//                 case LogWorker(data, elapsedTime) => 
//                     ctx.log.debug(f"Agent ${sim.id} receives ${messages.size} messages")
                    
//                     Behaviors.same
//             }
//         }.receiveSignal {
//             case (context, PostStop) =>
//                 context.log.debug(f"Agent ${sim.id} stopped")
//                 Behaviors.stopped
//         }
// }

object DriverWorkerExp {
    sealed trait Command
    final case class SpawnDriver(totalWorkers: Int, totalTurn: Int, messages: List[Message]) extends Command
    final case class SpawnWorker(workerId: Int, sims: Seq[Actor], totalWorkers: Int) extends Command
    final case class DriverStopped() extends Command
    final case class WorkerStopped(workerId: Int, sims: Seq[Actor]) extends Command

    var cluster: Cluster = null
    var totalWorkers: Int = 0
    val stoppedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var activeWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var finalAgents: ConcurrentLinkedQueue[Actor] = new ConcurrentLinkedQueue[Actor]()

    def apply(totalTurn: Int, totalWorkers: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            this.totalWorkers = totalWorkers
            val roles = cluster.selfMember.getRoles
            val totalActors = actors.size
            var actorsPerWorker = totalActors/totalWorkers
            if (totalActors % totalWorkers > 0){
                actorsPerWorker += 1
            }
            stoppedWorkers.clear()
            activeWorkers.clear()
            finalAgents.clear()
            
            ctx.log.debug(f"${actorsPerWorker} actors per worker")

            if (roles.exists(p => p.startsWith("Worker"))) {
                ctx.log.warn(f"Creating a worker!")
                val worker_id = roles.head.split("-").last.toInt
                val containedAgents = actors.slice(worker_id*actorsPerWorker, List((worker_id+1)*actorsPerWorker, totalActors).min)        
                // if (containedAgents.size > 0){
                    ctx.self ! SpawnWorker(worker_id, containedAgents, totalWorkers)
                // }
            } 
            
            if (cluster.selfMember.hasRole("Driver")) {
                ctx.log.warn(f"Creating a driver!")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn, messages)
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.log.warn(f"Standalone mode")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn, messages)
                for (i <- Range(0, totalWorkers)){
                    val containedAgents = actors.slice(i*actorsPerWorker, List((i+1)*actorsPerWorker, totalActors).min)        
                    // if (containedAgents.size > 0){
                        ctx.self ! SpawnWorker(i, containedAgents, totalWorkers)
                    // }
                }
            }
            waitTillFinish(Vector.empty)
        }

    def waitTillFinish(finalAgents: IndexedSeq[Actor]): Behavior[Command] = {
        Behaviors.receive { (ctx, message) => 
            message match {
                case SpawnDriver(totalWorkers, totalTurn, messages) => 
                    val driver = ctx.spawn((new Driver).apply(totalWorkers, totalTurn, messages), "driver")
                    ctx.watchWith(driver, DriverStopped())
                    Behaviors.same

                case SpawnWorker(workerId, agents, totalWorkers) =>
                    val sim = ctx.spawn((new Worker).apply(workerId, agents, totalWorkers), f"worker${workerId}")
                    activeWorkers.add(workerId)
                    ctx.watchWith(sim, WorkerStopped(workerId, agents))
                    Behaviors.same
                
                case DriverStopped() =>
                    if (cluster.selfMember.hasRole("Standalone")) {
                        Behaviors.same
                    } else {
                        Behaviors.stopped {() =>
                            ctx.system.terminate()
                        }
                    }

                case WorkerStopped(workerId, agents) =>
                    if (cluster.selfMember.hasRole("Standalone")) {
                        ctx.log.warn(f"Worker stop signal received! ${workerId} Stopped workers: ${stoppedWorkers}")
                        if (!stoppedWorkers.contains(workerId)){
                            stoppedWorkers.add(workerId)
                            if (activeWorkers.toSet.diff(stoppedWorkers.toSet).isEmpty){
                                DriverWorkerRun.addStoppedAgents(finalAgents ++ agents)
                                Behaviors.stopped {() =>
                                    ctx.system.terminate()
                                }
                            } else {
                                waitTillFinish(finalAgents ++ agents)
                            }
                        } else {
                            if (activeWorkers.toSet.diff(stoppedWorkers.toSet).isEmpty){
                                Behaviors.stopped {() =>
                                    ctx.system.terminate()
                                }
                            } else {
                                waitTillFinish(finalAgents)
                            }
                        }
                    } else {
                        Behaviors.stopped {() =>
                            ctx.system.terminate()
                        }
                    }                   
            }

        }
    }
}

object DriverWorkerRun {

    private var stoppedAgents = List[Actor]()

    var lastWords: List[Message] = List[Message]()

    def addStoppedAgents(agents: IndexedSeq[Actor]): Unit = {
        stoppedAgents = agents.toList 
    }

    def initialize(): Unit = {
        stoppedAgents=List()
        lastWords=List()
    }

    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message], 
            role: String= "Standalone", port: Int = 25251): SimulationSnapshot = {
        def startup(role: String, port: Int): Unit ={
            val config = ConfigFactory.parseString(s"""
                akka.remote.artery.canonical.port=$port
                akka.cluster.roles = [$role]
                """).withFallback(ConfigFactory.load("application"))
            // If there are more workers than agents, then set the worker number to the same as agents
            var total_workers: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.total-workers").render().toInt
            println(f"Total workers are ${total_workers} Total actors ${actors.size}")
            if (total_workers > actors.size){
                println(f"Detect more workers than agents! Set total workers from ${total_workers} to ${actors.size}")
                total_workers = actors.size
            }
            val actorSystem = ActorSystem(DriverWorkerExp(totalTurn, total_workers, actors, staged, messages), "SimsCluster", config)
            Await.ready(actorSystem.whenTerminated, 10.days)
        }
        initialize()    
        println(f"Simulation starts in ${role} mode!")
        startup(role, port)
        println("Simulation ends!")
        // Actor.reset 
        SimulationSnapshot(stoppedAgents, lastWords)
    }
}