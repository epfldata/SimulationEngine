package example
package epidemic

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

@lift
class Person(val age: Int) extends Actor {
    var dailyContact: Int = 5
    val symptomatic: Boolean = Random.nextBoolean()
    var health: HealthStatus = Susceptible
    var country: Country = null
    var vulnerability: VulnerabilityLevel = null
    var daysInfected: Int = 0
    var policy: NPI = NoNPI
    var connections: List[Person] = null

    var f: List[Future[Boolean]] = List()

    def learnPolicy(newPolicy: NPI): Unit = {
        policy = newPolicy
    }

    def makeContact(risk: Double): Boolean = {
        if (health == Infectious){
            DiseaseParameter.infectiousness(health, symptomatic) > 1
        } else {
            if (health == Susceptible) {
                var personalRisk = risk
                if (age > DiseaseParameter.ageThreshold) {
                    personalRisk = personalRisk * DiseaseParameter.ageSkew
                }
                if (personalRisk > 1) {
                    health = health.change(vulnerability)
                }
            }
            false
        }
    }

    def main(): Unit = {
        vulnerability = DiseaseParameter.vulnerabilityLevel(age)

        while (true) {
            if (health != Deceased) {
                dailyContact = policy.contactNumber(health)
                // Meet with contacts 
                val selfRisk = DiseaseParameter.infectiousness(health, symptomatic)
                f = Range(0, dailyContact).toList            
                        .map(i => connections(Random.nextInt(connections.length)))
                        .map(x => asyncMessage[Boolean](() => x.makeContact(selfRisk)))

                while (f.exists(x => !x.isCompleted)){
                    waitLabel(Turn, 1)
                }
                val affected = f.map(x => x.popValue.get).exists(e => e == true)
                if (affected && health == Susceptible) {
                    health = health.change(vulnerability)
                }

                if ((health != Susceptible) && (health != Recover)) {
                    // report health status
                    asyncMessage[Unit](() => country.report(health))
                    if (daysInfected == DiseaseParameter.stateDuration(health)) {
                        health = health.change(vulnerability)
                        daysInfected = 0
                    } else {
                        daysInfected = daysInfected + 1
                    }
                }
                waitLabel(Turn, 1)
            } else {
                waitLabel(Turn, 1)
            }
        }
    }
}