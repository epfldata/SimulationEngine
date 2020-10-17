package generated.meta.example.monitor_example.agent_monitor

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val monitor_0 = new generated.meta.example.monitor_example.agent_monitor.monitorSim();
  val foo_1 = new generated.meta.example.monitor_example.agent_monitor.object1(monitor_0);
  scala.collection.immutable.List.apply[meta.deep.runtime.Actor](monitor_0, foo_1)
}}
}