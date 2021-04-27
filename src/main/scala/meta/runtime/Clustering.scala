package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

trait ClusteringAlgorithm {
    def cluster(inputAgents: List[AgentId], targetGroup: Int): List[List[AgentId]]
}

// Group the agents randomly into target number of groups
object RandomCluster extends ClusteringAlgorithm {
    def cluster(inputAgents: List[AgentId], targetGroup: Int) = {
        var clusterSize: Int = inputAgents.size / targetGroup
        if (inputAgents.size % targetGroup != 0) {
            clusterSize += 1
        }
        inputAgents.sliding(clusterSize, clusterSize).toList
    }
}