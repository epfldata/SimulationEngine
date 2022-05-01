package generated.meta.test.simClone

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = scala.collection.immutable.List.apply[generated.meta.test.simClone.MutableSim](new MutableSim()) 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}