package simulation.base

import scala.collection.mutable.{Buffer, ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime._
import meta.API._
import collection.JavaConverters._

class Base(var actors: List[Actor], val totalRound: Int) {

    var currentRound: Int = 0
    var elapsedRound: Int = 0
    var collectedMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()

    // Upon resuming
    actors.foreach(a => 
      a.sendMessages.foreach(i => {
        collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
      }))

    // discover the newly generated agents
    def collect() = {
      actors = SimRuntime.newActors.toList ::: actors
      SimRuntime.newActors.clear()
    }

    def run(): SimulationSnapshot = {
      while (currentRound < totalRound) {
        util.displayTime(currentRound)

        elapsedRound = actors.filterNot(_.deleted).map(a => {
          a.time += elapsedRound
          var proposed = a.run()
          a.sendMessages.foreach(i => {
            collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
          })
          proposed
        }).min
        collect()
        actors.filterNot(_.deleted).foreach(a => {
          a.receivedMessages :::= (a.proxyIds.flatMap(id => collectedMessages.getOrElse(id, Buffer())))
        })
        currentRound += elapsedRound
        collectedMessages.clear()
      }
      SimulationSnapshot(actors, collectedMessages.flatMap(i => i._2).toList)
    }
}