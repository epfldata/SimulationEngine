package example
package epidemic.evalNPI

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(populationsPerCountry: List[Int], p: Double): List[Actor] = {
            val totalCountries: Int = populationsPerCountry.size
            val dayUnit: Int = 10
            val countries: ListBuffer[Country] = ListBuffer[Country]()

            val allAgents = populationsPerCountry.flatMap(population => {
                val x = new Country((population * 0.01).toInt, dayUnit)
                val citizens = (1 to population).map(c => {
                    val p = new Person(Random.nextInt(90) + 10, dayUnit)
                    p.country = x 
                    p
                }).toList
                // Connect citizens with a random graph
                lib.Graph.ErdosRenyiGraph(citizens, p)
                // Random seeds of infected people
                (0 to (Random.nextInt(10)+4)).foreach(_ => {
                    citizens(Random.nextInt(population)).health = "Infectious"
                })
                x.citizens = citizens
                countries.append(x)
                x :: citizens
            })

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