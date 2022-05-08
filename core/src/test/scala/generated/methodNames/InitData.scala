package generated.meta.test.methodNames

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val a = new FooA();
  val b = new BarA();
  val c = new FooBar();
  scala.collection.immutable.List.apply[generated.meta.test.methodNames.FooA](a, b, c)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}