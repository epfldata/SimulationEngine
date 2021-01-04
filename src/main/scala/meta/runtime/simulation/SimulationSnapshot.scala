package meta.runtime
package simulation

/**
  * Capture the state of the simulation.
  * run() returns a snapshot object, where milliTime records the duration of the run
  * @param actors the Sims at the current state
  * @param currentTurn the turn when the snapshot is taken
  * @param currentTime the time when the snapshot is taken
  * @param milliTime the execution time in ms that the snapshot covers
  */
case class SimulationSnapshot(var actors: List[Actor] = List(),
                              var currentTurn: Int = 0,
                              var currentTime: Double = 0,
                              val milliTime: Double)

