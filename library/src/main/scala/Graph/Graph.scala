package lib.Graph

import meta.runtime.Actor
import scala.util.Random
import lib.Grid.Torus2D

class Graph {
    def build[T](nodes: List[T], f: T=>Unit): Unit = {
        nodes.foreach(vertex => f(vertex))
    }
}

object FullyConnectedGraph extends Graph {
    def apply(nodes: List[Actor]) {
        nodes.foreach(vertex => {
            vertex.connectedAgents = nodes.filter(_!=vertex)
        })
    }
}

object ErdosRenyiGraph extends Graph {
    def apply(nodes: List[Actor], edgeProb: Double): Unit = {
        nodes.foreach(vertex => {
            vertex.connectedAgents = nodes.filter(x => {
                x !=vertex && edgeProb>Random.nextDouble()
            })
        })
    }
}

object Torus2DGraph extends Graph {
    def apply(nodes: List[Actor], width: Int, height: Int, radius: Int): Unit = {
        (1 to nodes.length).foreach(i =>
            nodes(i-1).connectedAgents = Torus2D.getNeighborCells(width, height)(i-1, radius).map(j => nodes(j))
        )
    }
}