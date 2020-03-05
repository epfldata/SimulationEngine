package meta.example.TwoPL_example
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.{dbg_lift, lift}

@lift
class SharedObject() extends Actor{
  var secret: String = ""

  def get(): String = secret

  def main(): Unit = {
    while (true) {
//      println("The secret word is " + secret)
      waitTurns(1)
    }
  }
}
