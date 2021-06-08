package meta.runtime
package simulation

import scala.collection.mutable.ListBuffer

trait Simulation extends Serializable {

  var currentTurn: Int = 0

  var actors: List[Actor]
  val totalTurn: Int

  val events: ListBuffer[() => Unit]

  def init: ()=> Unit = () => {
    SimRuntime.initLabelVals()
  }

  // discover the newly generated agents
  def collect: ()=> Unit = () => {
    actors = SimRuntime.newActors.toList ::: actors
    SimRuntime.newActors.clear()
  }

  // go to the next state of the Sim
  def proceed: ()=> Unit = () => {
    SimRuntime.proceedGroups()
    currentTurn += 1

    // currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  // entry point of the simulation
  def run(): List[Actor] = {
    init()

    while (currentTurn < totalTurn) {
      util.bench {
        events.foreach(_())
      }
    }

    actors
  }
}
