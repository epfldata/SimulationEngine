package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

@lift
class Vertex() extends Actor {

    var counter: Int = 0

    private var neighbor: Vertex = null
    
    def API(rid: Long): Unit = {
        counter = counter + 1
    }

    def main(): Unit = {
        while (true){
            connectedAgents.foreach(x => {
                neighbor = x.asInstanceOf[Vertex]
                async_call(() => neighbor.API(id))
            })        
            waitAndReply(1)
        }
    }
}

class ResetTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Vertex with connections" should "compile" in {
        val liftMyClass: ClassWithObject[Vertex] = Vertex.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val v1 = new Vertex()
                val v2 = new Vertex()
                val v3 = new Vertex()
                val v4 = new Vertex()
                val v5 = new Vertex()
                val v6 = new Vertex()
                val v7 = new Vertex()
                val v8 = new Vertex()

                v1.setConnectedAgents(List(v2, v3, v4, v5, v6, v7, v8))
                v2.setConnectedAgents(List(v1, v3, v4, v5, v6, v7, v8))
                v3.setConnectedAgents(List(v1, v2, v4, v5, v6, v7, v8))
                v4.setConnectedAgents(List(v1, v2, v3, v5, v6, v7, v8))
                v5.setConnectedAgents(List(v1, v2, v3, v4, v6, v7, v8))
                v6.setConnectedAgents(List(v1, v3, v4, v5, v2, v7, v8))
                v7.setConnectedAgents(List(v1, v2, v4, v5, v6, v3, v8))
                v8.setConnectedAgents(List(v1, v2, v3, v5, v6, v7, v4))

                List(v1, v2, v3, v4, v5, v6, v7, v8)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.resetSim"),
            destFolder = "gen-core/src/main/scala/resetSim/")
    }
}
