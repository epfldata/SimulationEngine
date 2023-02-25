package simulation.spark
package examples

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.rdd.RDD

import scala.util.Random

object GameOfLifeGraphx { 
  def main(args: Array[String]): Unit = {
    val cores = args(0)
    val edgeListFile: String = args(1)

    // Creates a SparkSession.
    val spark = new SparkConf().setMaster(f"local[${cores}]")
    .setAppName("GameOfLife")
    .set("spark.driver.memory", "50g")
    .set("spark.driver.maxResultSize", "10g")
    .set("spark.executor.memory", "5g")
    .set("spark.executor.cores", "48")
    .set("spark.default.parallelism", "96")
    .set("spark.hadoop.dfs.replication", "1")
    // .set("spark.serializer", "akka.serialization.jackson.JacksonJsonSerializer")
    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(spark)
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")

    // $example on$
    // A graph with edge attributes containing distances
    val graph: Graph[Int, Int] = GraphLoader.edgeListFile(sc, edgeListFile)
    // Initialize the graph such that each vertex is either alive or dead.
    val initialGraph = graph.mapVertices((id, _) =>
        if (Random.nextBoolean()){1} else {0})
    val gol = initialGraph.pregel(0, maxIterations = 200)(
      (id, alive, aliveNeighbors) => {
        if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
            0
        } else {
            1
        }}, // Vertex Program
      triplet => {  // Send Message
          Iterator((triplet.dstId, triplet.attr))
        },
      (a, b) => a + b // Merge Message
    )
    gol.vertices.collect
    // println(gol.vertices.collect.mkString("\n"))
    // $example off$
    sc.stop()
  }
}