package meta.example.parameter_list_example.toy_factory_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.{lift, dbg_lift}

@lift
class toyFactory extends Actor {

  def genToy(cnt: Int): Unit = {
    new toy("Bob", cnt)
    new toy("Alice", cnt + 5)
  }

  def main(): Unit ={
    var cnt: Int = 1
    while (true) {
      genToy(cnt)
      cnt = cnt + 10
      waitTurns(1)
    }
  }
}
