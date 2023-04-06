package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import squid.lib.transparencyPropagating

@lift
class Vertex() extends Actor {

    var counter: Int = 0

    private var neighbor: Vertex = null
    
    @transparencyPropagating
    def API(rid: Long): Unit = {
        // println(id + " receives message from " + rid)
        counter = counter + 1
    }

    def main(): Unit = {
        while (true){
            connectedAgents.foreach(x => {
                neighbor = x.asInstanceOf[Vertex]
                callAndForget(neighbor.API(id), 1)
            })
            waitAndReply(1)
        }
    }
}

class ResetTest extends FlatSpec {

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

                v1.connectedAgents=List(v2, v3, v4, v5, v6, v7, v8)
                v2.connectedAgents=List(v1, v3, v4, v5, v6, v7, v8)
                v3.connectedAgents=List(v1, v2, v4, v5, v6, v7, v8)
                v4.connectedAgents=List(v1, v2, v3, v5, v6, v7, v8)
                v5.connectedAgents=List(v1, v2, v3, v4, v6, v7, v8)
                v6.connectedAgents=List(v1, v3, v4, v5, v2, v7, v8)
                v7.connectedAgents=List(v1, v2, v4, v5, v6, v3, v8)
                v8.connectedAgents=List(v1, v2, v3, v5, v6, v7, v4)

                List(v1, v2, v3, v4, v5, v6, v7, v8)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.resetSim"),
            destFolder = "gen-core/src/main/scala/resetSim/")
    }
}
