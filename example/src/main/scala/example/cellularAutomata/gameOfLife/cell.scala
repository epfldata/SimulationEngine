package example
package gameOfLife

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import lib.Grid.AgentWithNeighbors

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Boolean, var cfreq: Int) extends AgentWithNeighbors {

    var futures: List[Future[Boolean]] = List()

    def getValue: Boolean = alive

    private def rule(neighbors: List[Boolean]): Unit = {
        val aliveNeighbors = neighbors.filter(x => x==true).size

        if (alive && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
            alive = false
        }
        
        if (!alive && (aliveNeighbors==3)) {
            alive = true
        }
    }

    def main(): Unit = {
        while(true) {
            futures = connectedAgents.map(x => x._2.asInstanceOf[Cell]).toList.map(v => asyncMessage(() => v.getValue))

            while (!(futures.nonEmpty && futures.forall(x => x.isCompleted))) {
                waitLabel(Turn, 1)
            }

            val ans: List[Boolean] = futures.map(o => o.popValue.get).asInstanceOf[List[Boolean]]

            rule(ans)

            waitLabel(Turn, cfreq)
        }
    }
}
