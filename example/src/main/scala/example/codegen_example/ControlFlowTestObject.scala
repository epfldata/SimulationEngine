package example
package codegen_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class ControlFlowTestObject extends Actor {

  var x = 0
  var y = 0

  def main(): Unit = {
    while (true) {
      if (x < 0) {
        waitLabel(Turn,1)
      } else {
        if (x < 0) {
          waitLabel(Turn,1)
        }
      }
      waitLabel(Turn,1)
    }
  }

}
