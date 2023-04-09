package cloudcity.lib
package Graph
package test

import org.scalatest.FlatSpec

class GraphSpec extends FlatSpec {

  "Each cell in a 2D grid" should "have eight neighbors" in {
    val graph1 = GenerateGraph.Torus2DGraph(3, 3)
    graph1.foreach(i => assert(i._2.size == 8))
    assert(graph1.size == 9)

    val graph2 = GenerateGraph.Torus2DGraph(100, 100)
    graph2.foreach(i => assert(i._2.size == 8))
    assert(graph2.size == 10000)
  }
}