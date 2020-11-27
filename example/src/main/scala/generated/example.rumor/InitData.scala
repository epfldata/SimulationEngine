package generated.example.rumor

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val l_0 = new scala.collection.mutable.ListBuffer[meta.deep.runtime.Actor]();
  val lp_1 = new scala.collection.mutable.ListBuffer[example.rumor.Person]();
  val env_2 = new generated.example.rumor.Env();
  val seed_3 = new generated.example.rumor.Gossiper(env_2, true, 0.5);
  lp_1.append(seed_3);
  val x_4 = scala.Predef.intWrapper(1);
  val x_5 = x_4.to(10000);
  x_5.foreach[scala.Unit](((i_6: scala.Int) => {
    val x_7 = new generated.example.rumor.Gossiper(env_2, false, 0.3);
    lp_1.append(x_7)
  }));
  val x_8 = lp_1.toList;
  x_8.foreach[scala.collection.mutable.Set[example.rumor.Person]](((p_9: example.rumor.Person) => {
    val x_10 = p_9.network;
    val x_11 = scala.Predef.intWrapper(1);
    val x_12 = x_11.to(5);
    val x_13 = scala.collection.immutable.IndexedSeq.canBuildFrom[scala.Int];
    val x_16 = x_12.map[scala.Int, scala.collection.immutable.IndexedSeq[scala.Int]](((x$1_14: scala.Int) => {
      val x_15 = (10000).+(1);
      scala.util.Random.nextInt(x_15)
    }))(x_13);
    val x_17 = scala.collection.immutable.IndexedSeq.canBuildFrom[example.rumor.Person];
    val x_19 = x_16.map[example.rumor.Person, scala.collection.TraversableOnce[example.rumor.Person]](((x_18: scala.Int) => lp_1(x_18)))(x_17);
    x_10.++=(x_19)
  }));
  l_0.appendAll(lp_1);
  l_0.append(env_2);
  l_0.toList
}}
}