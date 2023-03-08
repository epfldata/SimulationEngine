package example
package stockMarket
package v3

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import meta.runtime.{DoubleArrayMessage, Message}

@lift 
class Trader(var budget: Double, val interestRate: Double) extends Actor {

    var wealth: WealthManagement = null
    var estimatedWealth: Double = 0

    def main(): Unit = {
        wealth = new WealthManagement(budget, interestRate)
        while (true) {
            var m = receiveMessage()
            while (m.isDefined){
                var ans = m.get.asInstanceOf[DoubleArrayMessage].doubleArrayValue
                wealth.addDividends(ans(2))
                // reply message
                val msg = new Message()
                msg.value = wealth.takeAction(ans(1), List(ans(3).toInt, ans(4).toInt, ans(5).toInt)).toDouble
                sendMessage(ans(0).toLong, msg)
                m = receiveMessage()
            }
            waitRounds(1)
            wealth.addInterest()
        }
    }
}