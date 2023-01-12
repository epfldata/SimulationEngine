package example
package stockMarket.v4

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}
import meta.runtime.{Message, DoubleArrayMessage}

@lift 
class Market() extends Actor {

    val stock: Stock = new Stock(0.01)
    private var futures: List[Future[Int]] = null
    private var marketState: List[Int] = null
    // Initial price
    var stockPrice: Double = 100
    var dividendPerShare: Double = 0
    var buyOrders: Int = 0
    var sellOrders: Int = 0

    // val foo: ListBuffer[Double] = new ListBuffer[Double]()

    def main(): Unit = {
        stock.priceAdjustmentFactor = 0.1 / connectedAgentIds.size
        while (true) {
            marketState = stock.updateMarketInfo(stockPrice, dividendPerShare)
            connectedAgentIds.foreach(i => {
                val msg = new DoubleArrayMessage()
                // foo.appendAll()
                // foo.appendAll()
                msg.doubleArrayValue = List(id.toDouble, stockPrice, dividendPerShare) ::: marketState.map(j => j.toDouble)
                sendMessage(i, msg)
            })
            // foo.clear()

            waitRounds(2)
            
            var m = receiveMessage()
            while (m.isDefined){
                var ans = m.get.value
                if (ans == 1) {
                    buyOrders = buyOrders + 1
                } 
                if (ans == 2) {
                    sellOrders = sellOrders + 1
                }
                m = receiveMessage()
            }
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            dividendPerShare = stock.getDividend()
            println(buyOrders + ", " + sellOrders + ", " + dividendPerShare + ", " + stockPrice)
            buyOrders = 0
            sellOrders = 0
        }
    }
}

