package simulation.akka

import meta.runtime._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId
import meta.API.{SimulationSnapshot, BoundedLatency, newContainer}
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Await
import scala.concurrent.duration._

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}

import java.util.Collections
import java.util.concurrent.atomic.AtomicInteger
import collection.JavaConverters._

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
    final case class RoundEnd() extends DriverEvent with NoSerializationVerificationNeeded

    // val serviceKeyInitAgentMap = ServiceKey[InitializeAgentMap]("InitializeAgentMap")
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

    var start: Long = 0
    var end: Long = 0

    def apply(workers: Int, maxTurn: Int): Behavior[DriverEvent] = Behaviors.setup {ctx =>
        // ctx.system.receptionist ! Receptionist.Register(serviceKeyInitAgentMap, ctx.self)

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

                case RoundStart => {
                    ctx.log.info(f"Round ${currentTurn}")
                    start = System.currentTimeMillis()
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
                                for (r <- replies) {
                                    for (k <- r.sendTo) {
                                        workerReceiveFrom = workerReceiveFrom.updated(k, workerReceiveFrom.getOrElse(k, Set()).union(Set(r.workerId)))
                                    }
                                    if (r.agentTime > currentTurn){
                                        currentTurn = r.agentTime
                                    }
                                }
                                RoundEnd()
                            },
                            timeout=100.seconds))
                    ctx.log.debug(f"Driver receives notifications from all workers! New receive from ${workerReceiveFrom}")
                    driver()
                }

                case RoundEnd() =>
                    end = System.currentTimeMillis()
                    ctx.log.info(f"Round takes ${end-start} time")
                    if (currentTurn >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.debug(f"Simulation completes! Stop the driver")
                            workersStop.foreach(a => a ! Worker.Stop())
                        }
                    } else {
                        ctx.self ! RoundStart
                        driver()
                    }
            }
        }
}

object Worker {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[SendTo], name = "SendTo")))
    sealed trait WorkerEvent
    final case class Prepare() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class AgentsCompleted() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class Stop() extends WorkerEvent with JsonSerializable
    final case class Start() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class ReceiveMessages(workerId: Int, messages: Map[Long, List[Message]]) extends WorkerEvent with JsonSerializable
    final case class ReceiveAgentMap(workerId: Int, agentIds: Seq[Long], replyTo: ActorRef[ReceiveMessages]) extends WorkerEvent with JsonSerializable
    final case class SendTo(workerId: Int, sendTo: Set[Int], agentTime: Int) extends WorkerEvent with JsonSerializable
    final case class ExpectedReceives(ids: Map[Int, Set[Int]], sendTo: ActorRef[SendTo]) extends WorkerEvent with JsonSerializable
    final case class MessagesAdded(agentTime: Int, indexedSentMessages: Map[Long, ListBuffer[Message]]) extends WorkerEvent with NoSerializationVerificationNeeded

    val WorkerStartServiceKey = ServiceKey[ExpectedReceives]("WorkerStart")
    val WorkerStopServiceKey = ServiceKey[Stop]("WorkerStop")
    val WorkerUpdateAgentMapServiceKey = ServiceKey[ReceiveAgentMap]("Update peer workers")
}

class Worker {
    import Worker._
    private var local_sims: ConcurrentHashMap[Long, Actor] = new ConcurrentHashMap[Long, Actor]()
    private var workerId: Int = 0
    private var totalAgents: Int = 0
    private val nameMap: ConcurrentHashMap[Long, Int] = new ConcurrentHashMap[Long, Int]()

    private var local_compute_threads: ConcurrentHashMap[Long, ActorRef[LocalAgent.AgentEvent]] = new ConcurrentHashMap[Long, ActorRef[LocalAgent.AgentEvent]]()
    private val peer_workers: ConcurrentHashMap[Int, ActorRef[Worker.ReceiveMessages]] = new ConcurrentHashMap[Int, ActorRef[Worker.ReceiveMessages]]() 
    private var message_map: ConcurrentHashMap[Int, MutMap[Long, ConcurrentLinkedQueue[Message]]] = new ConcurrentHashMap[Int, MutMap[Long, ConcurrentLinkedQueue[Message]]]()
    private val receivedMessages: ConcurrentHashMap[Long, ConcurrentLinkedQueue[Message]] = new ConcurrentHashMap[Long, ConcurrentLinkedQueue[Message]]()
    private val receivedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    private var expectedWorkerSet: Set[Int] = Set[Int]()
    private var sendToRef: ActorRef[SendTo] = null

    // private var tscontroller: ActorRef[TimeseriesController.LogWorker] = null
    var start: Long = 0
    var end: Long = 0

