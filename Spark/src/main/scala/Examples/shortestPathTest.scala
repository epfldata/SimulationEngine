package simulation.spark
package examples

object shortestPathTest {
    def main(args: Array[String]): Unit = {
        val totalVertices: Int = 50
        val totalRounds: Int = 50

        val agents = generated.core.test.shortestPath.InitData()
        val snapshot1 = API.Simulate(agents, totalRounds)
        // API.Simulate.log.timeseries.foreach(i => println(i._1, i._2.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)))
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
    }
}