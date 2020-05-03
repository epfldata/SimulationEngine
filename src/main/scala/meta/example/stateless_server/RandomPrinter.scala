package meta.example.stateless_server

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import squid.quasi.lift

@lift
class RandomPrinter(val server: RandomNumberServer) extends Actor {
  var i: Int = 0

  def printNum(): Unit = {
    println(i, server.getNumber())
  }

//  var future_obj1: Option[Future[Int]]= None
//  def sendAsyncMsg(): Unit = {
////    val msg = () => server.getDelayedNumber()
//    if(future_obj1 == None){
//      future_obj1 = asyncMessage(() => server.getDelayedNumber())
//    }
//  }

  def main(): Unit = {
    while (true) {
      printNum()
//      sendAsyncMsg()

      waitTurns(1)
    }
  }
}
