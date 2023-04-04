package example
package epidemic.v5

import scala.util.Random
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.runtime.Message
import example.epidemic._

@lift
class Person(val age: Int, val cfreq: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var health: Int = 0
    var vulnerability: Int = 0
    var daysInfected: Int = 0

    def main(): Unit = {
        vulnerability = if (age > 60) 1 else 0
        if (Random.nextInt(100)==0){
            health = 2
        }

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
                if (health == SIRModel.Infectious) {
                    // calculate infectiousness only once
                    val selfRisk = SIRModel.infectiousness(health, symptomatic)
                    connectedAgentIds.foreach(i => {
                        // default 
                        // Range(0, cfreq).foreach(j => {
                        //     val msg = new Message()
                        //     msg.value = selfRisk
                        //     sendMessage(i, msg)
                        // })
                        // batching
                        sendMessages.getOrElseUpdate(i, new ListBuffer[Message]()).appendAll(Range(0, cfreq).map(i => {
                            val msg = new Message()
                            msg.value = selfRisk
                            msg
                        }))
                    })
                }

                if ((health != SIRModel.Susceptible) && (health != SIRModel.Recover)) {
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