package meta.example.nb_methods_example
import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = ListBuffer[Actor]()

    val obj2: Object2 = new Object2
    foo.append(obj2)

    (1 to 5).foreach(i => {
      val obj1: Object1 = new Object1(obj2)
      foo.append(obj1)
    })

    foo.toList
  }
}