package example
package epidemic.v2

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

@lift
class Person(val age: Int, val cfreq: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: Int = 0 // susceptible
    var vulnerability: Int = 0
    var daysInfected: Int = 0

    @transparencyPropagating
    def makeContact(risk: Double): Boolean = {
        if (health == 0) {
            var personalRisk = risk
            if (age > 60) {
                personalRisk = personalRisk * 2
            }
            if (personalRisk > 1) {
                health = SIRModel.change(health, vulnerability)
            }
        }
        false
    }

    def main(): Unit = {
        vulnerability = if (age > 60) 1 else 0

        while (true) {
            if (health != SIRModel.Deceased) {
                // Meet with contacts 
                val selfRisk = SIRModel.infectiousness(health, symptomatic)
                if (!connectedAgents.isEmpty) {
                    Range(0, cfreq).foreach(i => {
                        connectedAgents.foreach(x => asyncCall(x.asInstanceOf[Person].makeContact(selfRisk), 1))
                    })
                }

                if ((health != SIRModel.Susceptible) && (health != SIRModel.Recover)) {
                    if (daysInfected == SIRModel.stateDuration(health)) {
                        health = SIRModel.change(health, vulnerability)
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