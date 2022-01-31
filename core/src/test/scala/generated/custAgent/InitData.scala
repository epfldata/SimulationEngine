package generated.meta.test.custAgent

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val x = new NewSim2(null);
  val y = new NewSim2(x);
  scala.collection.immutable.List.apply[generated.meta.test.custAgent.NewSim2](x, y)
} 
        
          def wrapper(args: List[Int]): List[meta.runtime.Actor] = {
            apply()
          }
          
          def writeSchema(pw: java.io.PrintWriter): Unit = {
            pw.write("")
            pw.flush()
          }
          
        
}