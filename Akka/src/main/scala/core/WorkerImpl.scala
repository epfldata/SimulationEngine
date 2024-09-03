package simulation.akka.core

import meta.runtime.{Actor, Message}
import meta.API.TimeseriesBuilder
import scala.collection.mutable.{Buffer, Map => MutMap}

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import scala.collection.JavaConversions._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.receptionist.{Receptionist}
import java.util.concurrent.atomic.AtomicInteger

class Worker {
    import WorkerSpec._
    private var localSims: Map[Long, Actor] = Map[Long, Actor]()
    private var workerId: Int = 0
    private var totalAgents: Int = 0
    private var totalWorkers: Int = 0
    // key: agent id, value: worker id. Track which worker an agent is placed at
    private val nameMap: MutMap[Long, Int] = MutMap[Long, Int]()
    // private val nameMapCache: MutMap[Long, Long] = MutMap[Long, Long]()
    private val peerWorkers: ConcurrentHashMap[Int, ActorRef[ReceiveMessages]] = new ConcurrentHashMap[Int, ActorRef[ReceiveMessages]]() 
    private var messageMap: Map[Int, Map[Long, Seq[Message]]] = Map[Int, Map[Long, Seq[Message]]]()
    private val receivedWorkers = new ConcurrentHashMap[Int, Int]()
    private var sendToRef: ActorRef[SendTo] = null
    private var loggerRef: ActorRef[LogControllerSpec.AggregateLog] = null

    var start: Long = 0
    var end: Long = 0
    
    private var logicalClock: Long = 0
    private var acceptedInterval: Int = 0
    private var proposeInterval: Int = Int.MaxValue
    private var availability: Int = 1

    private var completedAgents: Long = 0
    private var registeredWorkers: AtomicInteger = new AtomicInteger(0)

    def apply(id: Int, sims: Seq[Actor], totalWorkers: Int, builder: Option[TimeseriesBuilder]): Behavior[WorkerEvent] = Behaviors.setup { ctx =>
        localSims = sims.map(x => (x.id, x)).toMap
        totalAgents = sims.size
        this.totalWorkers = totalWorkers
        workerId = id

        val simIds = sims.map(i => i.id).toSet

        if (simulation.akka.API.OptimizationConfig.conf == simulation.akka.API.DirectMethodCall){
            sims.foreach(i => {
                i.reachableAgents = i.connectedAgents.map(i => i.id).toSet.intersect(simIds)
            })
        }

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case WorkerUpdateAgentMapServiceKey.Listing(workers) =>
                if (workers.size == totalWorkers){
                    workers.filter(i => i!= ctx.self).foreach(w => {
                        w ! ReceiveAgentMap(workerId, simIds.asInstanceOf[Set[java.lang.Long]], ctx.self)
                    })
                }
                Prepare()

            case LogControllerSpec.LoggerAggregateServiceKey.Listing(logger) =>
                ctx.log.debug(f"Log controller registered!")
                if (logger.size == 1) {
                    loggerRef = logger.head
                }
                Prepare()
        }  

        if (totalWorkers>1){
            ctx.system.receptionist ! Receptionist.Register(WorkerUpdateAgentMapServiceKey, ctx.self)     
            ctx.system.receptionist ! Receptionist.Subscribe(WorkerUpdateAgentMapServiceKey, workerSub)
        } else {
            ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
            ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
        }

