package example
package monitor_example.agent_monitor

import squid.quasi.lift

@lift
class MainInit {
  def main(): List[Actor] = {
    val monitor: monitorSim = new monitorSim
    val foo: object1 = new object1(monitor)

    List(monitor, foo)
  }
}
