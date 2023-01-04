package example
package epidemic.v3

import scala.util.Random

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(population: Int, p: Double, sbm: Boolean, blocks: Int): List[Actor] = {
            val citizens = (1 to population).map(c => { new Person(Random.nextInt(90) + 10) })

            if (!sbm){
                citizens.foreach(c => {
                    c.connectedAgentIds = Range(1, population).filter(i => {(i!= c.id) &&  Random.nextDouble() < p}).toList
                })
            } else {
                (0 to blocks-1).foreach(i => {
                    citizens.slice(i*population/blocks, (i+1)*population/blocks).foreach(j => {
                        j.connectedAgentIds = Range(i*population/blocks, (i+1)*population/blocks).filter(k => {(k!= j.id) &&  Random.nextDouble() < p}).toList
                    })
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