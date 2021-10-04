package example
package segregation

object Example extends App {

    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int, population: Int): List[Actor] = {
            val world: WorldMap = new WorldMap(width, height)

            val populationSize: Int = population
            val toleranceThreshold: Double = 0.5

            val people = (1 to populationSize).map(i => {
                val p = if (scala.util.Random.nextBoolean()){
                    new Person(world, 0, toleranceThreshold)
                } else {
                    new Person(world, 1, toleranceThreshold)
                }
                p.location = i-1
                p
            })

            people.foreach(x => {
                x.connectedAgents = people.map(x => (x.id, x)).toMap
            })

            world :: people.toList
        }
    }

    val cls1: ClassWithObject[Person] = Person.reflect(IR)
    val cls2: ClassWithObject[WorldMap] = WorldMap.reflect(IR)

    compileSims(List(cls1, cls2), Some(liftedMain))
}