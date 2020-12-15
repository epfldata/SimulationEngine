package example.latency

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift
import meta.deep.runtime.Future

@lift
class Client(var server: Server, var reqTime: Double, var replyTime: Double) extends Actor {

  var sentTime: Double = 0
  var receivedTime: Double = 0

  def makeReq(): Unit = {
    println("Client " + id + " processing ")
    server.request(id)
  }

  def procReply(): Unit = {
    println("Client " + id + " analyzing reply; take " + replyTime)
//    waitTime(replyTime)
  }

  def main(): Unit = {
    while(true) {
      makeReq()
//      procReply()
//      waitTurns(1)
    }
  }
}