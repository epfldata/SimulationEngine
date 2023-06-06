package cloudcity.lib
package Graph

import meta.runtime.Actor

// Define a rewrite rule that lowers Array2D[Vertex[V]] to Array2D[V]
trait Topo[V] {
    def toAgent(): Actor
    val vertexUpdateRule: (V, Iterable[V]) => V
    // vector size
    val cols: Int
}

object Topo {
    type VertexId = Int

    def toAgentId(vid: VertexId): meta.runtime.Actor.AgentId = {
        vid.toLong
    }
}