package example
package stockMarket

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(totalAgents: Int): List[Actor] = {
<<<<<<< HEAD
            val traders = (1 to totalAgents-1).map(x => new Trader(1000, 0.001)).toList
=======
            val traders = (2 to totalAgents).map(x => new Trader(1000, 0.001)).toList
>>>>>>> 3d88391db18c82bbf686edc5971c75abad80e8ae
            val market = new Market(traders)
            market :: traders
        }
    }
    
    val cls1: ClassWithObject[Market] = Market.reflect(IR)
    val cls2: ClassWithObject[Trader] = Trader.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}