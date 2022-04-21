package lib.Graph

import meta.runtime.Actor
import scala.util.Random
import lib.Grid.Torus2D

class Graph {
    def build[T](nodes: List[T], f: T=>Unit): Unit = {
        nodes.foreach(n => f(n))
    }
}

object FullyConnectedGraph extends Graph {
    def apply(nodes: List[Actor]): Unit = {
        build[Actor](nodes, n=>n.connectedAgents = nodes.filter(_!=n))
    }
}

object ErdosRenyiGraph extends Graph {
    def apply(nodes: List[Actor], edgeProb: Double): Unit = {
        build[Actor](nodes, n => n.connectedAgents = nodes.filter(x => {
                x !=n && edgeProb>Random.nextDouble()
            }))
    }
}

object Torus2DGraph extends Graph {
    def apply(nodes: List[Actor], width: Int, height: Int, radius: Int): Unit = {
        (1 to nodes.length).foreach(n =>
            nodes(n-1).connectedAgents = Torus2D.getNeighborCells(width, height)(n-1, radius).map(j => nodes(j))
        )
    }
}

object CompleteBipartiteGraph extends Graph {
    // Each vertex in the set nodesA is connected to each vertex in nodesB
    def apply(nodesA: List[Actor], nodesB: List[Actor]): Unit = {
        build[Actor](nodesA, n => n.connectedAgents = nodesB)
    }
}