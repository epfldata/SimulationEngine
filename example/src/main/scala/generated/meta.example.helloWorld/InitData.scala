package generated.meta.example.helloWorld

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val p1_0 = new generated.meta.example.helloWorld.TimidPerson("Alice");
  val p2_1 = new generated.meta.example.helloWorld.OutgoingPerson("Bob");
  val p3_2 = new generated.meta.example.helloWorld.TimidPerson("Eve");
  val x_3 = p1_0.friendList;
  x_3.append(p2_1);
  val x_4 = p2_1.friendList;
  x_4.append(p1_0, p3_2);
  val x_5 = p3_2.friendList;
  x_5.append(p2_1);
  scala.collection.immutable.List.apply[meta.example.helloWorld.Person](p1_0, p2_1, p3_2)
}}
}