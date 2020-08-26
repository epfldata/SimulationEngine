package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import squid.quasi.{dbg_lift, lift}

import scala.collection.mutable.ListBuffer

object expSetup {
  val someVal: Double = 10.7
}

@lift
class Object1(var n1: Object2) extends Actor {

  var future_obj1: Option[Future[Int]]= None

  // The arguments for asynchronous message need to be a local variable of the Sim. constant won't work
  val secretToken: Double = 0.5
  var mtdId: Int = 1
  var secretToken2: Double = expSetup.someVal // if need any global var

  def main(): Unit = {
    while (true) {
      val msg1 = ()=> n1.get(mtdId, secretToken, secretToken2)

      if (future_obj1 == None){
        println("Send async msg1: with response")
        future_obj1 = asyncMessage(msg1)
        println("The line after async call")
      } else {
        if (isCompleted(future_obj1.get)){
          println("Receive response from msg1!")
          future_obj1 = clearFutureObj(future_obj1.get)
        } else {
          println("msg1 not completed!")
        }
      }

      // Wrong! a new future_obj3 is created in each iteration, always return non completed
//      var future_obj3: Option[Future[Int]] = asyncMessage(msg3)
//
//      if (!isCompleted(future_obj3.get)){
//        println("4 Not completed!")
//      } else {
//        println("4 Reply value is " + getFutureValue[Int](future_obj3.get))
//        future_obj3 = clearFutureObj(future_obj3.get)
//      }

      waitTurns(1)
    }
  }
}
