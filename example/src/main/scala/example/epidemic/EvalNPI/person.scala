package example
package epidemic.evalNPI

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

@lift
class Person(val age: Int, val dayUnit: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: String = "Susceptible"
    var country: Country = null
    var vulnerability: String = "low"
    var daysInfected: Int = 0
    var policy: Int = 0
    var hourCounter: Int = 0

    var f: List[Future[Boolean]] = List()

    @transparencyPropagating
    def learnPolicy(newPolicy: Int): Int = {
        policy = newPolicy
        policy
    }

    @transparencyPropagating
    def makeContact(risk: Double): Boolean = {
        if (health == "Infectious"){
            DiseaseParameter.infectiousness(health, symptomatic) > 1
        } else {
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
    }

    def main(): Unit = {
        vulnerability = DiseaseParameter.vulnerabilityLevel(age)

        while (true) {
            if (health != "Deceased") {
                // Meet with contacts 
                val selfRisk = DiseaseParameter.infectiousness(health, symptomatic)
                if (!connectedAgents.isEmpty) {
                    f = connectedAgents.map(x => asyncCall(x.asInstanceOf[Person].makeContact(selfRisk), 1))

                    while (f.exists(x => !x.isCompleted)){
                        waitAndReply(1)
                        hourCounter = hourCounter + 1
                    }
                    val affected = f.map(x => x.popValue.get).exists(e => e == true)
                    if (affected && health == "Susceptible") {
                        health = HealthStatus.change(health, vulnerability)
                    }
                }

                if ((health != "Susceptible") && (health != "Recover")) {
                    // report health status
                    callAndForget(country.report(health), 2)
                    if (daysInfected == DiseaseParameter.stateDuration(health)) {
                        health = HealthStatus.change(health, vulnerability)
                        daysInfected = 0
                    } else {
                        daysInfected = daysInfected + 1
                    }
                }
                waitRounds(dayUnit - hourCounter)
                handleRPC()
                hourCounter = 0
            } else {
                waitRounds(dayUnit)
            }
        }
    }
}