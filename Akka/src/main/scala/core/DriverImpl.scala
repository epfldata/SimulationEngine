package simulation.akka.core

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._
import scala.concurrent.duration._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout

class Driver {
    import DriverSpec._
    
    private var totalWorkers: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0
    // worker x, a list of workers which x expects messages from
    private var workerReceiveFrom: Map[Int, Set[Int]] = Map[Int, Set[Int]]()
    private var workerIdMap: ConcurrentHashMap[Int, ActorRef[WorkerSpec.ExpectedReceives]] = new ConcurrentHashMap[Int, ActorRef[WorkerSpec.ExpectedReceives]]()
    private var registeredWorkers: AtomicInteger = new AtomicInteger(0)
    private var workersStop: Set[ActorRef[WorkerSpec.Stop]] = Set()
    private var workersStart: Set[ActorRef[WorkerSpec.ExpectedReceives]] = Set()

    var start: Long = 0
    var end: Long = 0

    def apply(workers: Int, maxTurn: Int): Behavior[DriverEvent] = Behaviors.setup {ctx =>
        totalWorkers = workers
        totalTurn = maxTurn
        currentTurn = 0 

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case WorkerSpec.WorkerStopServiceKey.Listing(workers) =>
                ctx.log.debug(f"Worker stop keys registered! Total ${workers.size}")

                if (workers.size == totalWorkers) {
                    ctx.log.debug(f"Recorded all workers that need to be stopped!")
                    workersStop = workers
                } 
                InitializeWorkers()

            case WorkerSpec.WorkerStartServiceKey.Listing(workers) =>
                ctx.log.debug(f"Worker start keys registered! Total ${workers.size}")

                if (workers.size == totalWorkers) {
                    ctx.log.debug(f"Recorded all workers that need to be stopped!")
                    workersStart = workers
                } 
                InitializeWorkers()
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(WorkerSpec.WorkerStartServiceKey, workerSub)
        ctx.system.receptionist ! Receptionist.Subscribe(WorkerSpec.WorkerStopServiceKey, workerSub)
        driver()
    }

    def driver(): Behavior[DriverEvent] = 
        Behaviors.receive[DriverEvent] { (ctx, message) => 
            message match { 
                case InitializeWorkers() =>
                    if (!workersStart.isEmpty && !workersStop.isEmpty){
                        ctx.log.debug("All workers are initialized! Start round.")
                        ctx.self ! RoundStart()
                    } 
                    Behaviors.same

                case RoundStart() => {
                    start = System.currentTimeMillis()
                    ctx.log.debug(f"Driver sends expected receives to all workers!  ${workerReceiveFrom}")
                    ctx.spawnAnonymous(
                        Aggregator[WorkerSpec.SendTo, RoundEnd](
                            sendRequests = { replyTo =>
                                workersStart.map(a => {
                                    a ! WorkerSpec.ExpectedReceives(workerReceiveFrom, replyTo)
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
                    ctx.log.info(f"Round ${currentTurn} takes ${end-start} ms")
                    if (currentTurn >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.debug(f"Simulation completes! Stop the driver")
                            workersStop.foreach(a => a ! WorkerSpec.Stop())
                        }
                    } else {
                        ctx.self ! RoundStart()
                        driver()
                    }
            }
        }
}
