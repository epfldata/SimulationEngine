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
    override def addReceiveMessages(messages: List[Message]): Actor = {
        println(s"Add receive messages for ${id}: ${messages} $responseListeners")

        this.receivedMessages.appendAll( messages.filter(
        x =>
            x.isInstanceOf[RequestMessage] || responseListeners
            .get(x.sessionId)
            .isEmpty))
        // Only invoke handler callback If the agent is not a container agent

        if (proxyIds.size == 1) {
        messages
        .filter(
            x =>
            responseListeners.get(x.sessionId).isDefined && x
                .isInstanceOf[ResponseMessage])
        .foreach(x => {
            val handler = responseListeners(x.sessionId)
            responseListeners.remove(x.sessionId)
            handler(x)
        })
        }

        this
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