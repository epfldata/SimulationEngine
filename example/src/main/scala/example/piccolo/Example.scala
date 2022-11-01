package example
package piccolo

import scala.collection.mutable.Map

// MutMap would result in compilation error
object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(): List[Actor] = {
            val partition = new GraphTable(Map(1 -> List(2), 2-> List(1, 3), 3 -> List(2)))
            // Initially all pages have equal ranks
            val current = new Table(Map(1 -> 1, 2-> 1, 3->1))
            val next = new Table(Map(1 -> 1, 2-> 1, 3->1))
            val kernel = new PageRankKernel(current, next, partition)
            List(partition, current, next, kernel)
        }
    }
}

object Example extends App {

    val cls1: ClassWithObject[GraphTable] = GraphTable.reflect(IR)
    val cls2: ClassWithObject[Table] = Table.reflect(IR)
    val cls3: ClassWithObject[PageRankKernel] = PageRankKernel.reflect(IR)
    val mainClass = MainInit.liftedMain
    
    compileSims(List(cls1, cls2, cls3), Some(mainClass))
}