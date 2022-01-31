package generated.meta.test.snapshot

object InitData  {
    
        def apply(): scala.`package`.List[meta.runtime.Actor] = {
  val r: Receiver = new Receiver();
  {
    val x$1 = r;
    scala.Predef.intWrapper(1).to(7).map[generated.meta.test.snapshot.Sender, scala.collection.immutable.IndexedSeq[generated.meta.test.snapshot.Sender]](((x: Int) => {
  val s: Sender = new Sender(r);
  s
}))(scala.collection.immutable.IndexedSeq.canBuildFrom[generated.meta.test.snapshot.Sender]).toList.::[meta.runtime.Actor](x$1)
  }
} 
        
          def wrapper(args: List[Int]): List[meta.runtime.Actor] = {
            apply()
          }
          
          def writeSchema(pw: java.io.PrintWriter): Unit = {
            pw.write("")
            pw.flush()
          }
          
        
}