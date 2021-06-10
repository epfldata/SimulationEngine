package example.gameOfLife

import scala.collection.mutable.ListBuffer
import scala.util.Random
import lib.Grid.Torus2D._
import meta.runtime.simulation.util

object centralizedGameOfLife {

    def main(args: Array[String]): Unit = {
        val gridSize: Int = args(0).toInt
        val totalTurns: Int = args(1).toInt

        val totalCells: Int = gridSize * gridSize

        var env: Seq[Boolean] = (1 to totalCells).map(i => Random.nextBoolean())

        def step(): Seq[Boolean] = {
            (0 to totalCells-1).map(i => {
                val n = getNeighborCells(gridSize, gridSize)(i, 1).map(x => env(x-1))
                val aliveNeighbors = n.filter(_==true).size
                if (env(i) && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
                    false
                } else if (!env(i) && (aliveNeighbors==3)) {
                    true
                } else {
                    env(i)
                }
            })
        }

        (1 to totalTurns).foreach({_ => {
            util.bench(
                env = step()
            )
        }})
    }
}