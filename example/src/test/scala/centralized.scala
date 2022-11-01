package example.gameOfLife

import scala.collection.mutable.ListBuffer
import scala.util.Random
import lib.Grid.Torus2D
import meta.runtime.util

object centralizedGameOfLife {

    def main(args: Array[String]): Unit = {
        val gridSize: Int = if (args.size > 0) args(0).toInt else 100
        val totalTurns: Int = if (args.size > 1) args(1).toInt else 100

        val totalCells: Int = gridSize * gridSize

        var env: Seq[Boolean] = (1 to totalCells).map(i => Random.nextBoolean())

        def step(): Seq[Boolean] = {
            (0 to totalCells-1).map(i => {
                val n = Torus2D.getNeighborCells(gridSize, gridSize)(i, 1).map(x => env(x))
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