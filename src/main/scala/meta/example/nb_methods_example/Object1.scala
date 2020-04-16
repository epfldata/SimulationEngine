package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import squid.quasi.{dbg_lift, lift}

import scala.collection.mutable.ListBuffer

@lift
class Object1(var n1: Object2, var n2: Object3) extends Actor {

  def hello(name: String): Unit = {
    println("Hello " + name)
  }

  var future_obj1: Option[Future[Int]]= None
  var future_obj2: Option[Future[Int]] = None

  def main(): Unit = {

    while (true) {
      val msg1 = ()=> n1.get(10, 15)
      val msg2 = ()=> n2.get()
      val msg3 = ()=> n2.getWR()

      println("This should be printed in each iteration")

      if (future_obj1 == None){
        println("Send async msg1")
        future_obj1 = asyncMessage(msg1)
      } else {
        if (isCompleted(future_obj1.get)){
          println("msg1 is completed!")
          println("msg1 value +10 is " + (getFutureValue[Int](future_obj1.get) + 10))
//          clearFutureResponse(future_obj1.get.id)
//          future_obj1 = None
          future_obj1 = clearFutureObj(future_obj1.get)
        } else {
          println("msg1 not completed!")
        }
      }

      // async call without returning values
      asyncMessage(msg2)

      // multiple async calls that return at the same iteration
      if (future_obj2 == None){
        println("Send async msg3")
        future_obj2 = asyncMessage(msg3)
      } else {
        if (isCompleted(future_obj2.get)){
          println("msg3 is completed!")
          println("msg3 value is " + getFutureValue[Int](future_obj2.get))
          future_obj2 = clearFutureObj(future_obj2.get)
        } else {
          println("msg3 not completed!")
        }
      }

      // Wrong! will always return not completed
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
