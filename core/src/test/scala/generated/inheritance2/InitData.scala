package generated.meta.test.inheritance2

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val teacher = new Teacher();
  val student = new Student(teacher);
  scala.collection.immutable.List.apply[meta.test.Person](teacher, student)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}