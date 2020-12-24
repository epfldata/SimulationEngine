package example
package segregation

import squid.quasi.lift

@lift
class MainInit {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = new ListBuffer()

    val worldMap = new WorldMap()
    foo.append(worldMap)
    val populationSize: Int = 1125

    (1 to populationSize).foreach(i => {
      val p1 = new Person(worldMap, 0)
      val p2 = new Person(worldMap, 1)
      foo.append(p1, p2)
    })

    // Setup the wait label
    SimRuntime.waitLabels("People") = populationSize*2
    // Setup the monitor, if desired
//    worldMap.monitor.initTimeseries("Segregation")
    foo.toList
  }
}