package simulation.akka.API

import com.typesafe.config.ConfigFactory
import meta.API.{DeforestationStrategy, SimulationData, SimulationDataBuilder, SnapshotBuilder, TimeseriesBuilder}
import meta.runtime.{Actor, Message}
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.actor.typed.ActorSystem

object Simulate {
    def apply(actors: IndexedSeq[Actor], totalTurn: Long, conf: Map[String, Any], cond: Option[Iterable[Iterable[Serializable]] => Boolean] = None)(implicit strategy: DeforestationStrategy): SimulationData = {

        require(conf.isDefinedAt("role"))   // Standalone, Driver, Machine-$id
        require(conf.isDefinedAt("port"))   // network port
        require(conf.isDefinedAt("name"))   // name of the actor system, to allow concurrent simulations
        require(conf.isDefinedAt("data"))   // timeseries or snapshot

        val role: String = conf("role").asInstanceOf[String]
        val port: Int = conf("port").asInstanceOf[Int]
        val name: String = conf("name").asInstanceOf[String]
        val dataConf: String = conf("data").asInstanceOf[String]

        val workersPerMachine: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.workers-per-machine").render().toInt
        val totalMachines: Int = ConfigFactory.load("driver-worker").getValue("driver-worker.total-machines").render().toInt
        var totalWorkers = workersPerMachine * totalMachines
        
        println(f"${totalMachines} total machines, ${totalWorkers} total workers, and ${actors.size} actors")
        
        if (totalWorkers > actors.size){
            println(f"Found more workers than agents! Set total workers from ${totalWorkers} to ${actors.size}")
            totalWorkers = actors.size
        }

        val machinePrefix = "Machine-" 
        val builder: SimulationDataBuilder = if (dataConf == "timeseries") {
            new TimeseriesBuilder(strategy)
        } else {
            new SnapshotBuilder()
        }

        val ip: String = conf.getOrElse("ip", "localhost").asInstanceOf[String]

        val actorSystem = role match {
            case "Standalone" => {
                // local mode
                val config = ConfigFactory.parseString(s"""
                    akka.remote.artery.canonical.port=$port
                    akka.remote.artery.canonical.hostname=localhost
                    akka.cluster.roles = [$role]
                    akka.cluster.seed-nodes = ["akka://$name@localhost:$port"]
                    """).withFallback(ConfigFactory.load("application"))
                ActorSystem(AkkaExp(totalTurn, totalWorkers, builder, actors, cond), name, config)
            }
            case "Driver" => {
                require(conf.isDefinedAt("ip"))
                // By default, driver is also the seed node
                val config = ConfigFactory.parseString(s"""
                    akka.remote.artery.canonical.hostname=$ip
                    akka.remote.artery.canonical.port=$port
                    akka.cluster.roles = [$role]
                    akka.cluster.seed-nodes = ["akka://$name@$ip:$port"]
                    """).withFallback(ConfigFactory.load("application"))
                ActorSystem(AkkaExp(totalTurn, totalWorkers, builder, Vector[Actor](), None), name, config)
            }
            case s if s.startsWith(machinePrefix) => {
                require(conf.isDefinedAt("ip"))
                require(conf.isDefinedAt("seed"))   // ip:port
                val seed: String = conf("seed").asInstanceOf[String]
                val config = ConfigFactory.parseString(s"""
                    akka.remote.artery.canonical.hostname=$ip
                    akka.remote.artery.canonical.port=$port
                    akka.cluster.roles = [$role]
                    akka.cluster.seed-nodes = ["akka://$name@$seed"]
                    """).withFallback(ConfigFactory.load("application"))

                // 0-based
                val mid = s.stripPrefix(machinePrefix).toInt
                assert(mid < totalMachines)
                ActorSystem(AkkaExp.materializedMachine(mid, totalTurn, totalWorkers, builder, actors), name, config)
            }
            case _ => throw new Exception("Invalid role! Supported roles are Standalone, Driver, and Machine-$id (o-based)")
        }
        Await.ready(actorSystem.whenTerminated, 10.days)

        println("Simulation ends!")
        builder.build()
    }
}