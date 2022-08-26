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
import org.apache.spark.deploy.worker.ui.WorkerPage

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
    private var workerReceiveFrom: ConcurrentHashMap[Int, List[Int]] = new ConcurrentHashMap()
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
                    ctx.spawnAnonymous(
                        Aggregator[Worker.SendTo, RoundEnd](
                            sendRequests = { replyTo =>
                                workersStart.map(a => {
                                    a ! Worker.ExpectedReceives(workerReceiveFrom.remove(a), replyTo)
                                })
                            },
                            expectedReplies = totalWorkers,
                            ctx.self,
                            aggregateReplies = replies => {
                                var passedRounds: Int = 1
                                for (r <- replies) {
                                    for (k <- r.sendTo) {
                                        workerReceiveFrom.update(k, r.workerId :: workerReceiveFrom.getOrDefault(k, List()))
                                    }
                                    if (r.elapsedTime > passedRounds){
                                        passedRounds = r.elapsedTime
                                    }
                                }
                                RoundEnd(passedRounds)
                            },
                            timeout=100.seconds))
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
        var ans: Int = 0
        for (i <- agentNameMap) {
            if (i._2.contains(agentId)) {
                if (ans != 0){
                    throw new Exception(f"Agent ${agentId} found in both workers ${i._1} and ${ans}!")
                }
                ans = i._1
            }
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
    final case class RegisterAgentMap(driver: ActorRef[Driver.InitializeAgentMap]) extends WorkerEvent with JsonSerializable
    final case class AgentsCompleted(indexedMessages: Map[Int, MutMap[Long, List[Message]]]) extends WorkerEvent with NoSerializationVerificationNeeded
    final case class Stop() extends WorkerEvent with JsonSerializable
    final case class ReceiveMessages(workerId: Int, messages: Map[Long, List[Message]]) extends WorkerEvent with JsonSerializable
    final case class ReceiveAgentMap(workerId: Int, agentIds: Seq[Long]) extends WorkerEvent with JsonSerializable
    final case class SendTo(workerId: Int, sendTo: Seq[Int], elapsedTime: Int) extends WorkerEvent with JsonSerializable
    final case class ExpectedReceives(ids: List[Int], sendTo: ActorRef[SendTo]) extends WorkerEvent with JsonSerializable

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
    private var peer_workers: Seq[(Int, ActorRef[Worker.WorkerEvent])] = Seq[(Int, ActorRef[Worker.WorkerEvent])]()
    private val collectedMessages: MutMap[Long, List[Message]] = MutMap[Long, List[Message]]()

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

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case Worker.WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, simIds)
                    })
                }
                Prepare()

            case Driver.serviceKeyInitAgentMap.Listing(drivers) =>
                if (drivers.size > 0){
                    RegisterAgentMap(drivers.head)
                }
                Prepare()
        } 

        // Creating an actor for each Sim
        local_agents = sims.map(a => (a.id, ctx.spawn((new LocalAgent).apply(a), f"simAgent${a.id}")))
        ctx.system.receptionist ! Receptionist.Subscribe(Driver.serviceKeyInitAgentMap, workerSub)
        ctx.system.receptionist ! Receptionist.Subscribe(WorkerUpdateAgentMapServiceKey, workerSub)
        nameMap.update(workerId, simIds)
        worker(List(), Map())
    }

    // Consider replacing receivedWorkers with a total workers
    private def worker(receivedWorkers: List[Int], receivedMessages: Map[Long, List[Message]]): Behavior[WorkerEvent] =
        Behaviors.receive[WorkerEvent] { (ctx, message) =>
            message match {
                case Prepare() => 
                    worker(receivedWorkers, receivedMessages)
                case RegisterAgentMap(driver) => 
                    driver ! Driver.InitializeAgentMap(workerId, simIds)
                    Behaviors.same
                case ReceiveAgentMap(workerId, nameIds) => 
                    if (!nameMap.isRegistered(workerId)){
                        nameMap.update(workerId, nameIds)
                    }
                    Behaviors.same
                case ReceiveMessages(workerId, messages) =>
                    worker(workerId :: receivedWorkers, receivedMessages ++ receivedMessages.map(x => (x._1, if (messages.get(x._1).isDefined) messages(x._1) ::: x._2 else x._2)))
                case ExpectedReceives(ids, replyTo) => 
                    // If worker has received messages from all above workers, then resume agents
                    if (ids == null || receivedWorkers.diff(ids).isEmpty){
                        ctx.log.debug(f"Worker ${workerId} starts!")
                        ctx.spawnAnonymous(
                            Aggregator[LocalAgent.MessagesAdded, AgentsCompleted](
                                sendRequests = { replyTo =>
                                    local_agents.map(a => {
                                        a._2 ! LocalAgent.AddMessages(receivedMessages.getOrElse(a._1, List()) ::: collectedMessages.remove(a._1).getOrElse(List()), replyTo)
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
                                    val ans = collectedMessages.groupBy(i => nameMap.getWorker(i._1))
                                    replyTo ! SendTo(workerId, ans.keys.toSeq, passedRounds)
                                    AgentsCompleted(ans)
                                },
                                timeout=100.seconds))
                        Behaviors.same
                    } else {
                        worker(receivedWorkers, receivedMessages)
                    }
                case AgentsCompleted(indexedMessages) =>
                    ctx.log.debug(f"Local agents in worker ${workerId} have completed!")
                    peer_workers.foreach(w => {
                        val ans: Map[Long, List[Message]] = indexedMessages.getOrElse(w._1, Map()).toMap
                        collectedMessages --= ans.map(_._1)
                        w._2 ! ReceiveMessages(workerId, ans)
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

object DriverWorkerExp {
    sealed trait Command
    final case class SpawnDriver(totalWorkers: Int, totalTurn: Int, messages: List[Message]) extends Command
    final case class SpawnWorker(workerId: Int, sims: Seq[Actor], totalWorkers: Int) extends Command
    final case class DriverStopped() extends Command
    final case class WorkerStopped(workerId: Int, sims: Seq[Actor]) extends Command

    var cluster: Cluster = null
    var totalWorkers: Int = 0
    val stoppedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var activeWorkers: Set[Int] = Set()

    def apply(totalTurn: Int, totalWorkers: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            this.totalWorkers = totalWorkers
            val roles = cluster.selfMember.getRoles
            val totalActors = actors.size
            val actorsPerWorker = totalActors/totalWorkers
            ctx.log.debug(f"Driver worker experiment starts! Member roles: ${cluster.selfMember.getRoles}")

            if (roles.exists(p => p.startsWith("Worker"))) {
                ctx.log.warn(f"Creating a worker!")
                val worker_id = roles.head.split("-").last.toInt                    
                ctx.self ! SpawnWorker(worker_id, actors.slice(worker_id*actorsPerWorker, List((worker_id+1)*actorsPerWorker, totalActors).min), totalWorkers)
            } 
            
            if (cluster.selfMember.hasRole("Driver")) {
                ctx.log.warn(f"Creating a driver!")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn, messages)
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.log.warn(f"Standalone mode")
                ctx.self ! SpawnDriver(1, totalTurn, messages)
                ctx.self ! SpawnWorker(0, actors, 1)
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
                    activeWorkers = activeWorkers + workerId
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
                        stoppedWorkers.add(workerId)
                        if (stoppedWorkers.toSet.diff(activeWorkers).isEmpty){
                            DriverWorkerRun.addStoppedAgents(finalAgents ++ agents)
                            Behaviors.stopped {() =>
                                ctx.system.terminate()
                            }
                        } else {
                            waitTillFinish(finalAgents ++ agents)
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
            val total_workers: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.total-workers").render().toInt
            println(f"Total workers are ${total_workers} Total actors ${actors.size}")
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