package generated.meta.test.blockingMethodCall

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val a = new AgentWithBlockingCall(null);
  val b = new AgentWithBlockingCall(a);
  val c = new AgentWithBlockingCall(a);
  scala.collection.immutable.List.apply[generated.meta.test.blockingMethodCall.AgentWithBlockingCall](a, b, c)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}