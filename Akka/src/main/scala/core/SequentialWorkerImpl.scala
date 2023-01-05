package simulation.akka.core
package sequential

import meta.runtime.{Actor, Message}
import scala.collection.mutable.{Map => MutMap}

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import scala.collection.JavaConversions._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
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
    // private val receivedMessages = new ConcurrentHashMap[Long, List[Message]]()
    private val receivedWorkers = new ConcurrentHashMap[Int, Int]()
    private var sendToRef: ActorRef[SendTo] = null

    // private var tscontroller: ActorRef[TimeseriesController.LogWorker] = null
    var start: Long = 0
    var end: Long = 0
    
    // private var logicalClock: Int = 0
    private var acceptedInterval: Int = 0
    private var proposeInterval: Int = Int.MaxValue
    private var availability: Int = 1

    private var completedAgents: Int = 0
    private var registeredWorkers: AtomicInteger = new AtomicInteger(0)

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int): Behavior[WorkerEvent] = Behaviors.setup { ctx =>
        local_sims = sims.map(x => (x.id, x)).toMap
        totalAgents = sims.size
        this.totalWorkers = totalWorkers
        workerId = id

        val simIds = sims.map(i => i.id).toSet

        if (simulation.akka.API.OptimizationConfig.conf == simulation.akka.API.DirectMethodCall){
            sims.foreach(i => {
                i.reachableAgents = i.connectedAgents.map(i => i.id).toSet.intersect(simIds)
            })
        }
        
        if (totalWorkers>1){
            ctx.system.receptionist ! Receptionist.Register(WorkerUpdateAgentMapServiceKey, ctx.self)

            val workerSub = ctx.messageAdapter[Receptionist.Listing] {
                case WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                    if (workers.size == totalWorkers){
                        workers.filter(i => i!= ctx.self).foreach(w => {
                            w ! ReceiveAgentMap(workerId, simIds.asInstanceOf[Set[java.lang.Long]], ctx.self)
                        })
                    }
                    Prepare()
            }       
            
            ctx.system.receptionist ! Receptionist.Subscribe(WorkerUpdateAgentMapServiceKey, workerSub)
        } else {
            ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
            ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
        }
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
                    // val start = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} receives ${messages.size} messages from worker ${wid}")
                    receivedWorkers.computeIfAbsent(wid, x => {
                        for (m <- messages) {
                            local_sims(m._1).receivedMessages.addAll(m._2)
                        }
                        0
                    })
                    receivedWorkers.putIfAbsent(wid, 0)
                    if (receivedWorkers.keys().size == totalWorkers-1){
                        ctx.self ! Start()
                    }
                    // val end = System.currentTimeMillis()
                    // ctx.log.info(f"Worker ${workerId} processes message takes ${end-start} ms")
                    worker()
                    
                case Start() =>
                    ctx.log.debug(f"Worker ${workerId} starts! Received from ${receivedWorkers.keys().toSet}")
                    start = System.currentTimeMillis()

                    if (totalAgents > 0){
                        receivedWorkers.clear()
                        var collectedMessages: MutMap[Long, List[Message]] = MutMap[Long, List[Message]]()

                        var localRounds: Int = 0
                        while (localRounds < this.availability) {
                            // collectedMessages.clear()
                            local_sims.foreach(a => {
                                a._2.time += acceptedInterval
                                val tmpProposeInterval = a._2.run()
                                if (tmpProposeInterval < proposeInterval){
                                    proposeInterval = tmpProposeInterval
                                }
                                a._2.sendMessages.foreach(i => {
                                    collectedMessages.update(i._1, collectedMessages.getOrElse(i._1, List[Message]()) ::: i._2) 
                                })
                            })
                            // Deliver local messages to agents' mailboxes
                            collectedMessages.filterKeys(x => local_sims.get(x).isDefined).foreach(i => {
                                local_sims(i._1).receivedMessages.addAll(collectedMessages.remove(i._1).get)
                            })
                            acceptedInterval = proposeInterval
                            localRounds += proposeInterval
                        }
                        // message_map =  Map()
                        message_map = collectedMessages.toMap.groupBy(i => nameMap.getOrElse(i._1, workerId))
                    } 
                    ctx.self ! AgentsCompleted()
                    worker()

                case ExpectedReceives(replyTo, acceptedInterval, availability) => 
                    // send out messages to other workers only at the beginning of a round to avoid race condition
                    peer_workers.forEach((id, ref) => {
                        if (message_map.get(id) != None){
                            ref ! ReceiveMessages(workerId, message_map(id).asInstanceOf[Map[java.lang.Long, List[Message]]])
                        } else {
                            ref ! ReceiveMessages(workerId, Map())
                        }
                    })
                    sendToRef = replyTo      
                    this.acceptedInterval = acceptedInterval    
                    this.availability = availability
                    // logicalClock += acceptedInterval       
                    if (receivedWorkers.keys().size == totalWorkers-1){
                        ctx.self ! Start()
                    } 
                    worker()
                    
                case AgentsCompleted() =>
                    end = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} runs for ${end-start} ms, propose ${proposeInterval}")
                    sendToRef ! SendTo(workerId, proposeInterval)
                    // if (simulation.akka.API.Simulate.log != null){
                    //     simulation.akka.API.Simulate.log.add[Actor](logicalClock, local_sims.map(_._2.SimClone()))
                    // }
                    completedAgents = 0
                    Behaviors.same

                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    Behaviors.stopped
            }
        }
}