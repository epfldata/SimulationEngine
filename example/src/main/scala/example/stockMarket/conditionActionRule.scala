package example
package stockMarket

sealed abstract class conditionActionRule() {
    // 0: no action, 1: buy, 2: sell
    def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int = ???
}

object conditionActionRule {
    val buy: Int = 1
    val sell: Int = 2
    val noAction: Int = 0
  // Buy if the dividend increases and sell if the dividend decreases
  final case object Rule1 extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int ={
        if (marketState(0)==1 && stockPrice < cash){
            buy
        } else if (marketState(0)==2 && shares >= 1) {
            sell
        } else {
            noAction
        }
    }    
  }

  // Buy if 100-day average decreases and sell if 10-day average increases
// If both conditions are met, prioritize sell
  final case object Rule2 extends conditionActionRule {
    override def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int ={
        if (marketState(1) == 1 && shares >= 1){
            sell
        } else if (stockPrice < cash && marketState(2) == 2){
            buy
        } else {
            noAction
        }
    }
  }

  // Buy if 10-day average decreases and sell if 10-day average increases
  final case object Rule3 extends conditionActionRule {
        override def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int ={
        if (marketState(1) == 2 && stockPrice < cash){
            buy
        } else if (marketState(1) == 1 && shares >= 1){
            sell
        } else {
            noAction
        }
    }
  }

    // Random buy and sell
  final case object Rule4 extends conditionActionRule {
        override def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int ={
        if (Random.nextBoolean){
            if (stockPrice < cash) {
                buy
            } else {
                noAction
            }
        } else {
            if (shares >= 1) {
                sell
            } else {
                noAction
            }
        }
    }
  }

  // Buy if 50-day average decreases and sell if 50-day average increases
// If both conditions are met, prioritize sell
  final case object Rule5 extends conditionActionRule {
        override def eval(stockPrice: Double, marketState: List[Int], cash: Double, shares: Double): Int ={
        if (marketState(2) == 1 && shares >= 1){
            sell
        } else if (marketState(2) == 2 && stockPrice < cash){
            buy
        } else {
            noAction
        }
    }
  }
}