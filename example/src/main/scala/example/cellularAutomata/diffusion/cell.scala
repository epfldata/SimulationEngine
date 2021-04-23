package example
package diffusion

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

@lift
class Cell(var color: Double) extends Actor {

    var futures: List[Future[Double]] = List()

    def getValue: Double = color

    def main(): Unit = {
        while(true) {
            println("Color value: " + color)
            futures = connectedAgents.map(x => x.asInstanceOf[Cell]).map(v => asyncMessage(() => v.getValue))
            while (!(futures.nonEmpty && futures.forall(x => x.isCompleted))) {
                waitLabel(Turn,1)
                handleMessages()
            }
            val ans: List[Double] = futures.map(o => o.popValue.get).asInstanceOf[List[Double]]

            color = (ans.sum + color) / (1 + connectedAgents.size)
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}
