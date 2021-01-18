package example
package latency

import squid.quasi.lift

@lift
class MainInit {
  def main(): List[Actor] = {
    val foo: ListBuffer[Actor] = new ListBuffer()
    val server: Server = new Server(0.5)

    foo.append(server)

    (1 to 5).foreach(i => {
      val client: Client = new Client(server, 0.2, 0.4)
      foo.append(client)
    })

    foo.toList
  }
}