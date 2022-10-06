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
        def startup(role: String, port: Int): Unit ={
            val config = ConfigFactory.parseString(s"""
                akka.remote.artery.canonical.port=$port
                akka.cluster.roles = [$role]
                """).withFallback(ConfigFactory.load("application"))
            // If there are more workers than agents, then set the worker number to the same as agents
            val workersPerMachine: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.workers-per-machine").render().toInt
            val totalMachines: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.total-machines").render().toInt
            var totalWorkers = workersPerMachine * totalMachines
            println(f"Total workers are ${totalWorkers} Total actors ${actors.size}")
            if (totalWorkers > actors.size){
                println(f"Detect more workers than agents! Set total workers from ${totalWorkers} to ${actors.size}")
                totalWorkers = actors.size
            }
            val actorSystem = ActorSystem(AkkaExp(totalTurn, totalWorkers, actors), "SimsCluster", config)
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
