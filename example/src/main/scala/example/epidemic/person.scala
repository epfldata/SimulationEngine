package example
package epidemic

import scala.util.Random
import scala.collection.mutable.ListBuffer
import breeze.stats.distributions.Gamma

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

// Epidemic simulation, static graph (fixed connection)
@lift
class Person(val dailyContact: Int, val age: Int) extends Actor {
    val symptomatic: Boolean = Random.nextBoolean()
    var connection: List[Person] = null
    var health: HealthStatus = Susceptible

    var totalConnections: Int = 0
    var f: Option[Future[Boolean]] = None

    var daysInfected: Int = 0

    val dailyTimeSlices: Int = 24
    var usedTimeSlices: Int = 0

    def getStatus(): HealthStatus = health

    def infectiousness(): Double = {
        if (health == Infectious) {
            var gd = Gamma(DiseaseParameter.infectiousAlpha, DiseaseParameter.infectiousBeta).draw()

            if (symptomatic){
                gd = gd * DiseaseParameter.sympotamaticSkew
            }

            gd
        } else {
            0
        }
    }

    def makeContact(risk: Double): Boolean = {
        if (health == Infectious){
            infectiousness() > 1
        } else {
            if (health == Susceptible) {
                var personalRisk = risk
                if (age > DiseaseParameter.ageThreshold) {
                    personalRisk = personalRisk * DiseaseParameter.ageSkew
                }
                if (personalRisk > 1) {
                    health = health.change()
                }
            }
            false
        }
    }

    var cnt: Int = 0

    // people don't change their behaviour
    def main(): Unit = {
        totalConnections = connection.size

        while (true) {
            cnt = 0
            usedTimeSlices = 0

            while ((cnt < dailyContact) && (usedTimeSlices < dailyTimeSlices)) {
                val conn = connection(Random.nextInt(totalConnections))
                val selfRisk = infectiousness()
                
                f = Some(asyncMessage(() => conn.makeContact(selfRisk)))

                while (!f.get.isCompleted) {
                    waitLabel(Turn, 1)
                    usedTimeSlices = usedTimeSlices + 1
                    handleMessages()
                }

                val affected = f.get.value.get

                if (affected && health == Susceptible) {
                    health = health.change()
                }

                cnt = cnt + 1
            }

            if (health == Infectious) {
                if (daysInfected == DiseaseParameter.mildRecover) {
                    health = health.change()
                    daysInfected = 0
                } else {
                    daysInfected = daysInfected + 1
                }
            }

            handleMessages()
            waitLabel(Turn, dailyTimeSlices - usedTimeSlices)
        }
    }
}