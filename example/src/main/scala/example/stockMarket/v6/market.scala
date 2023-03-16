package example
package stockMarket
package v6

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap, ListBuffer}

@lift 
class Market(val traders: List[Trader]) extends Actor {

    val stock: Stock = new Stock(0.01)
    private var futures: List[Future[Int]] = null
    private var marketState: List[Int] = null
    // Initial price
    var stockPrice: Double = 100
    var dividendPerShare: Double = 0
    val bufferedActions: ListBuffer[Int] = ListBuffer[Int]()

    @transparencyPropagating
    def traderAction(action: Int): Int = {
        bufferedActions.append(action)
        0
    }

    def main(): Unit = {
        markAllowDirectAccess("traderAction")
        stock.priceAdjustmentFactor = 0.1 / traders.size
        while (true) {
            marketState = stock.updateMarketInfo(stockPrice, dividendPerShare)
            // notify traders of the latest market state
            traders.foreach(x => callAndForget(x.inform(stockPrice, dividendPerShare, marketState), 1))
            // wait for traders to 
            waitAndReply(1)
            val buyOrders = bufferedActions.count(_ == 1)
            val sellOrders = bufferedActions.count(_ == 2)
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            dividendPerShare = stock.getDividend()
            // Merge
            bufferedActions.clear()
            waitAndReply(1)
        }
    }
}

