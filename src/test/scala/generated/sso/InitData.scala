package generated.meta.test.SSO

object InitData  {
  def initActors(): Unit = {
    
  val rServer_0 = new generated.meta.test.SSO.RandomServer();
  meta.runtime.SimRuntime.newActors.append(rServer_0);
  val server_1 = new generated.meta.test.SSO.Server(rServer_0);
  meta.runtime.SimRuntime.newActors.append(server_1);
  val client_2 = new generated.meta.test.SSO.Client(server_1);
  meta.runtime.SimRuntime.newActors.append(client_2);
  ()

  }  
}