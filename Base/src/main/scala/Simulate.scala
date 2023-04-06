package simulation.base.API

import scala.collection.mutable.{Buffer, Map => MutMap}
import meta.runtime.{SimRuntime, Actor, Message}
import meta.API.{SimulationSnapshot, util}

object Simulate {
    def apply(agents: Traversable[Actor], totalRound: Int): SimulationSnapshot = {
      var currentRound: Int = 0
      var elapsedRound: Int = 0
      var collectedMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()
      var actors: Traversable[Actor] = agents
            // Upon resuming
      actors.foreach(a => 
        a.sendMessages.foreach(i => {
          collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
        }))

      var end: Long = System.currentTimeMillis()
      val initial: Long = end

      while (currentRound < totalRound) {
        val start: Long = end
        // Add newly generated agents
        if (!SimRuntime.newActors.isEmpty) {
          actors = SimRuntime.newActors ++ actors
          SimRuntime.newActors.clear()
        }

        elapsedRound = actors.filterNot(_.deleted).map(a => {
          a.time += elapsedRound
          var proposed = a.run()
          a.sendMessages.foreach(i => {
            collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
          })
          proposed
        }).min

        actors.filterNot(_.deleted).foreach(a => {
          a.receivedMessages :::= (a.proxyIds.flatMap(id => collectedMessages.getOrElse(id, Buffer())))
        })
        currentRound += elapsedRound
        collectedMessages.clear()
        end = System.currentTimeMillis()
        println(f"Round ${currentRound} takes ${end-start} ms")
      }
      println(f"Average ${(end - initial)/totalRound} ms")
      SimulationSnapshot(actors, collectedMessages.flatMap(i => i._2).toList)
    }
}