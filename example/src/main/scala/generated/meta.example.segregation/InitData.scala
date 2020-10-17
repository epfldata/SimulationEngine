package generated.meta.example.segregation

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val foo_0 = scala.collection.mutable.ListBuffer.apply[meta.deep.runtime.Actor]();
  val worldMap_1 = new generated.meta.example.segregation.WorldMap();
  foo_0.append(worldMap_1);
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(1125);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val p1_5 = new generated.meta.example.segregation.Person(worldMap_1, 0);
    val p2_6 = new generated.meta.example.segregation.Person(worldMap_1, 1);
    foo_0.append(p1_5, p2_6)
  }));
  val x_7 = (1125).*(2);
  meta.deep.runtime.Actor.waitLabels.update("People", x_7);
  foo_0.toList
}}
}