package meta.example.monitor_example.builtin_monitor
import meta.deep.runtime.{Actor, Monitor}
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: object1 = new object1
    val bar: object2 = new object2

    foo.monitor.initTimeseries("Infectious", "Recovered")
    List(foo, bar)
  }
}
