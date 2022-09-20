package simulation.akkaDirectMethodCall

import meta.runtime._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId
import meta.API.{SimulationSnapshot}
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

import simulation.akka.{Driver}

class Worker {
    import simulation.akka.Worker._
    private var local_sims: ConcurrentHashMap[Long, Actor] = new ConcurrentHashMap[Long, Actor]()
    private var workerId: Int = 0
    private var totalAgents: Int = 0
    private val nameMap: ConcurrentHashMap[Long, Int] = new ConcurrentHashMap[Long, Int]()

    private val peer_workers: ConcurrentHashMap[Int, ActorRef[simulation.akka.Worker.ReceiveMessages]] = new ConcurrentHashMap[Int, ActorRef[simulation.akka.Worker.ReceiveMessages]]() 
    private var message_map: Map[Int, Map[Long, List[Message]]] = Map[Int, Map[Long, List[Message]]]()
    // private val receivedMessages: ConcurrentHashMap[Long, ConcurrentLinkedQueue[Message]] = new ConcurrentHashMap[Long, ConcurrentLinkedQueue[Message]]()
    private val receivedMessages: ConcurrentHashMap[Long, List[Message]] = new ConcurrentHashMap[Long, List[Message]]()
    private val receivedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    private var expectedWorkerSet: Set[Int] = Set[Int]()
    private var sendToRef: ActorRef[SendTo] = null

    // private var tscontroller: ActorRef[TimeseriesController.LogWorker] = null
    var start: Long = 0
    var end: Long = 0

    private var logical_clock: Int = 0
    private var completedAgents: Int = 0

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int): Behavior[simulation.akka.Worker.WorkerEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
        ctx.system.receptionist ! Receptionist.Register(simulation.akka.Worker.WorkerStartServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(simulation.akka.Worker.WorkerStopServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(simulation.akka.Worker.WorkerUpdateAgentMapServiceKey, ctx.self)

        local_sims = new ConcurrentHashMap(mapAsJavaMap(sims.map(x => (x.id, x)).toMap))
        totalAgents = sims.size
        workerId = id

        val simIds = sims.map(i => i.id).toSet
        // sims.foreach(i => { i.reachableAgents = simIds })
        
        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case simulation.akka.Worker.WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, local_sims.keys().toSeq, ctx.self)
                    })
                }
                Prepare()
        }       
        
        ctx.system.receptionist ! Receptionist.Subscribe(simulation.akka.Worker.WorkerUpdateAgentMapServiceKey, workerSub)
        // ctx.system.receptionist ! Receptionist.Subscribe(TimeseriesController.tsControllerServiceKey, workerSub)
        worker()
    }

    // Consider replacing receivedWorkers with a total workers
    private def worker(): Behavior[simulation.akka.Worker.WorkerEvent] =
        Behaviors.receive[simulation.akka.Worker.WorkerEvent] { (ctx, message) =>
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
                    val start = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} receives messages from worker ${wid} ${messages}")
                    if (!receivedWorkers.contains(wid)){
                        for (m <- messages) {
                            receivedMessages.update(m._1, receivedMessages.getOrElse(m._1, List()) ::: m._2)
                        }
                        receivedWorkers.add(wid)
                    }
                    if (receivedWorkers.toSet == expectedWorkerSet){
                        ctx.self ! Start()
                    }
                    val end = System.currentTimeMillis()
                    // ctx.log.info(f"Worker ${workerId} processes message takes ${end-start} ms")
                    worker()
                    
                case Start() =>
                    ctx.log.debug(f"Worker ${workerId} starts! Received from ${receivedWorkers}")
                    start = System.currentTimeMillis()

                    if (totalAgents > 0){
                        receivedWorkers.clear()
                        expectedWorkerSet = Set[Int]()

                        var collectedMessages: MutMap[Long, List[Message]] = MutMap[Long, List[Message]]()

                        local_sims.foreach(a => {
                            val x = receivedMessages.remove(a._1)
                            if (x != null) {
                                a._2.receivedMessages.addAll(x)
                            }
                            val time = a._2.run()
                            if (logical_clock < time){
                              logical_clock = time
                            }
                            a._2.sendMessages.foreach(i => {
                              collectedMessages.update(i._1, collectedMessages.getOrElse(i._1, List[Message]()) ::: i._2.toList) 
                            })
                        })
                        
                        local_sims.foreach(i => {
                          i._2.merge()
                        })
                        message_map = collectedMessages.toMap.groupBy(i => nameMap.getOrElse(i._1, workerId))
                    } 
                    ctx.self ! AgentsCompleted()
                    worker()

                case ExpectedReceives(receive_map, replyTo) => 
                    sendToRef = replyTo                    
                    expectedWorkerSet = receive_map.getOrElse(workerId, Set[Int]())
                    if (receivedWorkers.toSet == expectedWorkerSet){
                        ctx.self ! Start()
                    } 
                    worker()
                    
                case AgentsCompleted() =>
                    end = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} runs for ${end-start} ms")
                    val remoteWorkers = message_map.keys.filter(i => i!=workerId).toSet
                    sendToRef ! SendTo(workerId, remoteWorkers, logical_clock)
                    // send out messages to other workers asap
                    remoteWorkers.foreach(i => {
                        peer_workers.get(i) ! ReceiveMessages(workerId, message_map(i))
                    })
                    // Deliver local messages to agents' mailboxes
                    message_map.getOrElse(workerId, List()).foreach(i => {
                        local_sims.get(i._1).receivedMessages.addAll(i._2)
                    })
                    completedAgents = 0
                    Behaviors.same

                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    Behaviors.stopped
            }
        }
}

object AkkaExpDirectMethodCall {
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
            val roles: Set[String] = cluster.selfMember.getRoles.toSet
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
                            if (activeWorkers.toSet.diff(stoppedWorkers.toSet).isEmpty){
                                AkkaDirectMethodCall.addStoppedAgents(finalAgents ++ agents)
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

object AkkaDirectMethodCall {

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
            val actorSystem = ActorSystem(AkkaExpDirectMethodCall(totalTurn, total_workers, actors), "SimsCluster", config)
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
