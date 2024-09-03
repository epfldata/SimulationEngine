package simulation.base.API

import scala.collection.mutable.{Buffer, Map => MutMap}
import meta.runtime.{SimRuntime, Actor, Message}
import meta.API._
import scala.util.Random

object Simulate {
  val r_seed = new Random(1000)

  // Default: anonymous simulations that returns snapshot
  def apply(agents: IndexedSeq[Actor], totalRound: Long)(implicit strategy: DeforestationStrategy): SimulationData = {
    new Simulate(agents, totalRound, Map("name" -> f"Simulation_${r_seed.nextInt()}", "data" -> "snapshot"), None).run(strategy)
  }

  // SimulateUntil
  // todo
  def withUntilCondition(agents: IndexedSeq[Actor], totalRound: Long, cond: Iterable[Iterable[Serializable]] => Boolean)(implicit strategy: DeforestationStrategy): SimulationData = {
    new Simulate(agents, totalRound, Map("name" -> f"Simulation_${r_seed.nextInt()}", "data" -> "timeseries"), Some(cond)).run(strategy)
  }
}

class Simulate(agents: IndexedSeq[Actor], totalRound: Long, conf: Map[String, Any], cond: Option[Iterable[Iterable[Serializable]] => Boolean] = None) {
    def run(strategy: DeforestationStrategy): SimulationData = {

      require(conf.isDefinedAt("name"))   // name of the actor system, to allow concurrent simulations
      require(conf.isDefinedAt("data"))   // timeseries or snapshot

      val name: String = conf("name").asInstanceOf[String]
      val dataConf: String = conf("data").asInstanceOf[String]

      val builder: SimulationDataBuilder = if (dataConf == "timeseries") {
          new TimeseriesBuilder(strategy)
      } else {
          new SnapshotBuilder()
      }

      var currentRound: Long = 0
      var elapsedRound: Int = 0
      var collectedMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()
      var collectedSerializedMessages: MutMap[Long, Buffer[Array[Byte]]] = MutMap[Long, Buffer[Array[Byte]]]()
      var actors: IndexedSeq[Actor] = agents

      val initial: Long = System.currentTimeMillis()
      var end: Long = initial

      while (currentRound < totalRound) {
        val start: Long = end
        // Add newly generated agents
        if (!SimRuntime.newActors.isEmpty) {
          actors = actors ++ SimRuntime.newActors
          SimRuntime.newActors.clear()
        }

        elapsedRound = actors.filterNot(_.deleted).map(a => {
          a.time += elapsedRound
          var proposed = a.run()
          a.sendMessages.foreach(i => {
            collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
            i._2.clear()
          })
          a.sendSerializedMessages.foreach(i => {
            collectedSerializedMessages.getOrElseUpdate(i._1, Buffer[Array[Byte]]())++=i._2
            i._2.clear()
          })
          proposed
        }).min

        builder match {
          case x: TimeseriesBuilder => 
              builder.addTimeseries(Vector(strategy.transformer(actors.map(a => strategy.mapper(a.SimClone())))))
          case _ => 
        }

        actors.filterNot(_.deleted).foreach(a => {
          a.receivedMessages ++= collectedMessages.getOrElse(a.id, Buffer())
          a.receivedSerializedMessages ++= collectedSerializedMessages.getOrElse(a.id, Buffer())
        })

        currentRound += elapsedRound
        collectedMessages.clear()
        collectedSerializedMessages.clear()
        end = System.currentTimeMillis()
        println(f"Round ${currentRound} takes ${end-start} ms")
      }
      
      if (totalRound >= 1) {
        println(f"Average ${(end - initial)/totalRound} ms")
      } else {
        println(f"Average ${end - initial} ms")
      }

      builder match {
        case x: SnapshotBuilder => 
          builder.addAgents(actors)
          builder.addMessages(collectedMessages.flatMap(i => i._2).toVector)
        case _ => 
      }

      // SimulationSnapshot(actors, collectedMessages.flatMap(i => i._2).toList)
      builder.build()
    }
}
