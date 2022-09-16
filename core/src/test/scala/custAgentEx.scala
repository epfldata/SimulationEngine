package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime._
import meta.API._
import org.scalatest.FlatSpec

// A test that shows how users can override methods in Actor for debugging or other purposes
trait custActor extends Actor {
    override def messageListener(): Unit = {
        println(s"Current received messages for ${id} are ${receivedMessages}")
    }
}

@lift
class NewSim2(val n: NewSim2) extends custActor {

    def someMtd(): Unit = {
        println("Hello world!")
    }

    def main(): Unit = {
        while (true){
            println("This is agent " + id)
            if (n != null){
                n.someMtd()
            }
            waitAndReply(1)
        }
    }
}