        if (builder.isDefined) {
            ctx.system.receptionist ! Receptionist.Subscribe(LogControllerSpec.LoggerAggregateServiceKey, workerSub)
        }
        worker(builder)
    }

    // Consider replacing receivedWorkers with a total workers
    private def worker(builder: Option[TimeseriesBuilder]): Behavior[WorkerEvent] =
        Behaviors.receive[WorkerEvent] { (ctx, message) =>
            message match {
                case Prepare() => 
                    worker(builder)

                case ReceiveAgentMap(wid, nameIds, reply) => 
                    peerWorkers.computeIfAbsent(wid, x => {
                        val total = registeredWorkers.addAndGet(1)
                        nameIds.foreach(n => {
                            nameMap.update(n, wid)
                        })
                        if (total == totalWorkers - 1){
                            ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
                            ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
                        }
                        reply
                    })
                    // if (!peerWorkers.containsKey(wid)){
                    //     val total = registeredWorkers.addAndGet(1)
                    //     nameIds.foreach(n => {
                    //         nameMap.putIfAbsent(n, wid)
                    //     })
                    //     peerWorkers.putIfAbsent(wid, reply)
                    //     if (total == totalWorkers - 1){
                    //         ctx.system.receptionist ! Receptionist.Register(WorkerStartServiceKey, ctx.self)
                    //         ctx.system.receptionist ! Receptionist.Register(WorkerStopServiceKey, ctx.self)
                    //     }
                    // }
                    Behaviors.same

                case ReceiveMessages(wid, messages) =>
                    ctx.log.debug(f"Worker ${workerId} receives ${messages.size} messages from worker ${wid}")
                    receivedWorkers.computeIfAbsent(wid, x => {
                        for (m <- messages) {
                            localSims(m._1).receivedMessages ++= m._2
                            // localSims(m._1).receivedMessages :::= m._2._1
                            // localSims(m._1).receivedSerializedMessages :::= m._2._2
                        }
                        0
                    })
                    receivedWorkers.putIfAbsent(wid, 0)
                    if (receivedWorkers.keys().size == totalWorkers-1){
                        ctx.self ! RoundStart()
                    }
                    worker(builder)
                
                case RoundStart() =>
                    ctx.log.debug(f"Worker ${workerId} starts! Received from ${receivedWorkers.keys().toSet}")
                    start = System.currentTimeMillis()

                    if (totalAgents > 0){
                        receivedWorkers.clear()
                        val collectedMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()
                        // val collectedSerializedMessages: MutMap[Long, Buffer[Array[Byte]]] = MutMap[Long, Buffer[Array[Byte]]]()

                        var localRounds: Long = 0
                        while (localRounds < this.availability) {
                            localSims.foreach(a => {
                                a._2.time += acceptedInterval
                                val tmpProposeInterval = a._2.run()
                                if (tmpProposeInterval < proposeInterval){
                                    proposeInterval = tmpProposeInterval
                                }
                                a._2.sendMessages.foreach(m => {
                                    collectedMessages.getOrElseUpdate(m._1, Buffer[Message]()) ++= m._2
                                    m._2.clear()
                                })
                                // a._2.sendSerializedMessages.foreach(m => collectedSerializedMessages.update(m._1, collectedSerializedMessages.getOrElse(m._1, List[Array[Byte]]()) ::: m._2.toList))
                            })
                            // Deliver local messages to agents' mailboxes
                            collectedMessages.filterKeys(x => localSims.get(x).isDefined).foreach(i => {
                                localSims(i._1).receivedMessages ++= collectedMessages.remove(i._1).get
                            })
                            // collecting serialized messages is experimental
                            // collectedSerializedMessages.filterKeys(x => localSims.get(x).isDefined).foreach(i => {
                            //     localSims(i._1).receivedSerializedMessages :::= collectedSerializedMessages.remove(i._1).get
                            // })
                            acceptedInterval = proposeInterval
                            localRounds += proposeInterval
                        }
                        messageMap = collectedMessages.toMap.groupBy(i => {
                            nameMap.getOrElse(i._1, workerId)
                        })
                    } 
                    ctx.self ! AgentsCompleted()
                    worker(builder)

                case ExpectedReceives(replyTo, acceptedInterval, availability) => 
                    // send out messages to other workers only at the beginning of a round to avoid race condition
                    peerWorkers.forEach((id, ref) => {
                        if (messageMap.get(id) != None){
                            ref ! ReceiveMessages(workerId, messageMap(id).asInstanceOf[Map[java.lang.Long, Seq[Message]]])
                        } else {
                            ref ! ReceiveMessages(workerId, Map())
                        }
                    })
                    sendToRef = replyTo      
                    this.acceptedInterval = acceptedInterval    
                    this.availability = availability
                    logicalClock += acceptedInterval       
                    if (receivedWorkers.keys().size == totalWorkers-1){
                        ctx.self ! RoundStart()
                    } 
                    worker(builder)
                    
                case AgentsCompleted() =>
                    end = System.currentTimeMillis()
                    ctx.log.debug(f"Worker ${workerId} runs for ${end-start} ms, propose ${proposeInterval}")
                    if (builder.isDefined){
                        loggerRef ! LogControllerSpec.AggregateLog(workerId, logicalClock, localSims.map(s => s._2).map(agent => builder.get.strategy.mapper(agent.SimClone())))
                    }

                    sendToRef ! SendTo(workerId, proposeInterval)
                    completedAgents = 0
                    Behaviors.same

                case Stop() =>
                    ctx.log.debug(f"Stop worker ${workerId}")
                    Behaviors.stopped
            }
        }
}