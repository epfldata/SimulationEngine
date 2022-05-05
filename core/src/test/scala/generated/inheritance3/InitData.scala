package generated.meta.test.inheritance3

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val v = new Vehicle();
  val s = new ShortDistanceTransport();
  val bus = new Bus();
  val van = new Van();
  scala.collection.immutable.List.apply[generated.meta.test.inheritance3.Vehicle](v, s, bus, van)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}