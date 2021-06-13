package example.staged.gameOfLife

import meta.classLifting.SpecialInstructions._
import custMacros.Sim
import meta.runtime._

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@Sim
class Cell(var alive: Boolean) extends Actor {

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
            if (alive) {
                // println(id + " is alive!")
            }
            futures = connectedAgents.map(x => x._2.asInstanceOf[Cell]).toList.map(v => asyncMessage[Boolean](() => v.getValue))

            var syncOneTurn = false

            while (!(futures.nonEmpty && futures.forall(x => x.isCompleted))) {
                syncOneTurn = true
                waitLabel(Turn, 1)
            }

            if (!syncOneTurn){
                waitLabel(Turn, 1)
            }

            val ans: List[Boolean] = futures.map(o => o.popValue.get).asInstanceOf[List[Boolean]]

            rule(ans)

            waitLabel(Turn, 1)
        }
    }
}

class stagedExamples extends org.scalatest.FlatSpec {
    import meta.API._

    "game of life example" should "run and print out the alive cells' ids" in {

        val width = 10
        val height = 10

        val totalPoints: Int = width * height
        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => { 
            new Cell(scala.util.Random.nextBoolean())
        })

        (1 to totalPoints).foreach(i =>
            points(i-1).connectedAgents = lib.Grid.Torus2D.getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
        )

        val c = new SimulationConfig(points.toList, totalTurn = 10)

        StartSimulation[AkkaStaged.type](c)

    }
}