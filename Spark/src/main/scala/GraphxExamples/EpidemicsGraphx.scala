package simulation.spark
package examples

import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.rdd.RDD

import scala.util.Random
import scala.math.{min, max}
import breeze.stats.distributions.Gamma

object EpidemicsGraphx { 
  import simulation.spark.API.Simulate.sc

  def main(args: Array[String]): Unit = {
    val t1 = System.currentTimeMillis()
    val totalIterations: Int = 50
    val edgeListFile: String = args(0)
    val cfreq: Int = args(1).toInt
    val interval: Int = args(2).toInt

    // Encodings (health states)
    val Susceptible: Int = 0
    val Exposed: Int = 1
    val Infectious: Int = 2
    val Hospitalized: Int = 3
    val Recover: Int = 4
    val Deceased: Int = 5
    // Encodings (vulnerability levels)
    val Low: Int = 0
    val High: Int = 1
    // Infectious parameter (gamma distribution)
    val infectiousAlpha = 0.25
    val infectiousBeta = 1
    val symptomaticSkew = 2

    def change(health: Int, vulnerability: Int): Int = {
        health match {
            case Susceptible => Exposed
            case Exposed => 
                val worse_prob: Double = eval(vulnerability, Exposed, Infectious)
                if (Random.nextDouble < worse_prob) {
                    Infectious
                } else {
                    Recover
                }
            case Infectious => 
                val worse_prob: Double = eval(vulnerability, Infectious, Hospitalized)
                if (Random.nextDouble < worse_prob) {
                    Hospitalized
                } else {
                    Recover
                }
            case Hospitalized =>
                val worse_prob: Double = eval(vulnerability, Hospitalized, Deceased)
                if (Random.nextDouble < worse_prob) {
                    Deceased
                } else {
                    Recover
                }
            case _ => health
        }
    }

    def stateDuration(health: Int): Int = {
        val randDuration: Int = (3*Random.nextGaussian()).toInt

        health match {
            case Infectious => max(2, randDuration+6) 
            case Hospitalized => max(2, randDuration+7) 
            case Exposed => max(3, randDuration+5)
        }
    }

    def infectiousness(health: Int, symptomatic: Boolean): Double = {
        if (health == Infectious) {
            var gd = Gamma(infectiousAlpha, infectiousBeta).draw()
            if (symptomatic){
                gd = gd * 2
            }
            gd
        } else {
            0
        }
    }

    def eval(vulnerability: Int, currentHealth: Int, projectedHealth: Int): Double = {
        vulnerability match {
            case Low =>
                (currentHealth, projectedHealth) match {
                    case (Exposed, Infectious) => 0.6
                    case (Infectious, Hospitalized) => 0.1
                    case (Hospitalized, Deceased) => 0.1
                    case _ => 0.01
                }
            case High =>
                (currentHealth, projectedHealth) match {
                    case (Exposed, Infectious) => 0.9
                    case (Infectious, Hospitalized) => 0.4
                    case (Hospitalized, Deceased) => 0.5
                    case _ => 0.05
                }
        }
    }

    // $example on$
    // Msg(A): List[Int]
    // VD: Int
    // ED: List[Int]
    // Vertex state: [Age, Symptomatic, Health, Vulnerability, DaysInfected]
    val graph: Graph[List[Int], List[Double]] = GraphLoader.edgeListFile(sc, edgeListFile).mapEdges(e => List(e.attr.toDouble))
    .mapVertices((id, _) => {
      val age: Int = Random.nextInt(90) + 10
      val symptomatic: Int = if (Random.nextBoolean) 1 else 0
      val health: Int = if (Random.nextInt(100)==0) 2 else 0
      val vulnerability: Int = if (age > 60) 1 else 0
      val daysInfected: Int = 0
      val idleCountDown: Int = interval
      List(age, symptomatic, health, vulnerability, daysInfected, idleCountDown)
    })

    val vertexProgram = graph.pregel(List(0.0), maxIterations = totalIterations)(
      (id, state, receivedMsgs) => {
        val age: Int = state(0)
        val symptomatic: Int = state(1)
        var health: Int = state(2)
        val vulnerability: Int = state(3)
        var daysInfected: Int = state(4)
        var idleCountDown: Int = state(5)

        if (id != 0) {
          if (idleCountDown > 1) {
            idleCountDown -= 1
          } else {
            if (health != Deceased) {
              if ((health != Susceptible) && (health != Recover)) {
                  if (daysInfected == stateDuration(health)) {
                      // health = 4
                      health = change(health, vulnerability)
                      daysInfected = 0
                  } else {
                      daysInfected = daysInfected + 1
                  }
              }

              receivedMsgs.foreach(m => {
                if (health==0) {
                  var risk: Double = m
                  if (age > 60) {
                    risk = risk * 2
                  } 
                  if (risk > 1) {
                    health = change(health, vulnerability)
                  }
                }
              })
              idleCountDown = interval
            }
          }
          List(age, symptomatic, health, vulnerability, daysInfected, idleCountDown)
        } else {
          state
        }}, // Vertex Program
      triplet => {  // Send Message
        val health = triplet.srcAttr(2)
        val symptomatic = if (triplet.srcAttr(1)==1) true else false
        if (triplet.srcId == 0) {
          Iterator((triplet.dstId, List(0.0)))
        } else {
          val idleCountDown = triplet.srcAttr(5)
          // Calculate infectiousness only once
          val infectious = infectiousness(health.toInt, symptomatic)
          // Only send out messages to others if not idle
          if (idleCountDown <= 1) {
            Range(0, cfreq).map(i => (triplet.dstId, List(infectious))).toIterator
          } else {
            Iterator.empty
          }
        }
      },
      (a, b) => a ::: b // Merge Message
    )
    vertexProgram.vertices.collect
    println(f"Average time per iteration ${(System.currentTimeMillis() - t1)/totalIterations}")

    // $example off$
    sc.stop()
  }
}