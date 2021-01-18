package example
package segregation

import lib.Bot.LoggerBotTimeseries

object segregationExample extends App {

  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val cls2: ClassWithObject[WorldMap] = WorldMap.reflect(IR)
  val cls3: ClassWithObject[LoggerBotTimeseries] = LoggerBotTimeseries.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2, cls3), Some(mainClass))
}