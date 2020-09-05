package meta.example.latency

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = ListBuffer()
    val server: Server = new Server(0.5)
    foo.append(server)
    (1 to 5).foreach(i => {
      val client: Client = new Client(server, 0.2, 0.4)
      foo.append(client)
    })

    foo.toList
  }
}