package example
package segregation

import lib.Bot.LoggerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions.Group
import lib.Grid.Torus2D

@lift
class MainInit {
  def main(): List[Actor] = {
    var foo: List[Actor] = List()

    val world: WorldMap = new WorldMap(50, 50)

    foo = world :: foo

    val populationSize: Int = 1125
    val toleranceThreshold: Double = 0.5

    foo = foo ++ (1 to populationSize).toList.flatMap(i =>
      List(new Person(world, 0, toleranceThreshold),
        new Person(world, 1, toleranceThreshold))
    )

    // Setup the wait label
    SimRuntime.registerLabel(Group("People"), populationSize*2)
    foo
  }
}