package generated.meta.example.stateless_server

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val server_0 = new generated.meta.example.stateless_server.RandomNumberServer();
  var printers_1: scala.collection.immutable.List[meta.deep.runtime.Actor] = scala.collection.immutable.Nil;
  val x_2 = scala.Predef.intWrapper(0);
  val x_3 = x_2.until(1000);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val x_5 = new generated.meta.example.stateless_server.RandomPrinter();
    x_5.`server_=`(server_0);
    x_5.`i_=`(i_4);
    val x_6 = printers_1;
    val x_7 = x_6.::[meta.deep.runtime.Actor](x_5);
    printers_1 = x_7
  }));
  val x_8 = printers_1;
  x_8.::[meta.deep.runtime.Actor](server_0)
}}
}