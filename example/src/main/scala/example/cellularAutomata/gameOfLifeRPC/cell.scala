package example
package gameOfLifeRPC

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Int) extends Actor {

    var aliveNeighbors: Int = 0

    def tell(state: Int): Int = {
      aliveNeighbors = aliveNeighbors + state
      aliveNeighbors
    }

    def main(): Unit = {
        while(true) {
            connectedAgents.map(x => 
              x.asInstanceOf[Cell]).foreach(v => callAndForget(() => v.tell(alive), 1))
            aliveNeighbors = 0
            waitAndReply(1)
            if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && aliveNeighbors==3) {
              alive = 1
            }
        }
    }
}