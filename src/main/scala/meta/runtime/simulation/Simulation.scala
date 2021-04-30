package meta.runtime
package simulation

import scala.collection.mutable.ListBuffer

trait Simulation extends Serializable {

  protected val config: SimulationConfig

  protected val events: ListBuffer[() => Unit]

  // discover the newly generated agents
  protected def collect(): Unit

  // go to the next state of the Sim
  protected def proceed(): Unit

  // entry point of the simulation
  protected def run(): SimulationSnapshot
}
