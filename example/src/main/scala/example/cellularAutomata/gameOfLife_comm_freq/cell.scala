package example
package gameOfLifeCommFreq

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.runtime.Message
// import meta.io._

/**
  * Conway's game of life
  * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
  *
  * @param alive
  */
@lift
class Cell(var alive: Int, val cfreq: Int) extends Actor {

  var aliveNeighbors: Int = 0
    def main(): Unit = {
        while(true) {
            var m: Option[Message] = receiveMessage()
            aliveNeighbors = 0
            
            while (m.isDefined){
              aliveNeighbors = aliveNeighbors + m.get.value.toInt
              m = receiveMessage()
            }

            if (alive==1 && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
              alive = 0
            } else if (alive==0 && aliveNeighbors==3) {
                alive = 1
            }

            connectedAgentIds.foreach(i => {
              // default 
              // Range(0, cfreq).foreach(j => {
              //   val msg = new Message()
              //   msg.value = alive.toDouble
              //   sendMessage(i, msg)
              // })
              // batching  
              sendMessages.getOrElseUpdate(i, new ListBuffer[Message]()).appendAll(Range(0, cfreq).map(i => {
                  val msg = new Message()
                  msg.value = alive.toDouble
                  msg
              }))
            })
            waitRounds(1)
        }
    }
}
