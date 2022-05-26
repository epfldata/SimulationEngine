package example
package stockMarket

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    // At each round, respond to the market state with an action
    // true: to buy; false: to sell; None: no action
    @transparencyPropagating
    def action(marketState: List[Option[Boolean]]): Option[Boolean] = {
        Some(true)
    }

    def main(): Unit = {
        while (true) {
            waitAndReply(1)
        }
    }
}