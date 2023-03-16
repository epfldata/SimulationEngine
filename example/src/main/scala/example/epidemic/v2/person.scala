package example
package epidemic.v2

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import example.epidemic._
import scala.collection.mutable.ListBuffer

@lift
class Person(val age: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: Int = 0 // susceptible
    var vulnerability: Int = 0
    var daysInfected: Int = 0
    var riskBuffer: ListBuffer[Double] = new ListBuffer[Double]()

    @transparencyPropagating
    def makeContact(risk: Double): Int = {
        riskBuffer.append(risk)
        0
    }

    def main(): Unit = {
        markAllowDirectAccess("makeContact")

        vulnerability = if (age > 60) 1 else 0
        if (Random.nextInt(100)==0){
            health = 2
        }

        while (true) {
            if (health != SIRModel.Deceased) {
                // Meet with contacts 
                val selfRisk = SIRModel.infectiousness(health, symptomatic)
                if (!connectedAgents.isEmpty) {
                    connectedAgents.foreach(x => callAndForget(x.asInstanceOf[Person].makeContact(selfRisk), 1))
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
            // merge and process
            riskBuffer.toList.foreach(risk => {
                if (health == 0) {
                    var personalRisk = risk
                    if (age > 60) {
                        personalRisk = personalRisk * 2
                    }
                    if (personalRisk > 1) {
                        health = SIRModel.change(health, vulnerability)
                    }
                }
            })
            riskBuffer.clear()
            waitAndReply(1)
        }
    }
}