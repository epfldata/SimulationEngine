package meta.runtime
package simulation

trait Simulation {

//  var config: SimulationConfig
//
//  def init(): List[()=> Unit]
//
//  def collect(turn: Int): Unit
//
//  def proceed(): Unit
//
//  def scheduleEvents(): List[()=> Unit]

  def run(c: SimulationConfig): SimulationSnapshot
}
