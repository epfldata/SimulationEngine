package example
package stockMarket

import conditionActionRule._

class WealthManagement(var initWealth: Double, val interestRate: Double) {
    var bankDeposit = initWealth * 0.5
    var cash = initWealth - bankDeposit
    var shares: Double = 0
    
    val rules: List[conditionActionRule] = List(Rule1, Rule2, Rule3, Rule4, Rule5)
    // Strength of the rules
    val strength: Array[Int] = Array.ofDim[Int](rules.size)

    // index to rules
    var currentAction: Int = Random.nextInt(rules.size)  // randomly initialize
    var currentWealth: Double = initWealth

    def buyStock(stockPrice: Double): Unit = {
        shares += 1
        cash -= stockPrice
    }

    def sellStock(stockPrice: Double): Unit = {
        assert(shares >= 1)
        shares -= 1
        cash += stockPrice
    }

    def estimateWealth(stockPrice: Double): Double = {
        stockPrice * shares + bankDeposit + cash
    }

    def addInterest(): Unit = {
        bankDeposit = bankDeposit * (1+interestRate)
    }

    def addDividends(dividendPerShare: Double): Unit = {
        assert(dividendPerShare >= 0)
        // dividend in shares
        // shares = shares * (1 + dividendPerShare)

        // dividend in cash
        cash += shares * dividendPerShare
    }

    def takeAction(stockPrice: Double, marketState: List[Int]): Int = {
        // Evaluate the previous rule
        val updatedWealth = estimateWealth(stockPrice)
        // Feedback
        if (updatedWealth > currentWealth) {
            strength(currentAction) += 1
        }
        // Find the next rule
        // Add some randomness to avoid one dominating action
        if (Random.nextInt(10) < 3) {
            currentAction = Random.nextInt(rules.size)
        } else {
            currentAction = strength.indexOf(strength.max)
        }
        // println("Next action is " + nextAction)
        currentWealth = updatedWealth
        // println("Current wealth is " + currentWealth)
        val action = rules(currentAction).eval(stockPrice, marketState, cash, shares)
        if (action == buy) {
            buyStock(stockPrice)
        } else if (action == sell) {
            sellStock(stockPrice)
        }
        action
    }
}