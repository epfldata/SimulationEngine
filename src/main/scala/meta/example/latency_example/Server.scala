package meta.example.latency

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift
import scala.util.Random
@lift
class Server(var processTime: Double) extends Actor {

  // Each request takes 0.5 unit of time to process
  def request(clientId: AgentId): Int = {
    println("Server processing request for " + clientId + "; take " + processTime)
    waitTime(processTime)
    val ans: Int = Random.nextInt(100)
    println("Response for client id " + clientId + " is " + ans)
    ans
  }

  def main(): Unit = {
    while(true) {
      handleMessages()
      waitTime(0.1)
    }
  }
}