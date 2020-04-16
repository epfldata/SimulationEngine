package meta.example.nb_methods_example
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val obj2: Object2 = new Object2
    val obj3: Object3 = new Object3
    val obj1: Object1 = new Object1(obj2, obj3)

    List(obj1, obj2, obj3)
  }
}