package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._

object EvenPartition {
  def apply(s: Simulation): Simulation = {
    s.config match {
      case _: MergeSimulationConfig => {
        val config = s.config.asInstanceOf[MergeSimulationConfig]
        val origInit = s.init
        s.init = () => {
            origInit()
            if (config.runtimeMerging) {
              val totalAgents = newActors.size
              var clusterSize: Int = totalAgents / config.availableHardware
              if (totalAgents % config.availableHardware != 0) {
                clusterSize += 1
              }

              val containers = newActors.sliding(clusterSize, clusterSize).map(x => {
                val c1 = new Container()
                c1.initAddAgents(x)
                c1
              }).toList

              newActors.clear()
              newActors ++= containers
            }
        }
        s
      }
      case _ => throw new Exception("Invalid configuration file for merging!")
    }
  }
}