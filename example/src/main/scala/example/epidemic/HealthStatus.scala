package example.epidemic

import scala.util.Random

// SEIR model
object HealthStatus {
    def change(health: String, vulnerability: String): String = {
        health match {
            case "Susceptible" => "Infectious"
            case "Infectious" => 
                val worse_prob: Double = Risk.eval(vulnerability, "Infectious", "Hospitalized")
                if (Random.nextDouble < worse_prob) {
                    "Hospitalized"
                } else {
                    "Recover"
                }
            case "Hospitalized" =>
                val worse_prob: Double = Risk.eval(vulnerability, "Hospitalized", "Deceased")
                if (Random.nextDouble < worse_prob) {
                    // println("Deceased!")
                    "Deceased"
                } else {
                    "Recover"
                }
            case _ => health
        }
    }
}