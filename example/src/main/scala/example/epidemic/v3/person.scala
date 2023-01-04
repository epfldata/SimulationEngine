package example
package epidemic.v3

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.runtime.Message

@lift
class Person(val age: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: Int = Random.nextInt(5)
    var vulnerability: Int = 0
    var daysInfected: Int = 0

    def main(): Unit = {
        vulnerability = if (age > 60) 1 else 0

        while (true) {
            if (health != SIRModel.Deceased) {
                var m = receiveMessage()
                while (m.isDefined){
                    if (health == 0) {
                        var personalRisk = m.get.value
                        if (age > 60) {
                            personalRisk = personalRisk * 2
                        }
                        if (personalRisk > 1) {
                            health = SIRModel.change(health, vulnerability)
                        }
                    }
                    m = receiveMessage()
                }

                // Meet with contacts 
                val selfRisk = SIRModel.infectiousness(health, symptomatic)

                connectedAgentIds.foreach(i => {
                    val msg = new Message()
                    msg.value = selfRisk
                    sendMessage(i, msg)
                })
                

                if ((health != 0) && (health != 4)) {
                    if (daysInfected == SIRModel.stateDuration(health)) {
                        // health = 4
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