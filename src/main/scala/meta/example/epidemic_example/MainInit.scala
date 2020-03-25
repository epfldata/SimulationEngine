package meta.example.epidemic_example

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer
import scala.util.Random

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

//    val patient0: Person = new Person(30, dist)

    var cnt: Int = 0
    val population: Int = 5
    val averageAge: Int = 18

    val dist: School = new School(population, averageAge)
    l.append(dist)
    while (cnt < population) {
      val person: Person = new Person(Random.nextInt(2*averageAge), dist)
      cnt = cnt + 1
      l.append(person)
    }

    l.toList
  }
}