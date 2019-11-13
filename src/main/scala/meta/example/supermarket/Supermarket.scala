package meta.example.supermarket

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Supermarket() extends Actor {
  var totalSupply = Category(vegie=500, meat=500, snacks=500, grain=500)

  def sell(shoppingList: Category): Unit = {
    totalSupply.vegie = totalSupply.vegie - shoppingList.vegie
    totalSupply.meat = totalSupply.meat - shoppingList.meat
    totalSupply.snacks = totalSupply.snacks - shoppingList.snacks
    totalSupply.grain = totalSupply.grain - shoppingList.grain
  }

  def viewSupply(): Unit = {
    println("update: Total food now is "+totalSupply)
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      viewSupply()
      SpecialInstructions.waitTurns(1)
    }
  }
}