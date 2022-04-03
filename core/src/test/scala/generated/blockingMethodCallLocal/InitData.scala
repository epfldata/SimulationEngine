package generated.meta.test.blockingMethodCallLocal

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val a = new AgentWithBlockingCallLocal();
  scala.collection.immutable.List.apply[generated.meta.test.blockingMethodCallLocal.AgentWithBlockingCallLocal](a)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}