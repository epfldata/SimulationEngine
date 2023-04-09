package cloudcity.lib
package Graph.GenerateGraph

import scala.util.Random
import scala.math.floor

trait GenerateGraph

object ErdosRenyiGraph extends GenerateGraph {
    def apply(totalVertices: Int, edgeProb: Double, startingIndex: Int = 0): Map[Long, Iterable[Long]] = {
        val nodes = Range(startingIndex, totalVertices+startingIndex)
        (0 to totalVertices).map(i => {
            (i.toLong, nodes.filter(n => {
                (n!=i) && edgeProb > Random.nextDouble() 
            }).map(_.toLong))
        }).toMap
    }
}

object SBMGraph extends GenerateGraph {
    def apply(totalVertices: Int, p: Double, q: Double, blocks: Int, startingIndex: Int = 0): Map[Long, Iterable[Long]] = {
        val verticesPerBlock: Int = floor(totalVertices / blocks).toInt
        var graph: Map[Long, Iterable[Long]] = Map[Long, Iterable[Long]]()
        // The edge probability between two vertices in the same block is p
        Range(0, blocks).foreach(i => {
            val offset = startingIndex + verticesPerBlock * i
            if (i == (blocks-1)) {
                graph = graph ++ ErdosRenyiGraph(totalVertices - offset, p, offset)
            } else {
                graph = graph ++ ErdosRenyiGraph(verticesPerBlock, p, offset)
            }
        })
        // The edge probability between two vertices in different blocks is q
        if (q > 0) {
            Range(0, totalVertices).foreach(i => {
                val currentBlock: Int = floor(i / verticesPerBlock).toInt
                val verticesInOtherBlocks = if (currentBlock > 0){
                    Range(0, totalVertices).slice(0, currentBlock*verticesPerBlock) ++ Range(verticesPerBlock*(i+1), totalVertices)
                } else {
                    Range(verticesPerBlock, totalVertices)
                }
                graph = graph + (i.toLong -> (graph(i) ++ verticesInOtherBlocks.filter(i => (q > Random.nextDouble())).map(_.toLong)))
            })
        }
        graph
    }
}

object Torus2DGraph extends GenerateGraph {
    def apply(width: Int, height: Int, startingIndex: Int = 0): Map[Long, Iterable[Long]] = {
        Range(0, width * height).map(index => {
            val x = index % width
            val y = index / width

            val neighbors = for {
                i <- -1 to 1
                j <- -1 to 1
                if !(i == 0 && j == 0)
                    dx = (x + i + width) % width
                    dy = (y + j + height) % height
            } yield dy * width + dx
            (index.toLong + startingIndex, neighbors.map(n => n.toLong + startingIndex))
        }).toMap
    }
}

object BipartiteGraph extends GenerateGraph {
    def apply(set1Size: Int, set2Size: Int, startingIndex: Int = 0): Map[Long, Iterable[Long]] = {        
        (Range(startingIndex, set1Size + startingIndex).map(i => {
            (i.toLong, Range(set1Size + startingIndex, set2Size).map(_.toLong))
        }) ++ Range(startingIndex + set1Size, startingIndex + set1Size + set2Size).map(i => {
            (i.toLong, Range(startingIndex, startingIndex +startingIndex + set1Size).map(_.toLong))
        })).toMap
    }
}