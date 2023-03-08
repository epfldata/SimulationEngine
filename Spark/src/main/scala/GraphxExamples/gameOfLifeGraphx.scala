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

object GameOfLifeV2Graphx { 
  def main(args: Array[String]): Unit = {
    val cores = args(0)
    val edgeListFile: String = args(1)
    val cfreq: Int = args(2).toInt

    // Creates a SparkSession.
    val spark = new SparkConf().setMaster(f"local[${cores}]")
    .setAppName("GameOfLife2")
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
    // Msg(A): List[Int]
    // VD: Int
    // ED: List[Int]
    // A graph with edge attributes containing distances
    val graph: Graph[Int, List[Int]] = GraphLoader.edgeListFile(sc, edgeListFile).mapVertices((id, _) => if (Random.nextBoolean()){ 1 } else { 0 }).mapEdges(e => List(e.attr))
    // Initialize the graph such that each vertex is either alive or dead.
    // val initialGraph = graph.mapVertices((id, _) => if (Random.nextBoolean()){1} else {0})
    val gol = graph.pregel(List[Int](0), maxIterations = 200)(
      (id, alive, receivedMsgs) => {
        // println("Total received messages " + receivedMsgs.size)
        val aliveNeighbors = receivedMsgs.filter(i => i == 1).size
        if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
          0
        } else if (alive==0 && aliveNeighbors == 3){
          1
        } else {
          alive
        }}, // Vertex Program
      triplet => {  // Send Message
          Range(0, cfreq).map(_ => (triplet.dstId, List(triplet.srcAttr))).toIterator
          // Iterator(Range(0, cfreq).map(i => (triplet.dstId, List(triplet.srcAttr))))
        },
      (a, b) => a ::: b // Merge Message
    )
    gol.vertices.collect
    // println(gol.vertices.collect.mkString("\n"))
    // $example off$
    sc.stop()
  }
}

// With a clock vertex to track idle interval
object GameOfLifeV3Graphx { 
  def main(args: Array[String]): Unit = {
    val cores = args(0)
    val edgeListFile: String = args(1)
    val cfreq: Int = args(2).toInt
    val interval: Int = args(3).toInt

    // Creates a SparkSession.
    val spark = new SparkConf().setMaster(f"local[${cores}]")
    .setAppName("GameOfLife3")
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
    // Msg(A): List[Int]
    // VD: Int
    // ED: List[Int]
    // A graph with edge attributes containing distances
    val graph: Graph[List[Int], List[Int]] = GraphLoader.edgeListFile(sc, edgeListFile)
      .mapVertices((id, _) => if (Random.nextBoolean()){ List(1, interval) } else { List(0, interval) })
      .mapEdges(e => List(0))
    // Initialize the graph such that each vertex is either alive or dead.
    // val initialGraph = graph.mapVertices((id, _) => if (Random.nextBoolean()){1} else {0})
    val gol = graph.pregel(List[Int](0), maxIterations = 200)(
      (id, state, receivedMsgs) => {
          var alive: Int = state(0)
          var idleCountDown: Int = state(1)

          if (id != 0) {
            if (idleCountDown > 1) {
              idleCountDown -= 1
            } else {
              // println("Total received messages " + receivedMsgs.size)
              val aliveNeighbors = receivedMsgs.filter(i => i == 1).size
              if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
                alive = 0
              } else if (alive==0 && aliveNeighbors == 3){
                alive = 1
              } 
              idleCountDown = interval
            }
          } 
          List(alive, idleCountDown)
        }, // Vertex Program
      triplet => {  // Send Message
        if (triplet.srcId == 0) {
          Iterator((triplet.dstId, List(0)))
        } else {
          val idleCountDown = triplet.srcAttr(1)
          if (idleCountDown <= 1) {
            Range(0, cfreq).map(i => (triplet.dstId, List(triplet.srcAttr(0)))).toIterator
          } else {
            Iterator.empty
          }
        }
        },
      (a, b) => a ::: b // Merge Message
    )
    gol.vertices.collect
    // println(gol.vertices.collect.mkString("\n"))
    // $example off$
    sc.stop()
  }
}