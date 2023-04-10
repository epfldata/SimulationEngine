package example
package gameOfLife

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.runtime._

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Int) extends Actor {
  
  var aliveNeighbors: Int = 0
    def main(): Unit = {
        while(true) {
            // Messages are buffered and not delivered immediately
            connectedAgentIds.foreach(i => {
              val msg = new IntMessage(alive)
              sendMessage(i, msg)
            })
            // Messages are sent and arrive at the beginning of the next round
            waitRounds(1)

            var m: Option[Message] = receiveMessage()
            aliveNeighbors = 0
            while (m.isDefined){
              aliveNeighbors = aliveNeighbors + m.get.asInstanceOf[IntMessage].value
              m = receiveMessage()
            }

            if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && aliveNeighbors==3) {
              alive = 1
            }
        }
    }
}
