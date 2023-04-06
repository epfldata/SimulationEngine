package example
package gameOfLife

import scala.io.Source

// This MainInit demonstrates how to use an external edge file as the social graph of a simulation
object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(edgeFilePath: String): List[Actor] = {

            val source = Source.fromFile(edgeFilePath)
            var edges: Map[Long, List[Long]] = Map[Long, List[Long]]()
            for (line <- source.getLines()) {
                val fields = line.split(" ")
                val srcId: Long = fields(0).toLong
                val dstId: Long = fields(1).toLong
                edges = edges + (srcId -> (dstId :: edges.getOrElse(srcId, List())))
            }
            source.close()

            edges.map(i => {
                val cell = if (Random.nextBoolean) {
                    new Cell(1)
                } else {
                    new Cell(1)
                }
                cell.id = i._1
                cell.connectedAgentIds = i._2
                cell
            }).toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}