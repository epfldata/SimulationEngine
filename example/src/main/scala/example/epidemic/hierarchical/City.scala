package example
package epidemic.hierarchical

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

@lift
class City(val population: List[Person], var delayInResponse: Int) extends Actor {
    // concern ranges from 1 to 10. 10: highest importance; 1: no one cares
    var national_concern: Int = 1
    var total_infected: Int = 0
    var total_population: Int = 0
    var fs: List[Future[Boolean]] = List()

    @transparencyPropagating
    def nationalConcern(newPolicy: Int): Int = {
      national_concern = newPolicy
      national_concern
    }

    def updateResponsiveness(newValue: Int): Int = {
      delayInResponse = newValue
      delayInResponse
    }

    def getLatestInfectiousRatio(): Double = {
      total_infected.toDouble / total_population
    }

    def main(): Unit = {
        total_population = population.size
        while (true) {
            fs = population.map(i => asyncCall(() => i.isInfected(), 5))
            while (fs.exists(p => !p.isCompleted)){
              waitRounds(1)
            }
            total_infected = fs.map(i => i.popValue.get).filter(i => i).length
            println("Total infected people in city " + id + " is " + total_infected + " total population is " + total_population)
            if (total_infected > (1.0/national_concern) * total_population) {
              population.foreach(i => callAndForget(i.updatedRegulation(national_concern), 5))
            }
            waitAndReply(delayInResponse)
        }
    }
}