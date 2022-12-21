package example
package epidemic.evalNPIFlat

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

@lift
class Person(val age: Int, val dayUnit: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: String = "Susceptible"
    var vulnerability: String = "low"
    var daysInfected: Int = 0
    var policy: Int = 0

    @transparencyPropagating
    def learnPolicy(newPolicy: Int): Int = {
        policy = newPolicy
        policy
    }

    @transparencyPropagating
    def makeContact(risk: Double): Boolean = {
        if (health == "Susceptible") {
            var personalRisk = risk
            if (age > DiseaseParameter.ageThreshold) {
                personalRisk = personalRisk * DiseaseParameter.ageSkew
            }
            if (personalRisk > 1) {
                health = HealthStatus.change(health, vulnerability)
            }
        }
        false
    }

    def main(): Unit = {
        vulnerability = DiseaseParameter.vulnerabilityLevel(age)

        while (true) {
            if (health != "Deceased") {
                // Meet with contacts 
                val selfRisk = DiseaseParameter.infectiousness(health, symptomatic)
                if (!connectedAgents.isEmpty) {
                    connectedAgents.foreach(x => asyncCall(x.asInstanceOf[Person].makeContact(selfRisk), 1))
                }

                if ((health != "Susceptible") && (health != "Recover")) {
                    // report health status
                    if (daysInfected == DiseaseParameter.stateDuration(health)) {
                        health = HealthStatus.change(health, vulnerability)
                        daysInfected = 0
                    } else {
                        daysInfected = daysInfected + 1
                    }
                }
            } 
            waitAndReply(1)
        }
    }
}