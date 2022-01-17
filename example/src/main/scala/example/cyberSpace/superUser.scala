package example
package cyberSpace

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._


@lift
class SuperUser(var server: Server) extends Actor {
    
    def main(): Unit = {
        while (true) {
            server.get()
            server.post(id, "")
            // waitLabel(Turn, 1)
        }
    }
}