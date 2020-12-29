package example
package segregation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions.Group

@lift
class MainInit {
  def main(): List[Actor] = {
    var foo: List[Actor] = List()

    val worldMap = new WorldMap()
    foo = worldMap :: foo

    val populationSize: Int = 1125

    foo = foo ++ (1 to populationSize).toList.flatMap(i =>
      List(new Person(worldMap, 0), new Person(worldMap, 1))
    )

    // Setup the wait label
    SimRuntime.registerLabel(Group("People"), populationSize*2)
    foo
  }
}