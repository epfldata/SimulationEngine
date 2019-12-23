package meta.example.supermarket

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Supermarket extends Actor with SummaryTrait {

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
