package example
package epistemicLogicExamples
package RomeoJuliet

import squid.quasi.lift
import lib.EpistemicLogic._

@lift
class MainInit {
  def main(): List[Actor] = {

    val father: Capulet = new Capulet()

    val count: Count = new Count()
    count.lord = father

    val romeo: Romeo = new Romeo()
    val juliet: Juliet = new Juliet(romeo)

    romeo.lover = juliet
    father.daughter = juliet

    List(father, count, romeo, juliet)
  }
}

object RomeoJulietExample extends App {
// Construct an ontology log using a particular database
  // the database is our set of Sims in a simulation
  // e.g. A person lives in a house
  // (a person) -> (a house)  where ->: lives in
  // Not a general true statement, some people live in boats
  // But can be true for a given database (such as a company that provides housing for its employees)
  // Have a database regarding which your ontology log describes

  val cls1: ClassWithObject[Count] = Count.reflect(IR)
  val cls2: ClassWithObject[Capulet] = Capulet.reflect(IR)
  val cls3: ClassWithObject[Romeo] = Romeo.reflect(IR)
  val cls4: ClassWithObject[Juliet] = Juliet.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2, cls3, cls4), Some(mainClass))


}
