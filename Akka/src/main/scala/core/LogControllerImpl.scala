package simulation.akka.core

import meta.API.TimeseriesBuilder
import java.util.concurrent.{ConcurrentHashMap}
import akka.actor.typed.receptionist.{Receptionist}
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import DriverSpec.{LogControllerFinished, InterruptDriverServiceKey, InterruptDriver}

import scala.collection.mutable.ListBuffer

class LogController {
    import LogControllerSpec._
    
    private var totalWorkers: Int = 0
    // partially aggregated results
    val indexedTimeseries = new ConcurrentHashMap[Long, ConcurrentHashMap[Long, Iterable[Serializable]]]()
    // apply reducer and drop entries that have been materialized from  
    val reducedTimeseries = new ConcurrentHashMap[Long, Iterable[Serializable]]()

    val timeseries: ListBuffer[Iterable[Serializable]] = new ListBuffer[Iterable[Serializable]]()

    var firstReceivedStop: Long = 0
    val timeout: Long = 1000    // ms
    
    var interruptDriver: Set[ActorRef[DriverSpec.InterruptDriver]] = Set()
    var haltCond: Iterable[Iterable[Serializable]] => Boolean = null

    def apply(workers: Int, builder: TimeseriesBuilder): Behavior[LogControllerEvent] = Behaviors.setup {ctx =>
        totalWorkers = workers
        // Let workers and driver discover the log controller
        ctx.system.receptionist ! Receptionist.Register(LoggerAggregateServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(LoggerStopServiceKey, ctx.self) 
        logController(builder)
    }

    def apply(workers: Int, haltCond: Iterable[Iterable[Serializable]] => Boolean, builder: TimeseriesBuilder): Behavior[LogControllerEvent] = Behaviors.setup {ctx =>
        totalWorkers = workers
        this.haltCond = haltCond
        // Let workers and driver discover the log controller
        ctx.system.receptionist ! Receptionist.Register(LoggerAggregateServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(LoggerStopServiceKey, ctx.self) 

        val logControllerSub = ctx.messageAdapter[Receptionist.Listing] {
            case DriverSpec.InterruptDriverServiceKey.Listing(interruptRef) =>
                if (interruptRef.size > 0) {
                    ctx.log.debug(f"Register driver interrupt!")
                    interruptDriver = interruptRef
                } 
                RegisterDriverInterrupt()
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(DriverSpec.InterruptDriverServiceKey, logControllerSub)
        logControllerWithInterrupt(builder)
    }

    def logController(builder: TimeseriesBuilder): Behavior[LogControllerEvent] = 
        Behaviors.receive[LogControllerEvent] { (ctx, message) => 
            message match { 
                case AggregateLog(wid, time, agents) =>
                    indexedTimeseries.computeIfAbsent(time, x => {
                        new ConcurrentHashMap[Long, Iterable[Serializable]]
                    }).put(wid, agents)
                    if (indexedTimeseries.get(time).size == totalWorkers) {
                        reducedTimeseries.computeIfAbsent(time, x => {
                            builder.strategy.reducer(indexedTimeseries.remove(time).toSeq.map(_._2))
                        })
                    }
                    Behaviors.same
                    
                case Stop(time, replyTo) =>
                    if (firstReceivedStop == 0) {
                        firstReceivedStop = System.currentTimeMillis()
                    }
                    // wait up to $timeout$ ms for the rest of log to arrive from workers
                    if ((reducedTimeseries.containsKey(time)) || ((System.currentTimeMillis() - firstReceivedStop) > timeout)) {
                        builder.addTimeseries(reducedTimeseries.toSeq.sortBy(_._1).map(_._2))
                        replyTo ! LogControllerFinished()
                        Behaviors.stopped {() => 
                            ctx.log.info("Time series has " + indexedTimeseries.size + " entries")
                            ctx.log.info(f"Log controller received terminate signal")
                        }
                    }
                    ctx.self ! Stop(time, replyTo)
                    logController(builder)
            }
        }

    def logControllerWithInterrupt(builder: TimeseriesBuilder): Behavior[LogControllerEvent] = 
        Behaviors.receive[LogControllerEvent] { (ctx, message) => 
            message match { 
                case RegisterDriverInterrupt() =>
                    Behaviors.same
                
                case AggregateLog(wid, time, agents) =>
                    indexedTimeseries.computeIfAbsent(time, x => {
                        new ConcurrentHashMap[Long, Iterable[Serializable]]
                    }).put(wid, agents)
                    if (indexedTimeseries.get(time).size == totalWorkers) {
                        reducedTimeseries.computeIfAbsent(time, x => {
                            val agents = (indexedTimeseries.remove(time).toSeq.map(_._2)).flatten
                            timeseries.append(agents)
                            if (haltCond(timeseries)) {
                                interruptDriver.foreach(i => {
                                    i ! InterruptDriver(Vector(-1))
                                })
                            } 
                            Iterable()
                        })
                    }
                    Behaviors.same
                    
                case Stop(time, replyTo) =>
                    if (firstReceivedStop == 0) {
                        firstReceivedStop = System.currentTimeMillis()
                    }
                    // wait up to $timeout$ ms for the rest of log to arrive from workers
                    if ((reducedTimeseries.containsKey(time)) || ((System.currentTimeMillis() - firstReceivedStop) > timeout)) {
                        builder.addTimeseries(timeseries)
                        replyTo ! LogControllerFinished()
                        Behaviors.stopped {() => 
                            ctx.log.info("Time series has " + indexedTimeseries.size + " entries")
                            ctx.log.info(f"Log controller received terminate signal")
                        }
                    }
                    ctx.self ! Stop(time, replyTo)
                    logController(builder)
            }
        }
}
