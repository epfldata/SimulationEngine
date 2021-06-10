package meta.API

import meta.runtime.Actor
import meta.runtime.Container

/**
  * Defines the configuration for a simulation run
  * Simulation terminates when totalTurn or totalTime is reached (or both)
  * @param actors the Sims (agents) which are part of the simulation 
  * @param totalTurn define for how many turns the simulation continues
  */
class SimulationConfig(val actors: List[Actor], val totalTurn: Int = 40, val isCompiled: Boolean = true) {
  // Group agents statically into containers according to the number of partitions                 
  def staticPartition(partitions: Int): SimulationConfig = {
        val totalAgents = actors.size
        var clusterSize: Int = totalAgents / partitions

        if (totalAgents % partitions != 0) {
            clusterSize += 1
        }

        val containers = if (isCompiled){
          actors.sliding(clusterSize, clusterSize).map(x => {
            val c1 = new Container()
            c1.initAddAgents(x, "Compiled")
            c1
          }).toList
        } else {
          actors.sliding(clusterSize, clusterSize).map(x => {
            val c1 = new Container()
            c1.initAddAgents(x, "Staged")
            c1
          }).toList
        }

        new SimulationConfig(containers, totalTurn, isCompiled)
  }
}