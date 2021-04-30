package meta.runtime
package simulation

import Actor.AgentId

/**
  * Defines the configuration for a simulation run
  * Simulation terminates when totalTurn or totalTime is reached (or both)
  * @param actors the Sims (agents) to run simulation on. Default $object program$ InitData.initActors. Please reference scripts in /generated/src/test/
  * @param startTurn define what the first turn of the simulation is
  * @param totalTurn define for how many turns the simulation continues
  * @param startTime define the initial time of the simulation
  * @param totalTime define the total time of the simulation
  * @param methodInlining indicates whether to inline methods from stateless agents
  * @param runtimeMerging indicates whether to group agents into containers at runtime
  */
class SimulationConfig(var actors: List[Actor] = List(),
                      var startTurn: Int = 0,
                      var totalTurn: Int = 40,
                      var startTime: Double = 0,
                      var totalTime: Double = 10)

class MergeSimulationConfig(var availableHardware: Int,
                          var runtimeMerging: Boolean = true)
                          extends SimulationConfig