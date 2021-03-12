package generated.meta.test.inheritance

object InitData  {
  def initActors(): Unit = {
    
  val child_0 = new generated.meta.test.inheritance.child1();
  meta.runtime.SimRuntime.newActors.append(child_0);
  val child2_1 = new generated.meta.test.inheritance.child2(child_0);
  meta.runtime.SimRuntime.newActors.append(child2_1);
  ()

  }  
}