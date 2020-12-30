package meta.runtime
package simulation

import scala.reflect.io.Directory
import java.io.File

class Benchmark(config: SimulationConfig, repeat: Int) {

  private val default = new Default(config)
  private val spark = new SimulationSpark(config)

  private var executionTime: List[(Double, Double)] = List()

  def run(): Unit = {
    println("Start benchmark for " + repeat + " times")

    (1 to repeat).foreach(i => {
      val res2: SimulationSnapshot = spark.run()
      val res1: SimulationSnapshot = default.run()

      // Can only benchmark if they return the same result
      assert(res1.currentTurn == res2.currentTurn)
      assert(res1.currentTime == res2.currentTime)
      executionTime = (res1.milliTime, res2.milliTime) :: executionTime

      val directory = new Directory(
        new File ("/Users/ztian/Documents/economic_simulations/checkpoint"))
      directory.deleteRecursively()
    })

    val sum: (Double, Double) = (executionTime.foldLeft(0.0, 0.0)((x, y) => (x._1 + y._1, x._2 + y._2)))

    println("Benchmark summary")
    println("Average time (ms) Default: " + sum._1/repeat + " Spark: " + sum._2/repeat)
  }
}
