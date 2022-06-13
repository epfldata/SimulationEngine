package example
package graphAlgorithm.pageRank

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(n: Int, p: Double): List[Actor] = {
            val allAgents: List[Vertex] = 1.to(n).map(i => {
                new Vertex()
            }).toList
            lib.Graph.ErdosRenyiGraph(allAgents, p)
            allAgents
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Vertex] = Vertex.reflect(IR)
  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}