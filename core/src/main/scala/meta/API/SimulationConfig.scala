package meta.API

import meta.runtime.Actor
import meta.runtime.Container

/**
  * Defines the configuration for a simulation run
  * Simulation terminates when totalTurn or totalTime is reached (or both)
  * @param actors the Sims (agents) which are part of the simulation 
  * @param totalTurn defines for how many turns the simulation continues
  * @param isCompiled defines whether to compile or stage the agents
  * @param latencyBound defines a bounded message latency for the model, default to 1
  */
class SimulationConfig(val actors: List[Actor], val totalTurn: Int = 40, val isCompiled: Boolean = true, val latencyBound: Int = 1) {
  // Group agents statically into containers according to the number of partitions                 

  def staticPartition(partitions: Int)(containerOpt: SimContainerOptimization): SimulationConfig = {

        val totalAgents = actors.size
        var clusterSize: Int = totalAgents / partitions

        if (totalAgents % partitions != 0) {
            clusterSize += 1
        }

        val containers: List[Container] = actors.sliding(clusterSize, clusterSize).map(x => {
          newContainer(x)(isCompiled, containerOpt)
        }).toList

        containers.foreach(c => c.setKBound(latencyBound))
        
        new SimulationConfig(containers, totalTurn, isCompiled, latencyBound)
  }

  override def toString(): String = {
    f"Agents:${actors.length}; Turns:${totalTurn}; Bound:${latencyBound}; "
  }
}