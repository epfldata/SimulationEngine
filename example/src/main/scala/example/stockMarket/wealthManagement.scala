package example
package stockMarket


class WealthManagement(var initWealth: Double, val interestRate: Double) {
    var bankDeposit = initWealth * 0.5
    var cash = initWealth - bankDeposit
    var shares: Double = 0
    var currentAction: conditionActionRule = null
    var currentWealth: Double = initWealth

    val rules: Set[conditionActionRule] = Set(new rule1(this), new rule2(this), new rule3(this), new rule4(this), new rule5(this))

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

    def takeAction(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] = {
        // Evaluate the previous rule
        val updatedWealth = estimateWealth(stockPrice)
        if (currentAction != null){
            currentAction.feedback(updatedWealth > currentWealth)
        }
        // Find the next rule
        val nextAction: conditionActionRule = rules.map(x => (x.strength, x)).toList.sortBy(x => x._1).head._2
        // println("Next action is " + nextAction)
        currentWealth = updatedWealth
        // println("Current wealth is " + currentWealth)
        currentAction = nextAction
        nextAction.eval(stockPrice, marketState)
    }
}