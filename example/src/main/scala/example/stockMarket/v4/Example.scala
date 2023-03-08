package example
package stockMarket
package v4

import scala.collection.mutable.ListBuffer

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        // Assume 1 market agent
        def apply(tradersPerMachine: Int, machineId: Int, totalMachines: Int): List[Actor] = {
            val allAgents: ListBuffer[Actor] = new ListBuffer[Actor]
            val initialWealth: Double = 1000
            val interestRate: Double = 0.001

            // Assume 1 market for now
            if (machineId == 0) {
                val market = new Market()
                allAgents.append(market)
                val lastAgentId = meta.runtime.Actor.lastAgentId.toInt + 1
                val traders = (1 to tradersPerMachine).map(x => new Trader(initialWealth, interestRate))
                market.connectedAgentIds = Range(lastAgentId, tradersPerMachine*totalMachines+lastAgentId).toList
                allAgents.appendAll(traders)
            } else {
                meta.runtime.Actor.lastAgentId = machineId * tradersPerMachine
                val traders = (1 to tradersPerMachine).map(x => new Trader(initialWealth, interestRate))
                allAgents.appendAll(traders)
            }
            allAgents.toList
        }
    }
    
    val cls1: ClassWithObject[Market] = Market.reflect(IR)
    val cls2: ClassWithObject[Trader] = Trader.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}