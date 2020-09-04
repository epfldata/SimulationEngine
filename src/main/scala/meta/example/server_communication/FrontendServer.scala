package meta.example.server_communication

import meta.classLifting.SpecialInstructions.{handleMessages, waitTurns}
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class FrontendServer() extends Actor {
  var backendServer: BackendServer = null

  def requestPage(): String = {
    val serverTime = backendServer.getContent
    "<html>"+serverTime+"</html>"
  }

  def main(): Unit = {
    while(true) {
//      requestPage()
//      println("Hello world! Frontend " + id + " Turn " + currentTurn)
      println("requestPage content is " + requestPage())
      waitTurns(1)
      handleMessages()
    }
  }

}
