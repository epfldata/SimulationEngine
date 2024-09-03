package simulation.akka.API

import akka.cluster.typed.Cluster
import meta.runtime.Actor
import meta.API.{SimulationDataBuilder, TimeseriesBuilder, SnapshotBuilder}
import com.typesafe.config.ConfigFactory
import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}
import scala.collection.JavaConversions._
import akka.actor.typed.{Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.NoSerializationVerificationNeeded

object AkkaExp {
    sealed trait Command extends NoSerializationVerificationNeeded
    final case class SpawnDriver(totalWorkers: Int, totalTurn: Long, logControllerOn: Boolean) extends Command
    final case class SpawnWorker(workerId: Int, sims: Seq[Actor], totalWorkers: Int, logControllerOn: Boolean) extends Command
    final case class SpawnLogController(totalWorkers: Int) extends Command
    final case class DriverStopped() extends Command
    final case class WorkerStopped(workerId: Int, sims: Seq[Actor]) extends Command
    final case class LogControllerStopped() extends Command
    
    var cluster: Cluster = null
    var totalWorkers: Int = 0
    val stoppedWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var activeWorkers: ConcurrentLinkedQueue[Int] = new ConcurrentLinkedQueue[Int]()
    var finalAgents: ConcurrentLinkedQueue[Actor] = new ConcurrentLinkedQueue[Actor]()

    def materializedMachine(mid: Int, totalTurn: Long, totalWorkers: Int, builder: SimulationDataBuilder, actors: IndexedSeq[Actor]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            this.totalWorkers = totalWorkers
            val totalActors = actors.size
            val workersPerMachine: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.workers-per-machine").render().toInt
            // Machine id is 0-indexed
            var actorsPerWorker = totalActors/workersPerMachine

            stoppedWorkers.clear()
            activeWorkers.clear()
            finalAgents.clear()
            
            ctx.log.debug(f"Creating machine ${mid}!")
            for (i <- Range(0, workersPerMachine)) {
                val wid = mid * workersPerMachine + i
                val containedAgents = if (wid == totalWorkers-1){
                    actors.slice(i*actorsPerWorker, totalActors)    
                } else {
                    actors.slice(i*actorsPerWorker, (i+1)*actorsPerWorker)  
                }
                ctx.self ! SpawnWorker(wid, containedAgents, totalWorkers, false)
            } 
            // simulateUntil supports only Standalone mode for now
            waitTillFinish(Vector.empty, builder, None)
        }

    def apply(totalTurn: Long, totalWorkers: Int, builder: SimulationDataBuilder, actors: IndexedSeq[Actor], haltCond: Option[Iterable[Iterable[Serializable]] => Boolean]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            this.totalWorkers = totalWorkers
            val roles: Set[String] = cluster.selfMember.getRoles.toSet
            val totalActors = actors.size
            var actorsPerWorker = totalActors/totalWorkers

            stoppedWorkers.clear()
            activeWorkers.clear()
            finalAgents.clear()
            
            ctx.log.debug(f"${actorsPerWorker} actors per worker")

            val logControllerOn = haltCond.isDefined || builder.isInstanceOf[TimeseriesBuilder]
            
            // Worker id is 0-indexed
            if (roles.exists(p => p.startsWith("Worker"))) {
                ctx.log.debug(f"Creating a worker!")
                val wid = roles.head.split("-").last.toInt
                val containedAgents = if (wid == totalWorkers-1){
                    actors.slice(wid*actorsPerWorker, totalActors)    
                } else {
                    actors.slice(wid*actorsPerWorker, (wid+1)*actorsPerWorker)  
                }
                ctx.self ! SpawnWorker(wid, containedAgents, totalWorkers, logControllerOn)
            } 

            // Machine id is 0-indexed
            if (roles.exists(p => p.startsWith("Machine"))) {
                val mid = roles.head.split("-").last.toInt
                ctx.log.debug(f"Creating machine ${mid}!")
                val workersPerMachine: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.workers-per-machine").render().toInt
                for (i <- Range(0, workersPerMachine)) {
                    val wid = mid * workersPerMachine + i
                    val containedAgents = if (wid == totalWorkers-1){
                        actors.slice(wid*actorsPerWorker, totalActors)    
                    } else {
                        actors.slice(wid*actorsPerWorker, (wid+1)*actorsPerWorker)  
                    }
                    ctx.self ! SpawnWorker(wid, containedAgents, totalWorkers, logControllerOn)
                }        
            } 
            
            if (cluster.selfMember.hasRole("Driver")) {
                ctx.log.debug(f"Creating a driver!")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn, logControllerOn)
                // Co-locate the log controller with driver
                if (logControllerOn) {
                    ctx.self ! SpawnLogController(totalWorkers)
                }
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.log.debug(f"Standalone mode")
                ctx.self ! SpawnDriver(totalWorkers, totalTurn, logControllerOn)
                
                if (logControllerOn) {
                    ctx.self ! SpawnLogController(totalWorkers)
                }

                for (i <- Range(0, totalWorkers)){
                    val containedAgents = if (i == totalWorkers-1){
                        actors.slice(i*actorsPerWorker, totalActors)    
                    } else {
                        actors.slice(i*actorsPerWorker, (i+1)*actorsPerWorker)  
                    }
                    ctx.self ! SpawnWorker(i, containedAgents, totalWorkers, logControllerOn)
                }
            }
            waitTillFinish(Vector.empty, builder, haltCond)
        }

    def waitTillFinish(finalAgents: IndexedSeq[Actor], builder: SimulationDataBuilder, haltCond: Option[Iterable[Iterable[Serializable]] => Boolean]): Behavior[Command] = {
        Behaviors.receive { (ctx, message) => 
            message match {
                case SpawnDriver(totalWorkers, totalTurn, logControllerOn) => 
                    val driver = ctx.spawn((new simulation.akka.core.Driver).apply(totalWorkers, totalTurn, logControllerOn), "driver")
                    ctx.watchWith(driver, DriverStopped())
                    Behaviors.same

                case SpawnLogController(totalWorkers) => 
                    val logController = if (haltCond.isDefined) {
                        // ctx.log.info("Conditional termination is defined!")
                        ctx.spawn((new simulation.akka.core.LogController).apply(totalWorkers, haltCond.get, builder.asInstanceOf[TimeseriesBuilder]), "logController")
                    } else {
                        // ctx.log.info("Conditional termination is nto defined!")
                        ctx.spawn((new simulation.akka.core.LogController).apply(totalWorkers, builder.asInstanceOf[TimeseriesBuilder]), "logController")
                    }
                    ctx.watchWith(logController, LogControllerStopped())
                    Behaviors.same

                case SpawnWorker(workerId, agents, totalWorkers, logControllerOn) =>
                    val sim = builder match {
                        case x: TimeseriesBuilder => {
                            ctx.spawn((new simulation.akka.core.Worker).apply(workerId, agents,  totalWorkers, Some(x.asInstanceOf[TimeseriesBuilder])), f"worker${workerId}")
                        }
                        case _: SnapshotBuilder => {
                            ctx.spawn((new simulation.akka.core.Worker).apply(workerId, agents,  totalWorkers, None), f"worker${workerId}")
                        }
                    }
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
                
                case LogControllerStopped() =>
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
                                builder.addAgents(finalAgents ++ agents)
                                Behaviors.stopped {() =>
                                    ctx.system.terminate()
                                }
                            } else {
                                waitTillFinish(finalAgents ++ agents, builder, haltCond)
                            }
                        } else {
                            if (activeWorkers.toSet.diff(stoppedWorkers.toSet).isEmpty){
                                Behaviors.stopped {() =>
                                    ctx.system.terminate()
                                }
                            } else {
                                waitTillFinish(finalAgents, builder, haltCond)
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