package example
package epidemic.evalNPI

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(populationsPerCountry: List[Int], p: Double, sbm: Boolean, blocks: Int): List[Actor] = {
            val totalCountries: Int = populationsPerCountry.size
            val dayUnit: Int = 10 
            val countries: ListBuffer[Country] = ListBuffer[Country]()
            val allCitizens: ListBuffer[Person] = ListBuffer[Person]()

            val allAgents = populationsPerCountry.flatMap(population => {
                val x = new Country((population * 0.01).toInt, dayUnit)
                val citizens = (1 to population).map(c => {
                    val p = new Person(Random.nextInt(90) + 10, dayUnit)
                    p.country = x 
                    p
                })

                // Stochastic block model (50 blocks, q=0)
                if (sbm){
                    (0 to blocks-1).foreach(i => {
                        lib.Graph.ErdosRenyiGraph(citizens.slice(i*population/blocks, (i+1)*population/blocks), p)
                    })
                } else {
                    allCitizens.appendAll(citizens)
                }
                                
                // Random seeds of infected people
                (0 to (Random.nextInt(10)+4)).foreach(_ => {
                    citizens(Random.nextInt(population)).health = "Infectious"
                })
                x.citizens = citizens.toList
                countries.append(x)
                x :: citizens.toList
            })

            // Erdos-Renyi model
            if (!sbm) {
                lib.Graph.ErdosRenyiGraph(allCitizens, p)
            }
           
            Range(0, totalCountries).foreach(x => {
                val neighborCountries = Random.nextInt(totalCountries)
                countries(x).asInstanceOf[Country].otherCountries = Range(0, neighborCountries)
                    .map(_ => Random.nextInt(totalCountries))
                    .filterNot(i => i==x)
                    .map(k => countries(k).asInstanceOf[Country]).toList
            })

            allAgents
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val cls2: ClassWithObject[Country] = Country.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1, cls2), Some(mainClass))
}