    private var logical_clock: Int = 0
    private var completedAgents: Int = 0

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int): Behavior[WorkerEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
        ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(WorkerUpdateAgentMapServiceKey, ctx.self)

        val containers: ListBuffer[Actor] = ListBuffer[Actor]()
        local_sims = new ConcurrentHashMap(mapAsJavaMap(sims.map(x => (x.id, x)).toMap))
        totalAgents = sims.size
        workerId = id

        if (ConfigFactory.load("driver-worker").hasPath("driver-worker.containers-per-worker")){
            val totalSims = sims.size
            val totalGroups: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.containers-per-worker").render().toInt
            val simsPerGroup = totalSims / totalGroups
            for (i <- Range(0, totalGroups-1)) {
                containers.append(newContainer(sims.toList.slice(i*simsPerGroup, (i+1)*simsPerGroup))(true, BoundedLatency))
            }
            containers.append(newContainer(sims.slice((totalGroups-2), totalSims).toList)(true, BoundedLatency))
        } 
        
        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case Worker.WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, local_sims.keys().asScala.toSeq, ctx.self)
                    })
                }
                Prepare()
        } 

        // Creating an actor for each Sim
        local_compute_threads = 
            new ConcurrentHashMap(mapAsJavaMap(sims.map(a => (a.id, ctx.spawn((new LocalAgent).apply(a), f"simAgent${a.id}"))).toMap))
        ctx.system.receptionist ! Receptionist.Subscribe(WorkerUpdateAgentMapServiceKey, workerSub)
        // ctx.system.receptionist ! Receptionist.Subscribe(TimeseriesController.tsControllerServiceKey, workerSub)
        worker()
    }

    // Consider replacing receivedWorkers with a total workers
    private def worker(): Behavior[WorkerEvent] =
        Behaviors.receive[WorkerEvent] { (ctx, message) =>
            message match {
                case Prepare() => 
                    worker()

                case ReceiveAgentMap(wid, nameIds, reply) => 
                    nameIds.foreach(n => {
                        nameMap.putIfAbsent(n, wid)
                    })
                    peer_workers.putIfAbsent(wid, reply)
                    Behaviors.same

                case ReceiveMessages(wid, messages) =>
                    ctx.log.debug(f"Worker ${workerId} receives messages from worker ${wid} ${messages}")
                    if (!receivedWorkers.contains(wid)){
                        // buffer collected messages and send out only after agents finish
                        messages.foreach(i => {
                            if (receivedMessages.containsKey(i._1)){
                                receivedMessages.get(i._1).addAll(i._2.asJava)
                            } else {
                                val bar = new ConcurrentLinkedQueue[Message]()
                                bar.addAll(i._2.asJava)
                                receivedMessages.put(i._1, bar)
                            }
                        })
                        receivedWorkers.add(wid)
                    }
                    if (receivedWorkers.asScala.toSet == expectedWorkerSet){
                        ctx.self ! Start()
                    }
                    worker()
                    
                case Start() =>
                    ctx.log.debug(f"Worker ${workerId} starts! Received from ${receivedWorkers}")
                    start = System.currentTimeMillis()
                    receivedWorkers.clear()
                    expectedWorkerSet = Set[Int]()
                    receivedMessages.keys.asScala.foreach(i => {
                        local_sims.get(i).receivedMessages.addAll(receivedMessages.remove(i))
                    })

                    if (totalAgents > 0){
                        local_compute_threads.forEach((k, v) => {
                            v ! LocalAgent.AddMessages(ctx.self)   
                        })
                    } else {
                        ctx.self ! AgentsCompleted()
                    }
                    worker()

                // When one agent completes
                // Note that messages cannot be sent immediately bc receivers can still be active
                case MessagesAdded(agentTime, indexedSentMessages) => 
                    if (logical_clock < agentTime) {
                        logical_clock = agentTime
                    }
                    ctx.log.debug(f"${workerId} Message map before adding ${indexedSentMessages} are " + message_map)

                    indexedSentMessages.foreach(x => {
                        val wid = nameMap.getOrDefault(x._1, workerId)
                        if (message_map.containsKey(wid)){
                            message_map.get(wid).getOrElseUpdate(x._1, new ConcurrentLinkedQueue[Message]()).addAll(x._2.asJava)
                        } else {
                            val foo = MutMap[Long, ConcurrentLinkedQueue[Message]]()
                            val bar = new ConcurrentLinkedQueue[Message]()
                            bar.addAll(x._2.asJava)
                            foo.put(x._1, bar)
                            message_map.put(wid, foo)
                        }
                    })

                    ctx.log.debug(f"${workerId} Message map after is " + message_map)
                    completedAgents += 1
                    if (completedAgents == totalAgents){
                        ctx.self ! AgentsCompleted()
                    }
                    worker()

                case ExpectedReceives(receive_map, replyTo) => 
                    sendToRef = replyTo                    
                    expectedWorkerSet = receive_map.getOrElse(workerId, Set[Int]())
                    if (receivedWorkers.asScala.toSet == expectedWorkerSet){
                        ctx.self ! Start()
                    } 
                    worker()
                    
                case AgentsCompleted() =>
                    end = System.currentTimeMillis()
                    sendToRef ! SendTo(workerId, message_map.keys.asScala.filter(i => i!=workerId).toSet, logical_clock)
                    // Dispatch local messages asap
                    val local_msgs: MutMap[Long, ConcurrentLinkedQueue[Message]] = message_map.remove(workerId)
                    // send out messages to receivers asap
                    message_map.keys.asScala.foreach(i => {
                        peer_workers.get(i) ! ReceiveMessages(workerId, message_map.remove(i).map(i => (i._1, i._2.asScala.toList)).toMap)
                    })
                    if (local_msgs != null) {
                        local_msgs.foreach(m => {
                            local_sims.get(m._1).receivedMessages.addAll(m._2)
                        })
                    }
                    
                    completedAgents = 0
                    ctx.log.debug(f"Worker ${workerId} runs for ${end-start} ms")
                    Behaviors.same

                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    local_compute_threads.forEach((k, v) => ctx.stop(v))
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
    ))
    trait AgentEvent 
    final case class AddMessages(replyTo: ActorRef[Worker.MessagesAdded]) extends AgentEvent with JsonSerializable
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
                case AddMessages(replyTo) => 
                    // println(f"Agent ${sim.id} receives ${messages.size} messages")
                    ctx.log.debug(f"Agent ${sim.id} receives ${sim.receivedMessages.size} messages")
                    val time = sim.run()
                    replyTo ! Worker.MessagesAdded(time, sim.sendMessages.toMap)
                    Behaviors.same
            }
        }.receiveSignal {
            case (context, PostStop) =>
                context.log.debug(f"Agent ${sim.id} stopped")
                Behaviors.stopped
        }
}

