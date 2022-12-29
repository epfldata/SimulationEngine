package example
package epidemic.v3

import scala.util.Random

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(population: Int, p: Double, sbm: Boolean, blocks: Int): List[Actor] = {
            val citizens = (1 to population).map(c => { new Person(Random.nextInt(90) + 10) })

            if (sbm){
                (0 to blocks-1).foreach(i => {
                    lib.Graph.ErdosRenyiGraph.addIds(citizens.slice(i*population/blocks, (i+1)*population/blocks), p)
                })
            } else {
                lib.Graph.ErdosRenyiGraph.addIds(citizens, p)
            }

            // if (sbm){
            //     Range(0, blocks).foreach(i => {
            //         citizens.slice(i*population/blocks, (i+1)*population/blocks).foreach(c => {
            //             c.connectedAgentIds = Range(i*population/blocks, (i+1)*population/blocks).filter(k => {
            //                 (k!=c.id) && (p>Random.nextDouble())
            //             }).toList
            //         })
            //     })                
            // } else {
            //     citizens.foreach(c => {
            //         c.connectedAgentIds = Range(0, population).filter(k => {
            //             (k != c.id) && (p>Random.nextDouble())
            //         }).toList
            //     })
            // }
                            
            // Random seeds of infected people
            (0 to (Random.nextInt(10)+4)).foreach(_ => {
                citizens(Random.nextInt(population)).health = 1
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