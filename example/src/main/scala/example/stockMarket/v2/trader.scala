package example
package stockMarket
package v2

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    var wealth: WealthManagement = null
    var estimatedWealth: Double = 0
    
    // At each round, respond to the market state with an action
    // true: to buy; false: to sell; None: no action
    @transparencyPropagating
    def action(stockPrice: Double, dividendPerShare: Double, marketState: List[Int]): Int = {
        wealth.addDividends(dividendPerShare)
        wealth.takeAction(stockPrice, marketState)
    }

    def main(): Unit = {
        wealth = new WealthManagement(budget, interestRate)
        while (true) {
            waitAndReply(1)
            wealth.addInterest()
        }
    }
}