package example
package stockMarket.v1

class conditionActionRule {
    var strength: Double = 0
    var label: Int = 0
    def eval(stockPrice: Double, marketState: List[Int]): Int = ???
    def feedback(success: Boolean): Unit = {
        if (success){
            strength += 1
        } else {
            strength -= 1
        }
    }
}

// 0: None, 1: true, 2: false
// Buy if the dividend increases and sell if the dividend decreases
class rule1(wealth: WealthManagement) extends conditionActionRule {
    label = 1
    override def eval(stockPrice: Double, marketState: List[Int]): Int ={
        if (marketState(0)==1 && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            1
        } else if (marketState(0)==2 && wealth.shares >= 1) {
            wealth.sellStock(stockPrice)
            2
        } else {
            0
        }
    }
}

// Buy if 100-day average decreases and sell if 10-day average increases
// If both conditions are met, prioritize sell
class rule2(wealth: WealthManagement) extends conditionActionRule {
    label = 2
    override def eval(stockPrice: Double, marketState: List[Int]): Int ={
        if (marketState(1) == 1 && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            2
        } else if (stockPrice < wealth.cash && marketState(2) == 2){
            wealth.buyStock(stockPrice)
            1
        } else {
            0
        }
    }
}

// Buy if 10-day average decreases and sell if 10-day average increases
class rule3(wealth: WealthManagement) extends conditionActionRule {
    label = 3
    override def eval(stockPrice: Double, marketState: List[Int]): Int ={
        if (marketState(1) == 2 && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            1
        } else if (marketState(1) == 1 && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            2
        } else {
            0
        }
    }
}

// Random buy and sell
class rule4(wealth: WealthManagement) extends conditionActionRule {
    label = 4
    override def eval(stockPrice: Double, marketState: List[Int]): Int ={
        if (Random.nextBoolean){
            if (stockPrice < wealth.cash) {
                wealth.buyStock(stockPrice)
                1
            } else {
                0
            }
        } else {
            if (wealth.shares >= 1) {
                wealth.sellStock(stockPrice)
                2
            } else {
                0
            }
        }
    }
}

// Buy if 50-day average decreases and sell if 50-day average increases
// If both conditions are met, prioritize sell
class rule5(wealth: WealthManagement) extends conditionActionRule {
    label = 5
    override def eval(stockPrice: Double, marketState: List[Int]): Int ={
        if (marketState(2) == 1 && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            2
        } else if (marketState(2) == 2 && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            1
        } else {
            0
        }
    }
}