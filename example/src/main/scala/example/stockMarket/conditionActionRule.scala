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

// Buy randomly
class rule1(wealth: WealthManagement) extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (stockPrice < wealth.cash && Random.nextBoolean()){
            wealth.buyStock(stockPrice)
            Some(true)
        } else {
            None
        }
    }
}

// Buy randomly and sell if 10-day average increases
class rule2(wealth: WealthManagement) extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Option[Boolean]]): Option[Boolean] ={
        if (stockPrice < wealth.cash && Random.nextBoolean()){
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