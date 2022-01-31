package generated.meta.test.newSim

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = scala.collection.immutable.List.apply[generated.meta.test.newSim.NewSim](new NewSim()) 
        
          def wrapper(args: List[Int]): List[meta.runtime.Actor] = {
            apply()
          }
          
          def writeSchema(pw: java.io.PrintWriter): Unit = {
            pw.write("")
            pw.flush()
          }
          
        
}