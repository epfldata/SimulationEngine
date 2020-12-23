package example
package nb_methods_example
import squid.quasi.lift

@lift
class MainInit {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = new ListBuffer[Actor]()

    val obj2: Object2 = new Object2
    foo.append(obj2)

    (1 to 5).foreach(i => {
      val obj1: Object1 = new Object1(obj2)
      foo.append(obj1)
    })

    foo.toList
  }
}