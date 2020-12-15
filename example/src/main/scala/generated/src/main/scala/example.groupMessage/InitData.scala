package generated.example.groupMessage

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = new scala.collection.mutable.ListBuffer[meta.deep.runtime.Actor]();
  val lc_1 = new scala.collection.mutable.ListBuffer[generated.example.groupMessage.Person]();
  val x_2 = scala.Predef.intWrapper(1);
  val x_3 = x_2.to(7);
  x_3.foreach[scala.Unit](((i_4: scala.Int) => {
    val x_5 = "Member ".+(i_4);
    val x_6 = new generated.example.groupMessage.Person(x_5);
    lc_1.append(x_6)
  }));
  val x_7 = lc_1.toList;
  x_7.foreach[scala.Unit](((m_8: generated.example.groupMessage.Person) => {
    val x_10 = lc_1.filter(((n_9: generated.example.groupMessage.Person) => n_9.!=(m_8)));
    val x_11 = x_10.toList;
    m_8.`group_=`(x_11)
  }));
  lc_1.toList
}}
}