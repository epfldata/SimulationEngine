package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

// This object is mainly to show the behaviour of messaging for merged actors. Right now Send semantics is ill suited for the merged actor, e.g. if multiple replies come back at the same cycle, only the last message is processed and all previous ones are not, because each send pulls on the global response message and after receiving all messages, the response message variable contains only the last one. Fix can use similar techniques as async calls.
@lift
class object3() extends Actor {

  def introduction: Int = {
    println("This is object 3")
    98
  }

  def main(): Unit = {
    while(true){
      waitTurns(1)
    }
  }
}
