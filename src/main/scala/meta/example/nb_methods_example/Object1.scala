package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import squid.quasi.{dbg_lift, lift}

import scala.collection.mutable.ListBuffer

@lift
class Object1(var n1: Object2, var n2: Object3) extends Actor {

  def hello(msgId: Int): Unit = {
    println("Async msg" + msgId + ": local method with wait 1")
    waitTurns(1)
  }

  var future_obj1: Option[Future[Int]]= None
  var future_obj2: Option[Future[String]] = None

  def main(): Unit = {

    while (true) {
      val msg1 = ()=> n1.get(1)
      val msg2 = ()=> n2.get(2)
      val msg3 = ()=> hello(3)
      val msg4 = ()=> n2.getWR(4)

      if (future_obj1 == None){
        println("Send async msg1: with response")
        future_obj1 = asyncMessage(msg1)
      } else {
        if (isCompleted(future_obj1.get)){
          println("Receive response from msg1!")
//          println("msg1 value is " + getFutureValue[Int](future_obj1.get))
          future_obj1 = clearFutureObj(future_obj1.get)
        } else {
          println("msg1 not completed!")
        }
      }

      // async call without returning values
      println("Send async msg2: w/o response")
      asyncMessage(msg2)
      println("Send async msg3: w/o response")
      asyncMessage(msg3)

      // multiple async calls that return at the same iteration
      if (future_obj2 == None){
        println("Send async msg4: with response")
        future_obj2 = asyncMessage(msg4)
      } else {
        if (isCompleted(future_obj2.get)){
          println("Receive response from msg4!")
          future_obj2 = clearFutureObj(future_obj2.get)
        } else {
          println("msg4 not completed!")
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

      assert(async_messages.size == 0)
      waitTurns(1)
    }
  }
}
