package example
package epidemic

import scala.util.Random
import scala.math.{min, max}
import breeze.stats.distributions.Gamma

// Extended SIR model
object SIRModel {
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
}