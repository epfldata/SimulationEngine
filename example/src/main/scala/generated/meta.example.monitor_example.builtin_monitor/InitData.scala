package generated.meta.example.monitor_example.builtin_monitor

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val foo_0 = new generated.meta.example.monitor_example.builtin_monitor.object1();
  val bar_1 = new generated.meta.example.monitor_example.builtin_monitor.object2();
  val x_2 = foo_0.monitor;
  x_2.initTimeseries("Infectious", "Recovered");
  scala.collection.immutable.List.apply[meta.deep.runtime.Actor](foo_0, bar_1)
}}
}