object AkkaExp {
    sealed trait Command
    final case class SpawnDriver(totalWorkers: Int, totalTurn: Int) extends Command
    final case class SpawnWorker(workerId: Int, sims: Seq[Actor], totalWorkers: Int) extends Command
    final case class DriverStopped() extends Command
    final case class WorkerStopped(workerId: Int, sims: Seq[Actor]) extends Command

    var cluster: Cluster = null
    var totalWorkers: Int = 0
    val stoppedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var activeWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var finalAgents: ConcurrentLinkedQueue[Actor] = new ConcurrentLinkedQueue[Actor]()

    def apply(totalTurn: Int, totalWorkers: Int, actors: List[Actor]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            this.totalWorkers = totalWorkers
            val roles: Set[String] = cluster.selfMember.getRoles.asScala.toSet
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
                ctx.log.debug(f"Creating a worker!")
                val worker_id = roles.head.split("-").last.toInt
                val containedAgents = actors.slice(worker_id*actorsPerWorker, List((worker_id+1)*actorsPerWorker, totalActors).min)        
                // if (containedAgents.size > 0){
                    ctx.self ! SpawnWorker(worker_id, containedAgents, totalWorkers)
                // }
            } 
            
            if (cluster.selfMember.hasRole("Driver")) {
                ctx.log.debug(f"Creating a driver!")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn)
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.log.debug(f"Standalone mode")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn)
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
                case SpawnDriver(totalWorkers, totalTurn) => 
                    val driver = ctx.spawn((new Driver).apply(totalWorkers, totalTurn), "driver")
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
                        ctx.log.debug(f"Worker stop signal received! ${workerId} Stopped workers: ${stoppedWorkers}")
                        if (!stoppedWorkers.contains(workerId)){
                            stoppedWorkers.add(workerId)
                            if (activeWorkers.asScala.toSet.diff(stoppedWorkers.asScala.toSet).isEmpty){
                                AkkaRun.addStoppedAgents(finalAgents ++ agents)
                                Behaviors.stopped {() =>
                                    ctx.system.terminate()
                                }
                            } else {
                                waitTillFinish(finalAgents ++ agents)
                            }
                        } else {
                            if (activeWorkers.asScala.toSet.diff(stoppedWorkers.asScala.toSet).isEmpty){
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

object AkkaRun {

    private var stoppedAgents = List[Actor]()

    var lastWords: List[Message] = List[Message]()

    def addStoppedAgents(agents: IndexedSeq[Actor]): Unit = {
        stoppedAgents = agents.toList 
    }

    def initialize(): Unit = {
        stoppedAgents=List()
        lastWords=List()
    }

    def apply(actors: List[Actor], totalTurn: Int,
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
            val actorSystem = ActorSystem(AkkaExp(totalTurn, total_workers, actors), "SimsCluster", config)
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
