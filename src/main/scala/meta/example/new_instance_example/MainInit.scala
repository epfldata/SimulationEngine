package meta.example.new_instance_example
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: object1 = new object1
    val bar: object2 = new object2
    List(foo, bar)
  }
}
