package example
package cyberspace

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._


@lift
class Reader(var server: Server) extends Actor {
    
    def main(): Unit = {
        while (true) {
            server.get()
            // waitLabel(Turn, 1)
        }
    }
}