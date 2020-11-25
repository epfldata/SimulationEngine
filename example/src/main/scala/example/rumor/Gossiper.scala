 package meta.example.rumor

 import meta.classLifting.SpecialInstructions._
 import meta.deep.runtime.Actor
 import squid.quasi.lift
 import scala.collection.mutable.Set
 import scala.util.Random

 trait Person extends Actor {
   // Assume a person has the following prob to spread the rumour
//   var activityLevel = Random.nextInt(10)
   val network: Set[Person] = Set[Person]()
 }

 @lift
 class Gossiper(val env: Env, var heardRumor: Boolean, val spreadProb: Double) extends Person{
   var reported: Boolean = false

   def spreadRumor: Boolean = {
     val stochastic: Boolean = Random.nextDouble() > spreadProb
     heardRumor && stochastic
   }

   def hearRumor(): Unit = {
     heardRumor = true
   }

   def report(): Unit = {
     // todo fix unsupported code inside code
     if (heardRumor) {
       if (!reported) {
         env.reportRumor()
         reported = true
       }
     }
   }

   def gossipRumor(p: Gossiper): Unit = {
     p.hearRumor()
   }

   def main(): Unit = {
     while(true){
       report()
       if (spreadRumor){
          network.toList.foreach(p => if (Random.nextBoolean()) gossipRumor(p.asInstanceOf[Gossiper]))
       }
       waitTurns(1 )
       handleMessages()
     }
   }
 }


