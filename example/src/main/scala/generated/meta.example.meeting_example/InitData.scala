package generated.meta.example.meeting_example

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val Sim1_0 = new generated.meta.example.meeting_example.Person(true);
  val Sim2_1 = new generated.meta.example.meeting_example.Person(false);
  val Sim3_2 = new generated.meta.example.meeting_example.Person(false);
  meta.deep.runtime.Actor.waitLabels.update("MeetingGroup", 3);
  scala.collection.immutable.List.apply[generated.meta.example.meeting_example.Person](Sim1_0, Sim2_1, Sim3_2)
}}
}