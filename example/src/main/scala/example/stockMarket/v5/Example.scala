package example
package stockMarket
package v5

import scala.collection.mutable.ListBuffer

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(totalMarkets: Int, tradersPerMarket: Int, interval: Int): List[Actor] = {
            val allAgents: ListBuffer[Actor] = new ListBuffer[Actor]
            val initialWealth: Double = 1000
            val interestRate: Double = 0.001

            Range(0, totalMarkets).foreach(i => {
                val traders = (1 to tradersPerMarket).map(x => new Trader(initialWealth, interestRate, interval))
                allAgents.appendAll(traders)
                val market = new Market(interval)
                market.connectedAgentIds = traders.map(i => i.id.toInt).toList
                allAgents.append(market)
            })

            allAgents.toList
        }
    }
    
    val cls1: ClassWithObject[Market] = Market.reflect(IR)
    val cls2: ClassWithObject[Trader] = Trader.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}