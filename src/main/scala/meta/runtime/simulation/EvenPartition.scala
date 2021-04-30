package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time
import meta.runtime.Actor.AgentId
import scala.collection.immutable.ListMap

object EvenPartition {
  def apply(s: Simulation): Simulation = {
    s.config match {
      case _: MergeSimulationConfig => {
        val config = s.config.asInstanceOf[MergeSimulationConfig]
        val origInit = s.init
        s.init = () => {
            origInit()
            val actorMap: Map[AgentId, Actor] = newActors.map(x => (x.id, x)).toMap
            if (config.runtimeMerging) {
              val containerAgents = util.groupAgents(RandomCluster.cluster(newActors.map(_.id).toList, config.availableHardware), actorMap)
              newActors.clear()
              newActors ++= containerAgents
            }
        }
        s
      }
      case _ => throw new Exception("Invalid configuration file for merging!")
    }
  }
}