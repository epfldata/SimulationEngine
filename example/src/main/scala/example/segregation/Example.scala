package example
package segregation

import lib.Bot.LoggerBotTimeseries


object Example extends App {

    val liftedMain = custMacros.liftMethod{
        def apply(width: Int, height: Int, population: Int): List[Actor] = {
            var foo: List[Actor] = List()

            val world: WorldMap = new WorldMap(width, height)

            foo = world :: foo

            val populationSize: Int = population
            val toleranceThreshold: Double = 0.5

            foo = foo ++ (1 to populationSize).toList.flatMap(i =>
            List(new Person(world, 0, toleranceThreshold),
                new Person(world, 1, toleranceThreshold))
            )

            foo
        }
    }

    val cls1: ClassWithObject[Person] = Person.reflect(IR)
    val cls2: ClassWithObject[WorldMap] = WorldMap.reflect(IR)
    val cls3: ClassWithObject[LoggerBotTimeseries] = LoggerBotTimeseries.reflect(IR)

    compileSims(List(cls1, cls2, cls3), Some(liftedMain))
}