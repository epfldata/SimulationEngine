package example
package gameOfLifeRPCOneSideMultiversion

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}

@lift
class Cell(var alive: Int) extends Actor {    

    // var aliveNeighbors: Multiversion[Int] = new Multiversion[Int](0)
    var aliveNeighbors: MutMap[Int, Int] = MutMap[Int, Int]()
    var currentAliveNeighbors: Int = 0

    @transparencyPropagating
    def tell(key: Int, state: Int): Int = {
      // aliveNeighbors = aliveNeighbors + (key, state)
      aliveNeighbors.update(key, aliveNeighbors.getOrElse(key, 0) + state)
      aliveNeighbors(key)
    }
    
    def main(): Unit = {
      markAllowDirectAccess("tell")
        while(true) {
            aliveNeighbors.getOrElseUpdate(time, 0)
            handleRPC()
            // currentAliveNeighbors = (aliveNeighbors - time).get
            currentAliveNeighbors = aliveNeighbors.remove(time).get
            if (alive==1 && (currentAliveNeighbors > 3 || currentAliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && currentAliveNeighbors ==3) {
                alive = 1
            }
            connectedAgents.map(x => 
              x.asInstanceOf[Cell]).foreach(v => callAndForget(v.tell(time+1, alive), 1)
            )
            waitRounds(1)
        }
    }
}