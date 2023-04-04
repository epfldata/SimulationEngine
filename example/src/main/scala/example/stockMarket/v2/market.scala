package example
package stockMarket
package v2

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap, ListBuffer}

@lift 
class Market(val traders: List[Trader], val cfreq: Int) extends Actor {

    val stock: Stock = new Stock(0.01)
    private var futures: ListBuffer[Future[Int]] = new ListBuffer[Future[Int]]()
    private var marketState: List[Int] = null
    // Initial price
    var stockPrice: Double = 100
    var dividendPerShare: Double = 0

    def main(): Unit = {
        stock.priceAdjustmentFactor = 0.1 / traders.size
        while (true) {
            marketState = stock.updateMarketInfo(stockPrice, dividendPerShare)
            Range(0, cfreq).foreach(i => {
                val x = traders.map(x => {
                    asyncCall(x.action(stockPrice, dividendPerShare, marketState), 1)
                })
                futures.appendAll(x)
            })
            while (!futures.forall(x => x.isCompleted)){
                waitAndReply(1)
            }
            val x = futures.map(x => x.popValue.get).toList
            futures.clear()
            val buyOrders = x.count(_ == 1)
            val sellOrders = x.count(_ == 2)
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            dividendPerShare = stock.getDividend()
            println(buyOrders + ", " + sellOrders + ", " + dividendPerShare + ", " + stockPrice)
        }
    }
}

