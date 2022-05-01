package generated.meta.test.resetSim

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val v1 = new Vertex();
  val v2 = new Vertex();
  val v3 = new Vertex();
  val v4 = new Vertex();
  v1.`connectedAgents_=`(scala.collection.immutable.List.apply[generated.meta.test.resetSim.Vertex](v2, v3, v4));
  v2.`connectedAgents_=`(scala.collection.immutable.List.apply[generated.meta.test.resetSim.Vertex](v1, v3, v4));
  v3.`connectedAgents_=`(scala.collection.immutable.List.apply[generated.meta.test.resetSim.Vertex](v1, v2, v4));
  v4.`connectedAgents_=`(scala.collection.immutable.List.apply[generated.meta.test.resetSim.Vertex](v1, v2, v3));
  scala.collection.immutable.List.apply[generated.meta.test.resetSim.Vertex](v1, v2, v3, v4)
} 
        
        def wrapper(args: List[Any]): List[meta.runtime.Actor] = {
          apply()
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("")
          pw.flush()
        }
        
        
}