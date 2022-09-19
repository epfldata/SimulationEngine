package example
package gameOfLifeRPC

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Int) extends Actor {

    var aliveNeighbors: Int = 0

    def tell(state: Int): Unit = {
      aliveNeighbors = aliveNeighbors + state
    }

    def main(): Unit = {
        while(true) {
            aliveNeighbors = 0
            handleRPC()
            if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && aliveNeighbors==3) {
                alive = 1
            }

            connectedAgents.map(x => 
              x.asInstanceOf[Cell]).foreach(v => async_call(() => v.tell(alive), 1)
            )
            waitLabel(Turn, 1)
        }
    }
}