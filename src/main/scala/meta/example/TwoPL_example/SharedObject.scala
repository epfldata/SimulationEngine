package meta.example.TwoPL_example
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.{dbg_lift, lift}

//@dbg_lift
@lift
class SharedObject() extends Actor{
  var secret: String = ""
//  def hi: Array[String] = Array("Hello", "World!")

  def main(): Unit = {
    while (true) {
//      handleMessages()
      println("The current word is " + secret)
      waitTurns(1)
    }
  }
}
