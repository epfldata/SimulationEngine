package example
package stockMarket

class Wealth(var initWealth: Double, val interestRate: Double) {
    var bankDeposit = initWealth * 0.5
    var cash = initWealth - bankDeposit
    var shares: Double = 0

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
        shares = shares * (1 + dividendPerShare)
    }
}