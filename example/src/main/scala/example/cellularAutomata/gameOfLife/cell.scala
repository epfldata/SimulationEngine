package example
package gameOfLife

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.runtime.{RequestMessage, ResponseMessage, Message}
import meta.runtime.ResponseMessage

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Int) extends Actor {

    def main(): Unit = {
        while(true) {
            var m: Option[Message] = receiveMessage()
            var aliveNeighbors: Int = 0
            
            while (m.isDefined){
              aliveNeighbors = aliveNeighbors + m.get.value
              m = receiveMessage()
            }

            if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && aliveNeighbors==3) {
                alive = 1
            }

            connectedAgents.foreach(i => {
              val msg = new Message()
              msg.value = 0
              sendMessage(i.id, msg)
            })
            println("Agent " + id + " received total messages " + alive)
            waitLabel(Turn, 1)
        }
    }
}
