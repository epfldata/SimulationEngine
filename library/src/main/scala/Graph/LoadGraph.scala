package cloudcity.lib
package Graph

import scala.io.Source

// GraphX format
object LoadGraph {
    def apply(edgeFilePath: String): Map[Long, Iterable[Long]] = {
            val source = Source.fromFile(edgeFilePath)
            var edges: Map[Long, Array[Long]] = Map[Long, Array[Long]]()
            for (line <- source.getLines()) {
                val fields = line.split(" ")
                val srcId: Long = fields(0).toLong
                val dstId: Long = fields(1).toLong
                edges = edges + (srcId -> (edges.getOrElse(srcId, Array[Long]()) :+ dstId))
            }
            source.close()
            edges.map(i => (i._1, i._2.toIterable))
    }
}