package example
package epidemic

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(populationsPerCountry: List[Int]): List[Actor] = {
            val totalCountries: Int = populationsPerCountry.size

            val countries: ListBuffer[Country] = ListBuffer[Country]()

            val allAgents = populationsPerCountry.flatMap(population => {
                val x = new Country((population * 0.01).toInt)
                val citizens = (1 to population).map(c => {
                    val p = new Person(Random.nextInt(90) + 10)
                    p.country = x 
                    p
                }).toList
                citizens.foreach(c => {
                    c.connections = (0 to Random.nextInt(20)).map(x => {
                        citizens(Random.nextInt(population))
                    }).toList
                })
                // Random seeds of infected people
                (0 to (Random.nextInt(10)+4)).foreach(_ => {
                    citizens(Random.nextInt(population)).health = example.epidemic.Infectious
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