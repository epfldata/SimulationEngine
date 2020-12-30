package meta.runtime
package simulation

case class SimulationSnapshot(var actors: List[Actor] = List(),
                              var currentTurn: Int = 0,
                              var currentTime: Double = 0,
                              val milliTime: Double)

