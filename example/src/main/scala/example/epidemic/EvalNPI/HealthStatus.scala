package example
package epidemic.evalNPI


import scala.util.Random

// Extended SEIR model:
// Susceptible, Exposed, Infectious, Hospitalized, Recover, Deceased
object HealthStatus {
    def change(health: String, vulnerability: String): String = {
        health match {
            case "Susceptible" => "Exposed"
            case "Exposed" => 
                val worse_prob: Double = Risk.eval(vulnerability, "Exposed", "Infectious")
                if (Random.nextDouble < worse_prob) {
                    "Infectious"
                } else {
                    "Recover"
                }
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