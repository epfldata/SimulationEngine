package example
package epidemic.hierarchical

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import scala.util.Random

@lift
class Person(var infected: Boolean) extends Actor {
    var isolation_level: Int = 1

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
              // println("Isolation level of agent "+ id + " is " + isolation_level)
              if (isolation_level == 1){
                connectedAgents.foreach(i => onesideSend(i.asInstanceOf[example.epidemic.hierarchical.Person].contact()))
              } else if (isolation_level == 2) {
                connectedAgents.filter(i => Random.nextInt(100)<10).foreach(i => onesideSend(i.asInstanceOf[example.epidemic.hierarchical.Person].contact()))
              } else {
                connectedAgents.filter(i => Random.nextInt(100)<2).foreach(i => onesideSend(i.asInstanceOf[example.epidemic.hierarchical.Person].contact()))
              }
            }
            waitAndReply(1)
        }
    }
}