package meta.example.parameter_list_example.temperature_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val point1: molecule = new molecule(100, null)
    val point2: molecule = new molecule(20, point1)

    List(point1, point2)
  }
}