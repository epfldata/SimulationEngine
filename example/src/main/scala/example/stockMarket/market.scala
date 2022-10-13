package example
package stockMarket

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}

@lift 
class Market(val traders: List[Trader]) extends Actor {

    val stock: Stock = new Stock(0.01)
    private var futures: List[Future[Int]] = null
    private var marketState: List[Int] = null
    // Initial price
    var stockPrice: Double = 100
    var dividendPerShare: Double = 0

    def main(): Unit = {
        stock.priceAdjustmentFactor = 0.1 / traders.size
        while (true) {
            marketState = stock.updateMarketInfo(stockPrice, dividendPerShare)
            futures = traders.map(x => asyncCall(x.action(stockPrice, dividendPerShare, marketState), 1))
            while (!futures.forall(x => x.isCompleted)){
                waitAndReply(1)
            }
            val x = futures.map(x => x.popValue.get)
            val buyOrders = x.count(_ == 1)
            val sellOrders = x.count(_ == 2)
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            dividendPerShare = stock.getDividend()
            println(buyOrders + ", " + sellOrders + ", " + dividendPerShare + ", " + stockPrice)
        }
    }
}

