//package example
//package builtin_monitor
//
//////
////
//import squid.quasi.lift
//import meta.classLifting.SpecialInstructions._

//@lift
//class object2 extends Actor {
//
//  def main(): Unit = {
//    while(true){
//      if (scala.util.Random.nextBoolean()){
//        println("Object2 infected!")
//        monitor.logAggregate("Infectious")
//        monitor.logTimeseries("Infectious")
//      }
//      if (scala.util.Random.nextBoolean()){
//        println("Object2 Recovered!")
//        monitor.logAggregate("Recovered")
//        monitor.logTimeseries("Recovered")
//      }
//      waitLabel(Turn,1)
//      handleMessages()
//    }
//  }
//}