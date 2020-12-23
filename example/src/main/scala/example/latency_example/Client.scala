package example
package latency

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Client(var server: Server, var reqTime: Double, var otherWorkTime: Double) extends Actor {

  var sentTime: Double = 0
  var receivedTime: Double = 0

  def makeReq(): Unit = {
    println("Client " + id + " making request ")
    asyncMessage(() => server.request(id))
    waitLabel("time", reqTime)
  }

  def otherWork(): Unit = {
    println("Client " + id + " doing other work; take " + otherWorkTime)
    waitLabel("time", otherWorkTime)
  }

  def main(): Unit = {
    while(true) {
      makeReq()
      otherWork()
//      waitLabel("turn",1)
    }
  }
}