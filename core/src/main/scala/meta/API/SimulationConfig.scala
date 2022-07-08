package meta.API

import meta.runtime.{Actor, Container, Message}

/**
  * Defines the configuration for a simulation run
  * Simulation terminates when totalTurn or totalTime is reached (or both)
  * @param actors the Sims (agents) which are part of the simulation 
  * @param totalTurn defines for how many turns the simulation continues
  * @param isCompiled defines whether to compile or stage the agents
  * @param latencyBound defines a bounded message latency for the model, default to 1
  * @param messages defines a list of initial messages, especially when resuming from a snapshot
  * @param role defines the role of the host machine in a distributed environment in Akka, default to local host
  * @param port defines the port number for the role, default to 0, i.e. randomly assign a port value
  */
class SimulationConfig(val actors: List[Actor], val totalTurn: Int = 40, val isCompiled: Boolean = true, 
  val latencyBound: Int = 1, val messages: List[Message]=List(), val role: String="Standalone", val port: Int=25251) {
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
        
        new SimulationConfig(containers, totalTurn, isCompiled, latencyBound, messages, role, port)
  }

  def copy(actors: List[Actor] = actors, totalTurn: Int = totalTurn, isCompiled: Boolean = isCompiled, 
  latencyBound: Int = latencyBound, messages: List[Message]=messages, role: String=role, port: Int=port): SimulationConfig = {
    new SimulationConfig(actors, totalTurn, isCompiled, latencyBound, messages, role, port)
  }

  override def toString(): String = {
    f"Agents:${actors.length}; Turns:${totalTurn}; Bound:${latencyBound}; "
  }
}

trait DistConfig {
  def getConfig(): SimulationConfig
}

class DistSimulationConfig(actors: List[Actor], totalTurn: Int = 40, val totalMachines: Int=1, val machineSeq: Int = 0, isCompiled: Boolean = true, 
  latencyBound: Int = 1, messages: List[Message]=List(), role: String="Standalone", port: Int=25251) extends SimulationConfig(actors, totalTurn, isCompiled, latencyBound, messages, role, port) with DistConfig {
    def getConfig(): SimulationConfig = {     
      role match {
        case "Standalone" => new SimulationConfig(actors, totalTurn, isCompiled, latencyBound, messages, role, port)
        case _ => {
          val totalAgents = actors.size
          var agentsPerMachine: Int = totalAgents / totalMachines

          if (totalAgents % totalMachines != 0) {
              agentsPerMachine += 1
          }

          val agents: List[Actor] = {
            val start = machineSeq*agentsPerMachine
            if (machineSeq == totalMachines-1) {
              actors.slice(start, totalAgents)
            } else {
              actors.slice(start, start + agentsPerMachine)
            }
          }
          new SimulationConfig(agents, totalTurn, isCompiled, latencyBound, messages, role, port)
        }
    }
  }
} 

class DistSimulationConfigPartialEval(actors: => List[Actor], totalAgents: Int, totalTurn: Int = 40, val totalMachines: Int=1, val machineSeq: Int = 0, isCompiled: Boolean = true, 
  latencyBound: Int = 1, messages: List[Message]=List(), role: String="Standalone", port: Int=25251) extends DistConfig {
    def getConfig(): SimulationConfig = {     
      role match {
        case "Standalone" => new SimulationConfig(actors, totalTurn, isCompiled, latencyBound, messages, role, port)
        case _ => {
          var agentsPerMachine: Int = totalAgents / totalMachines

          if (totalAgents % totalMachines != 0) {
              agentsPerMachine += 1
          }

          val agents: List[Actor] = {
            val start = machineSeq*agentsPerMachine
            if (machineSeq == totalMachines-1) {
              actors.slice(start, totalAgents)
            } else {
              actors.slice(start, start + agentsPerMachine)
            }
          }
          new SimulationConfig(agents, totalTurn, isCompiled, latencyBound, messages, role, port)
        }
    }
  }
} 