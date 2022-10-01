package example
package epidemic.hierarchical

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import scala.util.Random

@lift
class Person(var infected: Boolean) extends Actor {
    var isolation_level: Int = 1
    var other_city_contacts: List[Person] = List()
    var other_country_contacts: List[Person] = List()

    @transparencyPropagating
    def updatedRegulation(national_concern: Int): Int = {
        isolation_level = national_concern/3 + 1
        isolation_level
    }

    @transparencyPropagating
    def contact(): Boolean = {
        if (!infected && Random.nextInt(50)==1){
          infected = true
        }
        infected
    }

    def isInfected(): Boolean = {
      infected
    }

    def main(): Unit = {
        while (true) {
            if (infected){
              if (isolation_level == 1){
                connectedAgents.foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 1))
                other_city_contacts.filter(i => Random.nextInt(100)<20).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 7))
                other_country_contacts.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 30))
              } else if (isolation_level == 2) { // Lock down countries and reduced comm. in general
                connectedAgents.filter(i => Random.nextInt(100)<10).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 1))
                other_city_contacts.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 21))
                other_country_contacts.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 60))
              } else { // Lock down cities and countries
                connectedAgents.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 1))
                other_city_contacts.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 28))
                other_country_contacts.filter(i => Random.nextInt(100)<5).foreach(i => callAndForget(i.asInstanceOf[example.epidemic.hierarchical.Person].contact(), 90))
              }
            }
            waitAndReply(1)
        }
    }
}