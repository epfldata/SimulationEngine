package generated.meta.example.server_communication

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = scala.collection.mutable.ListBuffer.apply[meta.deep.runtime.Actor]();
  val x_1 = scala.Predef.intWrapper(0);
  val x_2 = x_1.to(3);
  x_2.foreach[scala.Unit](((i_3: scala.Int) => {
    val backendServer_4 = new generated.meta.example.server_communication.BackendServer();
    val frontendServer_5 = new generated.meta.example.server_communication.FrontendServer();
    frontendServer_5.`backendServer_=`(backendServer_4);
    l_0.append(backendServer_4);
    l_0.append(frontendServer_5)
  }));
  l_0.toList
}}
}