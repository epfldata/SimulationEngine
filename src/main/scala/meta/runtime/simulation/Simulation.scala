package meta.runtime
package simulation

trait Simulation {

  // return a list of events
  def init(): List[()=> Unit]

  // discover the newly generated agents
  def collect(): Unit

  // go to the next state of the Sim
  def proceed(): Unit

  // user-defined events
  def scheduleEvents(): List[()=> Unit]

  // entry point of the simulation
  def run(c: SimulationConfig): SimulationSnapshot
}
