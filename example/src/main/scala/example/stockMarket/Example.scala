package example
package stockMarket

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(): List[Actor] = {
            val traders = (1 to 4).map(x => new Trader(100, 0.01)).toList
            val market = new Market(traders)
            market :: traders
        }
    }
    
    val cls1: ClassWithObject[Market] = Market.reflect(IR)
    val cls2: ClassWithObject[Trader] = Trader.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}