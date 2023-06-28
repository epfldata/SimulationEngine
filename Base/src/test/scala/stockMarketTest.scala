package simulation.base
package test

import org.scalatest.FlatSpec
import API._
import meta.runtime.Actor
import example.stockMarket.{WealthManagement, Stock}

class stockMarketTest extends FlatSpec {
    
    class Trader(var budget: Double, val interestRate: Double) {
        var wealth: WealthManagement = null
        var estimatedWealth: Double = 0
        
        // At each round, respond to the market state with an action
        // true: to buy; false: to sell; None: no action
        def action(stockPrice: Double, dividendPerShare: Double, marketState: List[Int]): Int = {
            wealth.addDividends(dividendPerShare)
            wealth.addInterest()
            wealth.takeAction(stockPrice, marketState)
        }
        wealth = new WealthManagement(budget, interestRate)
    }

    class Market(val totalTraders: Int) extends Actor {
        val traders = Range(0, totalTraders).map(i => new Trader(1000, 0.001))
        val stock: Stock = new Stock(0.01)
        private var marketState: List[Int] = null
        // Initial price
        var stockPrice: Double = 100
        var dividendPerShare: Double = 0

        stock.priceAdjustmentFactor = 0.1 / traders.size

        override def run(): Int = {
            marketState = stock.updateMarketInfo(stockPrice, dividendPerShare)
            val actions = traders.map(x => x.action(stockPrice, dividendPerShare, marketState))
            val buyOrders = actions.count(_ == 1)
            val sellOrders = actions.count(_ == 2)
            stockPrice = stock.priceAdjustment(buyOrders, sellOrders)
            // println(stockPrice)
            dividendPerShare = stock.getDividend()
            1
        }
    }

    f"The inlined/tiled stock market experiment" should f"run" in {
        val agents = new Market(9999)
        val start = System.currentTimeMillis()
        val snapshot1 = API.Simulate(Vector(agents), 100)
        val end = System.currentTimeMillis()
        println((end-start)/200)
    }
}