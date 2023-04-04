package meta.API

import meta.runtime.{Actor, Message}

/**
  * Capture the state of the simulation.
  * @param actors the Sims at the current state
  * @param messages the list of in-transit messages
  */
case class SimulationSnapshot(val sims: Traversable[Actor], val messages: Traversable[Message]=List()){
}
