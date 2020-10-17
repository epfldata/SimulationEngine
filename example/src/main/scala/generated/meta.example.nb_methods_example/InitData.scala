package generated.meta.example.nb_methods_example

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val foo_0 = scala.collection.mutable.ListBuffer.apply[meta.deep.runtime.Actor]();
  val obj2_1 = new generated.meta.example.nb_methods_example.Object2();
  foo_0.append(obj2_1);
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(5);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val obj1_5 = new generated.meta.example.nb_methods_example.Object1(obj2_1);
    foo_0.append(obj1_5)
  }));
  foo_0.toList
}}
}