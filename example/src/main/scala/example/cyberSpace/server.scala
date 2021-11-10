package example
package cyberSpace

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import meta.runtime.Actor.AgentId

@lift
class Server(var syncPeriod: Int) extends Actor {
    var content: Map[AgentId, String] = Map()
    var allServers: List[Server] = List()
    var elapsed: Int = 0

    def get(): String = {
        content.toString()
    }

    def post(id: AgentId, newContent: String): Unit = {
        if (content.get(id).isEmpty) {
            content = content.updated(id, newContent)
        } else {
            val old = content(id)
            content = content.updated(id, newContent + old)
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
                allServers.foreach(s => asyncMessage(() => s.sync(content)))
            }
        }
    }
}