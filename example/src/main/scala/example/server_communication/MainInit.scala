package example
package server_communication

import squid.quasi.lift

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = new ListBuffer[Actor]()

    for(i <- 0 to 3) {
      val backendServer = new BackendServer
      val frontendServer = new FrontendServer
      frontendServer.backendServer = backendServer

      l.append(backendServer)
      l.append(frontendServer)
    }

    l.toList

  }
}
