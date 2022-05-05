package generated.meta.test.timeseries

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val s1 = new CounterSim(null);
  val s2 = new CounterSim(s1);
  val s3 = new CounterSim(s2);
  scala.collection.immutable.List.apply[generated.meta.test.timeseries.CounterSim](s1, s2, s3)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}