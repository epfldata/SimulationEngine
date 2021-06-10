package example
package epidemic

object MainInit {
    val liftedMain = custMacros.liftMethod {
        def apply(populationSize: Int): List[Actor] = {
            val infectiousSeed = 4
            val perCapitaContacts = 6

            val seed = (1 to infectiousSeed).map(_ => {
                val p = new Person(perCapitaContacts, scala.util.Random.nextInt(90)+1)
                // p.health = Infectious
                p.health = example.epidemic.Infectious
                p
            }).toList

            val people = (1 to (populationSize - infectiousSeed)).map(x => {
                new Person(Random.nextInt(perCapitaContacts), scala.util.Random.nextInt(90)+1)
            }).toList ::: seed

            (1 to populationSize).foreach(i =>
                people(i-1).connection = people.toList
            )

            people.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)

  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}