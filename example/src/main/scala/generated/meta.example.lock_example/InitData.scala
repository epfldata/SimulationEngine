package generated.meta.example.lock_example

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = scala.collection.mutable.ListBuffer.apply[meta.deep.runtime.Actor]();
  val consensus_object_1 = new generated.meta.example.lock_example.Consensus();
  l_0.append(consensus_object_1);
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(5);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val x_5 = new generated.meta.example.lock_example.Voter(consensus_object_1);
    l_0.append(x_5)
  }));
  l_0.toList
}}
}