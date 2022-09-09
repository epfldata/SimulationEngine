package generated.example.gameOfLife

object InitData  {
    
        def apply(width: scala.Int, height: scala.Int, cfreq: scala.Int): scala.`package`.List[example.`package`.Actor] = {
  val totalPoints: scala.Int = width.*(height);
  val neighborRadius: scala.Int = 1;
  val points = scala.Predef.intWrapper(1).to(totalPoints).map[generated.example.gameOfLife.Cell, scala.collection.immutable.IndexedSeq[generated.example.gameOfLife.Cell]](((x: Int) => new Cell(example.`package`.Random.nextBoolean(), cfreq)))(scala.collection.immutable.IndexedSeq.canBuildFrom[generated.example.gameOfLife.Cell]);
  lib.Graph.Torus2DGraph.apply(points, width, height, neighborRadius);
  points.toList
} 
        
        def wrapper(args: List[Any]): List[example.Actor] = {
          apply(args(0).asInstanceOf[Int],args(1).asInstanceOf[Int],args(2).asInstanceOf[Int])
        }
        
        def writeSchema(pw: java.io.PrintWriter): Unit = {
          pw.write("width,height,cfreq")
          pw.flush()
        }
        
        
}