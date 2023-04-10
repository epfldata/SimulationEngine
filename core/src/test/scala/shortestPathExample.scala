package meta.test
package shortestPath

import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Message, IntMessage}
import meta.API._
import org.scalatest.FlatSpec
import meta.classLifting.SpecialInstructions

@lift
class Vertex() extends Actor {
    var isSource: Boolean = false
    var dist: Int = scala.Int.MaxValue
    var received: Option[Message] = None
    var receivedDist: Int = 0

    def main(): Unit = {
        if (isSource){
            dist = 0
        }

        while (true) {
            received = receiveMessage()
            while (received!=None){
                receivedDist = received.get.asInstanceOf[IntMessage].value
                if (receivedDist < dist){
                    dist = receivedDist + 1
                }
                received = receiveMessage()
            }

            connectedAgents.foreach(a => {
                val msg = new IntMessage(dist)
                sendMessage(a.id, msg)
            })
            // println(id + " distance to source is " + dist + " at round " + time)
            SpecialInstructions.waitRounds(1)
        }
    }
}

class shortestPathExample extends FlatSpec {

    "Mutable agent" should "compile" in {
        val liftMyClass: ClassWithObject[Vertex] = Vertex.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val totalVertices: Int = 50
                // Fully connected 10 vertices
                val vertices = (1 to totalVertices).map(i => {
                    new Vertex()
                }).toList

                (1 to totalVertices).foreach(i => {
                    vertices(i-1).connectedAgents = List(vertices(i % totalVertices))
                })

                // Assertions not supported in litelift
                // assert(totalVertices > 5)
                vertices(0).isSource = true
                vertices
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.shortestPath"),
            destFolder = "gen-core/src/main/scala/shortestPath/")
    }
}