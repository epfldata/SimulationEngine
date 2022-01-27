package example
package cyberspace

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import meta.runtime.Actor.AgentId
import scala.collection.mutable.Map

@lift
class Server(var syncPeriod: Int, var batchMessages: Int) extends Actor {
    val content: Map[AgentId, String] = Map()
    var allServers: List[Server] = List()
    var elapsed: Int = 0

    def get(): String = {
        content.toString()
    }

    def post(id: AgentId, newContent: String): Unit = {
        if (content.get(id).isEmpty) {
            content(id) = newContent
        } else {
            val old = content(id)
            content(id) = old + newContent
        }
    }

    def sync(c: Map[AgentId, String]): Unit = {
        c.foreach(x => {
            post(x._1, x._2)
        })
    }

    def main(): Unit = {
        while (true) {
            handleMessages()
            waitLabel(Turn, 1)
            elapsed = elapsed + 1
            if (elapsed >= syncPeriod){
                var batchCounter: Int = 1
                while (batchCounter < batchMessages) {
                    allServers.foreach(s => asyncMessage(() => s.sync(content)))
                    batchCounter = batchCounter + 1
                }
                elapsed = 0
            }
        }
    }
}