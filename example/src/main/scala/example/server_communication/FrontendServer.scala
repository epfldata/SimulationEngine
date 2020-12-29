package example
package server_communication

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

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
      waitLabel(Turn,1)
      handleMessages()
    }
  }

}
