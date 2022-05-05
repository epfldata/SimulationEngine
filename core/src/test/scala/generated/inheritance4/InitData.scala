package generated.meta.test.inheritance4

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val s = new ShortDistanceTransport();
  val c = new CommunicatingVehicle(s);
  scala.collection.immutable.List.apply[generated.meta.test.inheritance4.Vehicle](s, c)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}