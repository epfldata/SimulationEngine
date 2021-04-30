package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time
import meta.runtime.Actor.AgentId
import scala.collection.immutable.ListMap

class EvenPartition(val c: MergeSimulationConfig) extends Base(c) {

  override def init(): Unit = {
    initLabelVals()
    collect()
    if (c.runtimeMerging) {
      fuseAgents(RandomCluster.cluster(actors.keySet.toList, c.availableHardware))
    }
  }

  def fuseAgents(candidates: List[List[AgentId]]): Unit = {
    meta.Util.debug(s"Fuse agents: ${candidates}")

    candidates.foreach(x => {
      val c1 = new Container()
      c1.addAgents(x.map(aId => actors(aId)))
      // remove the merged agents from runtime
      x.foreach(aId => actors.remove(aId))
      // add the containers to runtime
      actors += (c1.id -> c1)
    })
  }
}