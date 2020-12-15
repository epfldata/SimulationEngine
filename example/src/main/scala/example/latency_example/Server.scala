package example.latency

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift
import scala.util.Random

@lift
class Server(var processTime: Double) extends Actor {

  def request(clientId: AgentId): Int = {
    println("Server processing request for " + clientId)
    if (Random.nextBoolean()) {
      println("Please wait! ; take " + processTime)
      waitTime(processTime)
    }
    val ans: Int = Random.nextInt(100)
    println("Response for client id " + clientId + " is " + ans)
    ans
  }

  def main(): Unit = {
    while(true) {
      handleMessages()
      waitTime(0.1)
      waitTurns(1)
    }
  }
}