package example
package cyberspace

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._


@lift
class Participant(var server: Server) extends Actor {
    
    def main(): Unit = {
        while (true) {
            server.get()
            if (Random.nextInt(10)==1){
                server.post(id, "")
            }
            // waitLabel(Turn, 1)
        }
    }
}