package cloudcity.lib
package Graph

import java.io._
import scala.util.Random

trait WriteGraph {
    var defaultVertexValue: String = ""
    var defaultEdgeValue: String = ""
    def schema(src: Long, neighbors: Iterable[Long]): String = ???
    def write(graph: Map[Long, Iterable[Long]], saveName: String, schema: (Long, Iterable[Long]) => String): Unit = {
        val pw = new PrintWriter(new FileOutputStream(new File(f"$saveName"),false))
        graph.foreach(i => {
            pw.write(schema(i._1, i._2)+"\n")
            pw.flush()
        })
        pw.close()
    }
}

class GraphxFormat extends WriteGraph {
    // [srcId dstId1]
    // [srcId dstId2]
    override def schema(src: Long, neighbors: Iterable[Long]): String = {
        neighbors.map(n => f"$src $n").mkString("\n")
    }
}

class GiraphFormat extends WriteGraph {
    // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
    override def schema(src: Long, neighbors: Iterable[Long]): String = {
        f"[${src},${defaultVertexValue},[${neighbors.map(i => f"[$i,$defaultEdgeValue]").mkString(",")}]]"
    }
}