package generated.example.epistemicLogicVC

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = new scala.collection.mutable.ListBuffer[meta.deep.runtime.Actor]();
  val lc_1 = new scala.collection.mutable.ListBuffer[generated.example.epistemicLogicVC.Process]();
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(3);
  x_3.foreach[scala.Unit](((x$1_4: scala.Int) => {
    val x_5 = new generated.example.epistemicLogicVC.Process();
    lc_1.append(x_5)
  }));
  lc_1.foreach[scala.Unit](((c_6: generated.example.epistemicLogicVC.Process) => {
    val x_7 = lc_1.toList;
    val x_9 = x_7.filterNot(((p_8: generated.example.epistemicLogicVC.Process) => p_8.==(c_6)));
    c_6.`others_=`(x_9)
  }));
  l_0.appendAll(lc_1);
  l_0.toList
}}
}