package example
package stockMarket

class conditionActionRule {
    var strength: Double = 0
    var label: Int = 0
    def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] = ???
    def feedback(success: Boolean): Unit = {
        if (success){
            strength += 1
        } else {
            strength -= 1
        }
    }
}

// Buy if the dividend increases and sell if the dividend decreases
class rule1(wealth: WealthManagement) extends conditionActionRule {
    label = 1
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (marketState(0)==Some(true) && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            Some(true)
        } else if (marketState(0)==Some(false) && wealth.shares >= 1) {
            wealth.sellStock(stockPrice)
            Some(false)
        } else {
            None
        }
    }
}

// Buy if 100-day average decreases and sell if 10-day average increases
// If both conditions are met, prioritize sell
class rule2(wealth: WealthManagement) extends conditionActionRule {
    label = 2
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (marketState(1) == Some(true) && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            Some(false)
        } else if (stockPrice < wealth.cash && marketState(2) == Some(false)){
            wealth.buyStock(stockPrice)
            Some(true)
        } else {
            None
        }
    }
}

// Buy if 10-day average decreases and sell if 10-day average increases
class rule3(wealth: WealthManagement) extends conditionActionRule {
    label = 3
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (marketState(1) == Some(false) && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            Some(true)
        } else if (marketState(1) == Some(true) && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            Some(false)
        } else {
            None
        }
    }
}

// Random buy and sell
class rule4(wealth: WealthManagement) extends conditionActionRule {
    label = 4
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (Random.nextBoolean){
            if (stockPrice < wealth.cash) {
                wealth.buyStock(stockPrice)
                Some(true)
            } else {
                None
            }
        } else {
            if (wealth.shares >= 1) {
                wealth.sellStock(stockPrice)
                Some(false)
            } else {
                None
            }
        }
    }
}

// Buy if 100-day average decreases and sell if 100-day average increases
// If both conditions are met, prioritize sell
class rule5(wealth: WealthManagement) extends conditionActionRule {
    label = 5
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (marketState(2) == Some(true) && wealth.shares >= 1){
            wealth.sellStock(stockPrice)
            Some(false)
        } else if (marketState(2) == Some(false) && stockPrice < wealth.cash){
            wealth.buyStock(stockPrice)
            Some(true)
        } else {
            None
        }
    }
}