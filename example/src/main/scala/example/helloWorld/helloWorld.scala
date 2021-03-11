package example
package helloWorld

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

/**
  * A hello world example. At each turn, the client asks the server for an int. 
  * You can inspect the generated objects to see different states of the Sim
  */ 
@lift
class Server() extends Actor {

    def getPrice(): Int = {
        println("Server request received!")
        Random.nextInt() + 50
    }

    def main(): Unit = {
        while(true) {
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}

@lift 
class Client(val server: Server) extends Actor {
    
    def main(): Unit = {
        while(true) {
            val p: Int = server.getPrice()
            println("Response received " + p)
            waitLabel(Turn, 1)
        }
    }
}   

@lift 
class MainInit {
    def main(): List[Actor] = {
        val server: Server = new Server() 
        val client: Client = new Client(server) 

        List(server, client)
    }
}

object Example extends App {

  val server: ClassWithObject[Server] = Server.reflect(IR)
  val client: ClassWithObject[Client] = Client.reflect(IR)
  val mainInit: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(server, client), Some(mainInit))
} 