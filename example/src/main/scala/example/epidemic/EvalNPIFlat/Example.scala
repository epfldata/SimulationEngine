package example
package epidemic.evalNPIFlat

import scala.util.Random

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(population: Int, p: Double, sbm: Boolean, blocks: Int): List[Actor] = {
            val dayUnit: Int = 10

            val citizens = (1 to population).map(c => { new Person(Random.nextInt(90) + 10, dayUnit) })

            // Stochastic block model (5 blocks, q=0)
            if (sbm){
                (0 to blocks-1).foreach(i => {
                    lib.Graph.ErdosRenyiGraph(citizens.slice(i*population/blocks, (i+1)*population/blocks), p)
                })
            } else {
                lib.Graph.ErdosRenyiGraph(citizens, p)
            }
                            
            // Random seeds of infected people
            (0 to (Random.nextInt(10)+4)).foreach(_ => {
                citizens(Random.nextInt(population)).health = "Infectious"
            })

            citizens.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}