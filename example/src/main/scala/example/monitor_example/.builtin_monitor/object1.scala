//package example
//package monitor_example.builtin_monitor
//
//
//////
//import squid.quasi.lift
//import meta.classLifting.SpecialInstructions._

//@lift
//class object1 extends Actor {
//  val name: String = "object 1"
//  var isInfected: Boolean = false
//
//  def main(): Unit = {
//    while (true) {
//      isInfected = scala.util.Random.nextBoolean()
//      if (isInfected){
//        println("Object1 infected!")
//        monitor.logAggregate("Infectious")
//        monitor.logTimeseries("Infectious")
//      }
//      waitLabel("turn",1)
//      handleMessages()
//    }
//  }
//}
