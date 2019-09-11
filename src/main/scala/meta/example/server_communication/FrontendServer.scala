package meta.example.server_communication

import meta.classLifting.SpecialInstructions
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
      requestPage()
      SpecialInstructions.waitTurns(1)
    }
  }

}
