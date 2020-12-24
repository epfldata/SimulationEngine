package example
package latency


import meta.runtime.Actor.AgentId
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Server(var processTime: Double) extends Actor {

  def request(clientId: AgentId): Int = {
    println("Server processing request for " + clientId)
    if (Random.nextBoolean()) {
      println("Please wait! ; take " + processTime)
      waitLabel("time", processTime)
    }
    val ans: Int = Random.nextInt(100)
    println("Response for client id " + clientId + " is " + ans)
    ans
  }

  def main(): Unit = {
    while(true) {
      handleMessages()
      waitLabel("time",0.1)
      waitLabel("turn",1)
    }
  }
}