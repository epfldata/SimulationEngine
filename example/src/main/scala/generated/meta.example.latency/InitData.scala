package generated.meta.example.latency

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val foo_0 = scala.collection.mutable.ListBuffer.apply[meta.deep.runtime.Actor]();
  val server_1 = new generated.meta.example.latency.Server(0.5);
  foo_0.append(server_1);
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(5);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val client_5 = new generated.meta.example.latency.Client(server_1, 0.2, 0.4);
    foo_0.append(client_5)
  }));
  foo_0.toList
}}
}