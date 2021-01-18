package example
package rumor

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import lib.Bot.LoggerBotInt

trait Person extends Actor {
   // Assume a person has the following prob to spread the rumour
   var network: Set[Person] = Set[Person]()
}

@lift
 class Gossiper(val loggerBot: LoggerBotInt,
                var heardRumor: Boolean,
                val spreadProb: Double) extends Person {

   var recorded: Boolean = false
   val accValue: Int = 1

   def spreadRumor: Boolean = {
     heardRumor && (Random.nextDouble() > spreadProb)
   }

   def hearRumor(): Unit = {
     heardRumor = true
   }

   def gossipRumor(p: Gossiper): Unit = {
     asyncMessage(() => p.hearRumor())
   }

  def newHire(): Unit = {
    network = network.union(Set(new Gossiper(loggerBot, false, spreadProb)))
  }

   def main(): Unit = {
     while(true){
       if (heardRumor && !recorded) {
         asyncMessage(() => loggerBot.log(accValue))
         recorded = true
       }

       if (spreadRumor){
          network.toList.foreach(p => if (Random.nextBoolean()) gossipRumor(p.asInstanceOf[Gossiper]))
       }
       if (Random.nextInt(30) == 5) {
         newHire()
       }
       waitLabel(Turn,1 )
       handleMessages()
     }
   }
 }


