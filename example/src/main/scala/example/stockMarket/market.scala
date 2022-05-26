package example
package stockMarket

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}

@lift 
class Market(val traders: List[Trader]) extends Actor {

    val stock: Stock = new Stock(0.001)
    private var futures: List[Future[Option[Boolean]]] = null
    private var marketState: List[Option[Boolean]] = null
    // Initial price
    var stockPrice: Double = 100

    def main(): Unit = {
        while (true) {
            marketState = stock.addPrice(stockPrice)
            futures = traders.map(x => asyncSend(x.action(marketState)))
            while (!futures.forall(x => x.isCompleted)){
                waitAndReply(1)
            }
            val x = futures.map(x => x.popValue.get)
            val buyOrders = x.count(_ == Some(true))
            val sellOrders = x.count(_ == Some(false))
            // println("Current buy orders are " + buyOrders + " sell orders are " + sellOrders)
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            // println("Current stock price is " + stockPrice)
        }
    }
}

