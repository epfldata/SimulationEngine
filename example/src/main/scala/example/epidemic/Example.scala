package example
package epidemic.v1

import scala.util.Random

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(population: Int, p: Double, sbm: Boolean, blocks: Int): List[Actor] = {
            val citizens = (1 to population).map(c => { new Person(Random.nextInt(90) + 10) })

            if (!sbm){
                lib.Graph.ErdosRenyiGraph(citizens, p)
            } else {
                Range(0, blocks).foreach(i => {
                    lib.Graph.ErdosRenyiGraph(citizens.slice(i*population/blocks, (i+1)*population/blocks), p)
                })
            }

            citizens.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}