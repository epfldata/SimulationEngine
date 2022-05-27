package example
package stockMarket

class conditionActionRule {
    var strength: Double = 0
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
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (stockPrice < wealth.cash && marketState(0)==Some(true)){
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
class rule2(wealth: WealthManagement) extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (stockPrice < wealth.cash && marketState(2) == Some(false)){
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

// Buy if 10-day average decreases and sell if 10-day average increases
class rule3(wealth: WealthManagement) extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (stockPrice < wealth.cash && marketState(1) == Some(false)){
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