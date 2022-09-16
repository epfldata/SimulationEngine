package meta.test
package shortestPath

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Message}
import meta.API._
import org.scalatest.FlatSpec

@lift
class Vertex() extends Actor {
    var isSource: Boolean = false
    var dist: Int = scala.Int.MaxValue
    var received: Option[Message] = None

    def main(): Unit = {
        if (isSource){
            dist = 0
        }

        while (true) {
            received = receiveMessage()
            while (received!=None){
                if (received.get.value < dist){
                    dist = received.get.value + 1
                }
                received = receiveMessage()
            }

            connectedAgents.foreach(a => {
                val msg = new Message()
                msg.value = dist
                sendMessage(a.id, msg)
            })
            // println(id + " distance to source is " + dist + " at round " + time)
            waitLabel(Turn, 1)
        }
    }
}

class shortestPathExample extends FlatSpec {
    import meta.deep.IR.Predef._

    "Mutable agent" should "compile" in {
        val liftMyClass: ClassWithObject[Vertex] = Vertex.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {

                // Fully connected 10 vertices
                val vertices = (1 to 10).map(i => {
                    new Vertex()
                }).toList

                (1 to 10).foreach(i => {
                    vertices(i-1).connectedAgents = List(vertices(i % 10))
                })

                vertices(5).isSource = true
                vertices
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.shortestPath"),
            destFolder = "gen-core/src/main/scala/shortestPath/")
    }
}