package meta.API

import meta.runtime.Actor

/**
  * Capture the state of the simulation.
  * @param actors the Sims at the current state
  */
case class SimulationSnapshot(val sims: List[Actor])

