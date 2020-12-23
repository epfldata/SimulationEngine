package example
package rumor

import scala.collection.mutable.Set
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

trait Person extends Actor {
   // Assume a person has the following prob to spread the rumour
//   var activityLevel = Random.nextInt(10)
   val network: Set[Person] = Set[Person]()
}

@lift
 class Gossiper(val env: Env, var heardRumor: Boolean, val spreadProb: Double) extends Person{
   var reported: Boolean = false

   def spreadRumor: Boolean = {
     heardRumor && (Random.nextDouble() > spreadProb)
   }

   def hearRumor(): Unit = {
     heardRumor = true
   }

   def report(): Unit = {
     if (heardRumor && !reported) {
       asyncMessage(() => env.reportRumor())
       reported = true
     }
   }

   def gossipRumor(p: Gossiper): Unit = {
     asyncMessage(() => p.hearRumor())
   }

   def main(): Unit = {
     while(true){
       report()
       if (spreadRumor){
          network.toList.foreach(p => if (Random.nextBoolean()) gossipRumor(p.asInstanceOf[Gossiper]))
       }
       waitLabel("turn",1 )
       handleMessages()
     }
   }
 }


