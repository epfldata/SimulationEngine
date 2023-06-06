package cloudcity.lib
package Graph

import meta.runtime.{Actor}

import Topo._

class Vertex[V](
    var vid: VertexId,
    var vertexValue: V,
    val vertexUpdateRule: (V, Iterable[V]) => V) extends Topo[V] {
    
    val cols = 1
    
    override def toAgent(): Actor = {
        class VertexActor extends Actor {
            this.id = toAgentId(vid)
            var state: V = vertexValue
            override def run(): Int = {
                val neighborValues = receivedMessages.map(m => {
                    m match {
                        case x: VertexMessage[V] => x.content
                        case _ => throw new Exception("Invalid message!")                    
                    }
                })
                state = vertexUpdateRule(vertexValue, neighborValues)
                connectedAgentIds.foreach(i => {
                    sendMessage(i, VertexMessage[V](state))
                })
                1
            }
        }
        new VertexActor
    }
}