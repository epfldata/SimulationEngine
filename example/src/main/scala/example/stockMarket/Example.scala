package example
package stockMarket

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(totalTraders: Int): List[Actor] = {
            val traders = (1 to totalTraders).map(x => new Trader(1000, 0.0001)).toList
            val market = new Market(traders)
            market :: traders
        }
    }
    
    val cls1: ClassWithObject[Market] = Market.reflect(IR)
    val cls2: ClassWithObject[Trader] = Trader.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}