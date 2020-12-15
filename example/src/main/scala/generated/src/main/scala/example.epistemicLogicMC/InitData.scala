package generated.example.epistemicLogicMC

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = new scala.collection.mutable.ListBuffer[meta.deep.runtime.Actor]();
  val lc_1 = new scala.collection.mutable.ListBuffer[generated.example.epistemicLogicMC.Child]();
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(2);
  x_3.foreach[scala.Unit](((x$1_4: scala.Int) => {
    val x_5 = new generated.example.epistemicLogicMC.Child(true);
    lc_1.append(x_5)
  }));
  val x_6 = scala.Predef.intWrapper(1);
  val x_7 = (3).-(2);
  val x_8 = x_6.to(x_7);
  x_8.foreach[scala.Unit](((x$2_9: scala.Int) => {
    val x_10 = new generated.example.epistemicLogicMC.Child(false);
    lc_1.append(x_10)
  }));
  lc_1.foreach[scala.Unit](((c_11: generated.example.epistemicLogicMC.Child) => {
    val x_12 = lc_1.toList;
    val x_14 = x_12.filterNot(((p_13: generated.example.epistemicLogicMC.Child) => p_13.==(c_11)));
    c_11.`neighbors_=`(x_14)
  }));
  val x_15 = lc_1.toList;
  val x_16 = new generated.example.epistemicLogicMC.Adult(x_15);
  l_0.appendAll(lc_1);
  l_0.append(x_16);
  l_0.toList
}}
}