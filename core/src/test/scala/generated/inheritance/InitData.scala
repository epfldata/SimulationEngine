package generated.meta.test.inheritance

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val teacher = new Teacher();
  val worker = new Worker();
  scala.collection.immutable.List.apply[meta.test.Person](teacher, worker)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}