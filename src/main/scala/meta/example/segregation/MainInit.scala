package meta.example.segregation

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = ListBuffer()

    val worldMap = new WorldMap()
    foo.append(worldMap)
    (1 to 1150).foreach(i => {
      val p1 = new Person(worldMap, 0)
      val p2 = new Person(worldMap, 1)
      foo.append(p1, p2)
    })

    worldMap.monitor.initTimeseries("Segregation")
    foo.toList
  }
}