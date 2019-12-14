package meta.example.supermarket

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.example.supermarket.goods.ItemTrait
import squid.quasi.lift

@lift
class Supermarket extends Actor with ItemTrait {

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    println("Record waste at supermarket")
    updateWasteSummary(category, priceUnit, isSold)
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      println("Waste summary: " + wasteSummary)
      SpecialInstructions.waitTurns(1)
    }
  }
}