package example
package gameOfLife

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

import lib.Bot.LoggerBotInt

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Boolean) extends Actor {

    var futures: List[Future[Boolean]] = List()

    def getValue: Boolean = alive

    def main(): Unit = {
        while(true) {
            if (alive) {
                println("Alive")
            }
            futures = connectedAgents.map(x => x.asInstanceOf[Cell]).map(v => asyncMessage(() => v.getValue))
            while (!(futures.nonEmpty && futures.forall(x => x.isCompleted))) {
                waitLabel(Turn, 1)
                handleMessages()
            }
            val ans: List[Boolean] = futures.map(o => o.popValue.get).asInstanceOf[List[Boolean]]

            val aliveNeighbors = ans.filter(x => x==true).size

            if (alive && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
                alive = false
            }
            if (!alive && (aliveNeighbors==3)) {
                alive = true
            }

            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}
