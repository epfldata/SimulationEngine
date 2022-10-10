package example
package gameOfLifeRPCOneSideDoubleBuffer

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}

@lift
class Cell(var alive: Int) extends Actor {    

    var aliveNeighbors1: Int = 0
    var aliveNeighbors2: Int = 0

    @transparencyPropagating
    def tell(key: Int, state: Int): Int = {
      if (key % 2 == 1){
        aliveNeighbors1 = aliveNeighbors1 + state
      } else {
        aliveNeighbors2 = aliveNeighbors2 + state 
      }
      0
    }
    
    def main(): Unit = {
        while(true) {
            if (time % 2 == 0) {
              aliveNeighbors1 = 0
            } else {
              aliveNeighbors2 = 0
            }
            handleRPC()
            if (time % 2 ==0){
              if (alive==1 && (aliveNeighbors1 > 3 || aliveNeighbors1 < 2)) {
                alive = 0
              } else if (alive==0 && aliveNeighbors1 ==3) {
                alive = 1
              }
            } else {
              if (alive==1 && (aliveNeighbors2 > 3 || aliveNeighbors2 < 2)) {
                alive = 0
              } else if (alive==0 && aliveNeighbors2 ==3) {
                alive = 1
              }
            }
            connectedAgents.map(x => 
              x.asInstanceOf[Cell]).foreach(v => callAndForget(v.tell(time+1, alive), 1)
            )
            barrierSync()
        }
    }
}