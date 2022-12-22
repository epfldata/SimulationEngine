package simulation.akka.API

import com.typesafe.config.ConfigFactory
import meta.API.SimulationSnapshot
import meta.runtime.{Actor, Message}
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.actor.typed.ActorSystem

object Simulate {
    var log: Log[_, _] = new Log[Actor, Iterable[Actor]]((a: Actor) => a, (b: Iterable[Actor]) => b)

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

        initialize()    

        val config = ConfigFactory.parseString(s"""
            akka.remote.artery.canonical.port=$port
            akka.cluster.roles = [$role]
            """).withFallback(ConfigFactory.load("application"))
        // If there are more workers than agents, then set the worker number to the same as agents
        val workersPerMachine: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.workers-per-machine").render().toInt
        val totalMachines: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.total-machines").render().toInt
        var totalWorkers = workersPerMachine * totalMachines
        println(f"${totalMachines} total machines, ${totalWorkers} total workers, and ${actors.size} actors")
        // well-formedness check
        val machinePrefix = "Machine-"
        val workerPrefix = "Worker-"
        try {
            role match {
                case "Standalone" => println(f"Simulation starts on a single server!")
                case s if s.startsWith(machinePrefix) && s.stripPrefix(machinePrefix).toInt < totalMachines => println(f"Starts ${s}!")
                case s if s.startsWith(workerPrefix) && s.stripPrefix(workerPrefix).toInt < totalWorkers => println(f"Starts ${s}!")
                case _ => throw new Exception("Invalid role!")
            }
        } catch {
            case e: Exception => throw new Exception("Available roles are Standalone, Machine-id, or Worker-id. Replacing id with 0-based int (less than total machines or workers)")
        }
        
        if (totalWorkers > actors.size){
            println(f"Found more workers than agents! Set total workers from ${totalWorkers} to ${actors.size}")
            totalWorkers = actors.size
        }

        val actorSystem = ActorSystem(AkkaExp(totalTurn, totalWorkers, actors), "SimsCluster", config)
        Await.ready(actorSystem.whenTerminated, 10.days)
        println("Simulation ends!")
        // Actor.reset 
        SimulationSnapshot(stoppedAgents, lastWords)
    }
}
