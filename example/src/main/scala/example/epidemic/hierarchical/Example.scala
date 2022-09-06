package example
package epidemic.hierarchical

import scala.util.Random
import scala.collection.mutable.ListBuffer

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(citySize: Int, cityPerCountry: Int, totalCountries: Int, p: Double, sbm: Boolean): List[Actor] = {
            val priorities = Range(0, totalCountries).map(i => Random.nextInt(3)+1)
            val countries: ListBuffer[Country] = ListBuffer[Country]()
            val cities: ListBuffer[Country] = ListBuffer[Country]()
            val allCitizens: ListBuffer[Person] = ListBuffer[Person]()

            val ans: ListBuffer[Actor] = ListBuffer[Actor]()

            (0 to totalCountries-1).foreach(nation => {
              val cities = (1 to cityPerCountry).map(city => {
                var locals = (1 to citySize).map(i => {
                  if (Random.nextInt(10)==1){
                    new Person(true)
                  } else {
                    new Person(false)
                  }
                })
                ans.appendAll(locals)
                lib.Graph.ErdosRenyiGraph(locals, p)
                val city = new City(locals.toList, 12)
                ans.append(city)
                city
              }).toList
              // cities.foreach(c => i.population.foreach(p =>))
              ans.append(new Country(cities, priorities(nation), 24))
            })

            lib.Graph.ErdosRenyiGraph(ans.filter(a => a.isInstanceOf[Person]), p)
            ans.toList
      }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val cls2: ClassWithObject[City] = City.reflect(IR)
  val cls3: ClassWithObject[Country] = Country.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1, cls2, cls3), Some(mainClass))
}