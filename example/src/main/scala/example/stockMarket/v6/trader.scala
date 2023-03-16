package example
package stockMarket
package v6

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    var wealth: WealthManagement = null
    var estimatedWealth: Double = 0
    var traderAction: Int = 0
    var market: Market = null

    // At each round, respond to the market state with an action
    // true: to buy; false: to sell; None: no action
    @transparencyPropagating
    def inform(stockPrice: Double, dividendPerShare: Double, marketState: List[Int]): Int = {
        wealth.addDividends(dividendPerShare)
        traderAction = wealth.takeAction(stockPrice, marketState)
        0
    }

    def main(): Unit = {
        markAllowDirectAccess("action")
        wealth = new WealthManagement(budget, interestRate)
        while (true) {
            callAndForget(market.traderAction(traderAction),1)
            waitAndReply(1)
            wealth.addInterest()
        }
    }
}