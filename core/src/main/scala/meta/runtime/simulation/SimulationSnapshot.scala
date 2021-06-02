package meta.runtime
package simulation

/**
  * Capture the state of the simulation.
  * run() returns a snapshot object, where milliTime records the duration of the run
  * @param actors the Sims at the current state
  * @param currentTurn the turn when the snapshot is taken
  */
case class SimulationSnapshot(var actors: List[Actor] = List(),
                              var currentTurn: Int = 0)

