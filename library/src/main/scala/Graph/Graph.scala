package lib.Graph

import meta.runtime.Actor
import scala.util.Random
import lib.Grid.Torus2D

class Graph {
    def build[T](nodes: Iterable[T], f: T=>Unit): Unit = {
        nodes.foreach(n => f(n))
    }
}

object FullyConnectedGraph extends Graph {
    def apply(nodes: Iterable[Actor]): Unit = {
        build[Actor](nodes, n=>n.connectedAgents = nodes.filter(_!=n).toList)
    }
}

object ErdosRenyiGraph extends Graph {
    def apply(nodes: Iterable[Actor], edgeProb: Double): Unit = {
        build[Actor](nodes, n => n.connectedAgents = nodes.filter(x => {
                x !=n && edgeProb>Random.nextDouble()
            }).toList)
    }

    def addIds(nodes: Iterable[Actor], iRange: List[Int], edgeProb: Double): Unit = {
        nodes.foreach(n => {
            n.connectedAgentIds = iRange.filter(x => {
                (x !=n.id) && (edgeProb>Random.nextDouble())
            })
        })
    }
}

object Torus2DGraph extends Graph {
    def apply(nodes: Iterable[Actor], width: Int, height: Int, radius: Int): Unit = {
        val nodesSeq = nodes.toIndexedSeq
        nodes.zipWithIndex.foreach(n => {
            n._1.connectedAgents = Torus2D.getNeighborCells(width, height)(n._2, radius).map(j => nodesSeq(j))
        })
    }
}

object CompleteBipartiteGraph extends Graph {
    // Each vertex in the set nodesA is connected to each vertex in nodesB
    def apply(nodesA: Iterable[Actor], nodesB: Iterable[Actor]): Unit = {
        build[Actor](nodesA, n => n.connectedAgents = nodesB.toList)
    }
}