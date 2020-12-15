package generated.example.codegen_example

object InitData  {
  def initActors: List[meta.deep.runtime.Actor] = {{
  val m_0 = new generated.example.codegen_example.Market();
  val f_1 = new generated.example.codegen_example.Farmer();
  f_1.`market_=`(m_0);
  scala.collection.immutable.List.apply[meta.deep.runtime.Actor](m_0, f_1)
}}
}