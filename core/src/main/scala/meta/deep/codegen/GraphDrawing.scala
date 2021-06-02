package meta.deep.codegen

import java.io.File

import guru.nidi.graphviz.attribute.{Color, Label, RankDir}
import guru.nidi.graphviz.engine.{Format, Graphviz}
import guru.nidi.graphviz.model.{Factory, Graph}
import meta.deep.member.EdgeInfo

import scala.collection.mutable.ListBuffer

object GraphDrawing {

  /**
    * This functions draws the graph of the connected edges
    * Saves the file in a debug directory
    *
    * @param graph graph which should be drawn
    * @param name  some name to make file name unique
    */
  def drawGraph(graph: ListBuffer[EdgeInfo], name: String = ""): Unit = {
    var g: Graph = Factory
      .graph("ExecutionGraph")
      .directed()
      .graphAttr()
      .`with`(RankDir.LEFT_TO_RIGHT)

    graph
      .groupBy(_.from.getId)
      .foreach(y => {
        // It is required to create each node only once, otherwise some links get lost
        var nStart = Factory.node(y._1)
        y._2.foreach(x => {
          if (x.waitEdge) {
            nStart = nStart.link(
              Factory
                .to(Factory.node(x.to.getId))
                .`with`(Color.RED)
                .`with`(Label.of(x.label)))
          } else if (x.isMethod) {
            nStart = nStart.link(
              Factory
                .to(Factory.node(x.to.getId))
                .`with`(Color.BLUE)
                .`with`(Label.of(x.label)))
          } else {
            nStart = nStart.link(
              Factory
                .to(Factory.node(x.to.getId))
                .`with`(Color.BLACK)
                .`with`(Label.of(x.label)))
          }
        })
        g = g.`with`(nStart)
      })

    val gviz = Graphviz.fromGraph(g)

    gviz.render(Format.PNG).toFile(new File("debug/graph_" + name + ".png"))
  }

  /**
    * This draws a graph for the mergeInfo node introduced in actorMerge.
    *
    * @param graph graph which should be drawn
    * @param name  some name to make file name unique
    */
  def drawMergeGraph(graph: ListBuffer[MergeInfo], name: String = ""): Unit = {
    var g: Graph = Factory
      .graph("ExecutionGraph")
      .directed()
      .graphAttr()
      .`with`(RankDir.LEFT_TO_RIGHT)

    graph
      .groupBy(_.from.getId)
      .foreach(y => {
        // It is required to create each node only once, otherwise some links get lost
        var nStart = Factory.node(y._1)
        y._2.foreach(x => {
          nStart = nStart.link(
            Factory
              .to(Factory.node(x.to.getId))
              .`with`(Color.BLACK)
              .`with`(Label.of(
                x.graph1._1 + "->" + x.graph1._2 + "|" + x.graph2._1 + "->" + x.graph2._2)))
        })
        g = g.`with`(nStart)
      })

    val gviz = Graphviz.fromGraph(g)

    gviz.render(Format.PNG).toFile(new File("debug/graph_" + name + ".png"))
  }

}
