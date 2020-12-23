package example
package nb_methods_example

import meta.deep.runtime.{Future}
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

object expSetup {
  val someVal: Double = 10.7
}

@lift
class Object1(var n1: Object2) extends Actor {

  var future_obj1: Option[Future[Int]]= None

  // The arguments for asynchronous message need to be a local variable of the Sim. constant won't work
  val secretToken: Double = 0.5
  var secretToken2: Double = expSetup.someVal // if need any global var

  def inquire(): Unit = {
    // Wrong! a new future_obj1 is created in each iteration, always return non completed
    //      var future_obj1: Option[Future[Int]] = asyncMessage(msg1)
    val msg1 = ()=> n1.get(id, secretToken, secretToken2)
    if (future_obj1 == None){
      println("Send async msg1 " + id)
      future_obj1 = asyncMessage(msg1)
      //        println("The line after async call")
    } else {
      if (isCompleted(future_obj1.get)){
        println("Response received " + getFutureValue[Int](future_obj1.get) + " Id: " + id)
        future_obj1 = clearFutureObj(future_obj1.get)
      } else {
        println("msg not completed! " + id)
      }
    }
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      inquire()
      assert(async_messages.size == 0)
      waitLabel("turn",1)
    }
  }
}
