package meta.runtime
package simulation

import scala.collection.mutable.ListBuffer

trait Simulation extends Serializable {

  val config: SimulationConfig
  var currentTurn: Int = 0
  var currentTime: Double = 0

  val events: ListBuffer[() => Unit]

  var init: ()=> Unit

  // discover the newly generated agents
  var collect: ()=> Unit

  // go to the next state of the Sim
  var proceed: ()=> Unit

  var takeSnapshot: ()=> SimulationSnapshot

  // entry point of the simulation
  var run: () => SimulationSnapshot = () => {
    init()

    while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
      util.bench {
        events.foreach(_())
      }
    }
    takeSnapshot()
  }
}
