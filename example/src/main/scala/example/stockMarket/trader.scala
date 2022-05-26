package example
package stockMarket

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    private var wealth: WealthManagement = null
    private var estimatedWealth: Double = 0
    
    // At each round, respond to the market state with an action
    // true: to buy; false: to sell; None: no action
    @transparencyPropagating
    def action(stockPrice: Double, dividendPerShare: Double, marketState: List[Option[Boolean]]): Option[Boolean] = {
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