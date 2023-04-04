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

      while (currentRound < totalRound) {
        // println(util.displayTime(currentRound))
        elapsedRound = actors.filterNot(_.deleted).map(a => {
          a.time += elapsedRound
          var proposed = a.run()
          a.sendMessages.foreach(i => {
            collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
          })
          proposed
        }).min
        
        // Add newly generated agents
        if (!SimRuntime.newActors.isEmpty) {
          actors = SimRuntime.newActors ++ actors
          SimRuntime.newActors.clear()
        }

        actors.filterNot(_.deleted).foreach(a => {
          a.receivedMessages :::= (a.proxyIds.flatMap(id => collectedMessages.getOrElse(id, Buffer())))
        })
        currentRound += elapsedRound
        collectedMessages.clear()
      }
      SimulationSnapshot(actors, collectedMessages.flatMap(i => i._2).toList)
    }
}