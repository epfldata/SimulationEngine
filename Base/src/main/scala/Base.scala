package simulation.base

import scala.collection.mutable.{Buffer, ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime._
import meta.API._
import collection.JavaConverters._

class Base(var actors: List[Actor], val totalTurn: Int) {

    var currentTurn: Int = 0
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
      while (currentTurn < totalTurn) {
        util.displayTime(currentTurn)
        actors.filterNot(_.deleted).foreach(a => {
          if (a.run() > currentTurn){
            currentTurn = a.time
          }
          a.sendMessages.foreach(i => {
            collectedMessages.getOrElseUpdate(i._1, Buffer[Message]())++=i._2
          })
        })
        collect()
        actors.filterNot(_.deleted).foreach(a => {
          a.receivedMessages.addAll(a.proxyIds.flatMap(id => collectedMessages.getOrElse(id, Buffer())).asJava)
        })
        collectedMessages.clear()
      }
      SimulationSnapshot(actors, collectedMessages.flatMap(i => i._2).toList)
    }
}