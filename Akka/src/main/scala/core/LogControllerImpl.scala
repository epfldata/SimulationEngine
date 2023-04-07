package simulation.akka.core

import java.util.concurrent.{ConcurrentHashMap}
import akka.actor.typed.receptionist.{Receptionist}
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import DriverSpec.{LogControllerFinished}
import meta.runtime.JsonSerializable

class LogController {
    import LogControllerSpec._
    
    private var totalWorkers: Int = 0
    // partially aggregated results
    val timeseries = new ConcurrentHashMap[Int, ConcurrentHashMap[Int, Iterable[JsonSerializable]]]()
    // apply reducer and drop entries that have been materialized from  
    val reducedTimeseries = new ConcurrentHashMap[Int, Iterable[JsonSerializable]]()

    var firstReceivedStop: Long = 0
    val timeout: Long = 1000    // ms

    def apply(workers: Int): Behavior[LogControllerEvent] = Behaviors.setup {ctx =>
        totalWorkers = workers
        // Let workers and driver discover the log controller
        ctx.system.receptionist ! Receptionist.Register(LoggerAggregateServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(LoggerStopServiceKey, ctx.self) 

        Behaviors.receive[LogControllerEvent] { (ctx, message) => 
            message match { 
                case AggregateLog(wid, time, logPerWorker) =>
                    timeseries.computeIfAbsent(time, x => {
                        new ConcurrentHashMap[Int, Iterable[JsonSerializable]]
                    }).put(wid, logPerWorker)
                    if (timeseries.get(time).size == totalWorkers) {
                        ctx.self ! ReduceLog(time, timeseries.remove(time).toMap)
                    }
                    Behaviors.same

                case ReduceLog(time, logPerRound) => 
                    reducedTimeseries.computeIfAbsent(time, x => {
                        simulation.akka.API.OptimizationConfig.timeseriesSchema.reducer(logPerRound.map(_._2))
                    })
                    Behaviors.same

                case Stop(time, replyTo) =>
                    if (firstReceivedStop == 0) {
                        firstReceivedStop = System.currentTimeMillis()
                    }
                    // wait up to $timeout$ ms for the rest of log to arrive from workers
                    if ((reducedTimeseries.containsKey(time)) || ((System.currentTimeMillis() - firstReceivedStop) > timeout)) {
                        simulation.akka.API.Simulate.timeseries = reducedTimeseries.toSeq.sortBy(_._1).map(_._2)
                        replyTo ! LogControllerFinished()
                        Behaviors.stopped {() => 
                            ctx.log.info("Time series has " + timeseries.size + " entries")
                            ctx.log.info(f"Log controller received terminate signal")
                        }
                    } else {
                        ctx.self ! Stop(time, replyTo)
                        Behaviors.same
                    }
                }
            }
        }
}
