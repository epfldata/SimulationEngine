package example
package stockMarket

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    private var wealth: Wealth = null
    private var estimatedWealth: Double = 0

    // At each round, respond to the market state with an action
    // true: to buy; false: to sell; None: no action
    @transparencyPropagating
    def action(stockPrice: Double, dividendPerShare: Double, marketState: List[Option[Boolean]]): Option[Boolean] = {
        // println("Stock price is " + stockPrice + " dividendPerShare is " + dividendPerShare)
        estimatedWealth = wealth.estimateWealth(stockPrice)
        wealth.addDividends(dividendPerShare)
        // println("Current wealth is " + estimatedWealth)
        if (stockPrice < wealth.cash){
            val buy: Boolean = Random.nextBoolean()
            if (buy) {
                wealth.buyStock(stockPrice)
            }
            Some(buy)
        } else {
            None
        }
    }

    def main(): Unit = {
        wealth = new Wealth(budget, interestRate)
        while (true) {
            waitAndReply(1)
            wealth.addInterest()
        }
    }
}