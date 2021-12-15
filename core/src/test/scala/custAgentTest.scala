package meta.test.custAgent

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime._
import meta.API._
import org.scalatest.FlatSpec
import meta.compile._

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
            waitLabel(Turn, 1)
        }
    }
}

class CustAgentTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Generating new custom agents" should "compile" in {
        val liftMyClass: ClassWithObject[NewSim2] = NewSim2.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val x = new NewSim2(null)
                val y = new NewSim2(x)
                List(x, y)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()),
            destFolder = "core/src/test/scala/generated/custAgent")
    }

    "Adding receive messages at run time" should "print the custom debug message" in {
        val agents = generated.meta.test.custAgent.InitData()
        val c = new SimulationConfig(agents, 5)
        StartSimulation[BaseMessagingLayer.type](c)
    }
}