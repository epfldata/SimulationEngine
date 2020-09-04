package meta.example.codegen_example

import _root_.meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class InitClass {
  def main(): List[Actor] = {
    val m = new Market
    val f = new Farmer()
    f.market = m
    List(m, f)
  }
}
