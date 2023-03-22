package example
package epidemic.v1

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import example.epidemic._

@lift
class Person(val age: Int) extends Actor {
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
        if (Random.nextInt(100)==0){
            health = 2
        }

        while (true) {
            if (health != SIRModel.Deceased) {
                handleRPC()
                if (health == SIRModel.Infectious) {
                    // Meet with contacts 
                    if (!connectedAgents.isEmpty) {
                        val selfRisk = SIRModel.infectiousness(health, symptomatic)
                        connectedAgents.foreach(x => callAndForget(x.asInstanceOf[Person].makeContact(selfRisk), 1))
                    }
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
            waitRounds(1)
        }
    }
}