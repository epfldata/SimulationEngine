package simulation.akka.core
package sequential

import meta.runtime.{Actor, Message}
import scala.collection.mutable.{Map => MutMap}

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import scala.collection.JavaConversions._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import java.util.concurrent.atomic.AtomicInteger

class Worker {
    import WorkerSpec._
    private var local_sims: Map[Long, Actor] = Map[Long, Actor]()
    private var workerId: Int = 0
    private var totalAgents: Int = 0
    private var totalWorkers: Int = 0
    private val nameMap: ConcurrentHashMap[Long, Int] = new ConcurrentHashMap[Long, Int]()

    private val peer_workers: ConcurrentHashMap[Int, ActorRef[ReceiveMessages]] = new ConcurrentHashMap[Int, ActorRef[ReceiveMessages]]() 
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
    private var registeredWorkers: AtomicInteger = new AtomicInteger(0)

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int): Behavior[WorkerEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
        ctx.system.receptionist ! Receptionist.Register(WorkerUpdateAgentMapServiceKey, ctx.self)

        local_sims = sims.map(x => (x.id, x)).toMap
        totalAgents = sims.size
        this.totalWorkers = totalWorkers
        workerId = id

        val simIds = sims.map(i => i.id).toSet
        sims.foreach(i => { i.reachableAgents = simIds })
        
        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, simIds, ctx.self)
                    })
                }
                Prepare()
        }       
        
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
                    if (!peer_workers.containsKey(wid)){
                        val total = registeredWorkers.addAndGet(1)
                        nameIds.foreach(n => {
                            nameMap.putIfAbsent(n, wid)
                        })
                        peer_workers.putIfAbsent(wid, reply)
                        if (total == totalWorkers - 1){
                            ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
                            ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
                        }
                    }
                    Behaviors.same

                case ReceiveMessages(wid, messages) =>
                    val start = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} receives ${messages.size} messages from worker ${wid}")
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
                    val sendToWorkers = message_map.keys.filter(i => i!=workerId).toSet
                    sendToRef ! SendTo(workerId, sendToWorkers, logical_clock)
                    // send out messages to other workers asap
                    sendToWorkers.foreach(i => {
                        // val msgs = message_map.remove(i)
                        peer_workers.get(i) ! ReceiveMessages(workerId, message_map(i))
                    })
                    // Deliver local messages to agents' mailboxes
                    message_map.getOrElse(workerId, List()).foreach(i => {
                        local_sims(i._1).receivedMessages.addAll(i._2)
                    })
                    completedAgents = 0
                    Behaviors.same

                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    Behaviors.stopped
            }
        }
}