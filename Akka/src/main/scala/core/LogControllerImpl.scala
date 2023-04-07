package simulation.akka.core

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import akka.actor.typed.receptionist.{Receptionist}
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import DriverSpec.{LogControllerFinishedServiceKey, LogControllerFinished}

class LogController {
    import LogControllerSpec._
    
    private var totalWorkers: Int = 0
    // partially aggregated results
    val timeseries = new ConcurrentHashMap[Int, ConcurrentHashMap[Int, Iterable[Serializable]]]()
    // apply reducer and drop entries that have been materialized from  
    val reducedTimeseries = new ConcurrentHashMap[Int, Iterable[Serializable]]()

    var firstReceivedStop: Long = 0
    val timeout: Long = 1000    // ms
    var notifyDriver: ActorRef[LogControllerFinished] = null

    def apply(workers: Int): Behavior[LogControllerEvent] = Behaviors.setup {ctx =>
        totalWorkers = workers
        // Let workers and driver discover the log controller
        ctx.system.receptionist ! Receptionist.Register(LoggerAggregateServiceKey, ctx.self)
        ctx.system.receptionist ! Receptionist.Register(LoggerStopServiceKey, ctx.self)

        val workerSub = ctx.messageAdapter[Receptionist.Listing] {
            case LogControllerFinishedServiceKey.Listing(driver) =>
                if (driver.size == 1){
                    notifyDriver = driver.head
                }
                RegisteredDriverNotifier()
        }  

        ctx.system.receptionist ! Receptionist.Subscribe(LogControllerFinishedServiceKey, workerSub)
        logController()
    }

    def logController(): Behavior[LogControllerEvent] = 
        Behaviors.receive[LogControllerEvent] { (ctx, message) => 
            message match { 
                case RegisteredDriverNotifier() => 
                    logController()

                case AggregateLog(wid, time, agents) =>
                    timeseries.computeIfAbsent(time, x => {
                        new ConcurrentHashMap[Int, Iterable[Serializable]]
                    }).put(wid, agents)
                    if (timeseries.get(time).size == totalWorkers) {
                        reducedTimeseries.computeIfAbsent(time, x => {
                            simulation.akka.API.OptimizationConfig.timeseriesSchema.reducer(timeseries.remove(time).toSeq.map(_._2))
                        })
                    }
                    // println("Aggregate log received from worker " + wid)
                    Behaviors.same
                    
                case Stop(time) =>
                    if (firstReceivedStop == 0) {
                        firstReceivedStop = System.currentTimeMillis()
                    }
                    // wait up to $timeout$ ms for the rest of log to arrive from workers
                    if ((reducedTimeseries.containsKey(time)) || ((System.currentTimeMillis() - firstReceivedStop) > timeout)) {
                        simulation.akka.API.Simulate.timeseries = reducedTimeseries.toSeq.sortBy(_._1).map(_._2)
                        notifyDriver ! LogControllerFinished()
                        Behaviors.stopped {() => 
                            ctx.log.info("Time series has " + timeseries.size + " entries")
                            ctx.log.info(f"Log controller received terminate signal")
                        }
                    }
                    ctx.self ! Stop(time)
                    logController()
            }
        }
}
