package meta.test
package split 

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Container, SimRuntime}
import meta.runtime.simulation.{Base, SimulationConfig}
import org.scalatest.FlatSpec
import scala.util.Random
import meta.runtime.Future

/**
 * This example demonstrates how we turn a sequence of blocking calls to broadcast to members within a container group. Also work for the broadcasting using asynchronous calls. 
 */ 
@lift 
class B() extends Actor {
    var foo: Int = Random.nextInt(90)

    var futures: List[Future[Boolean]] = List() 

    def setVal(bar: Int): Boolean = {
        // val randDelay = scala.util.Random.nextInt(3) + 1
        // meta.Util.debug(id + " add arbitrary delay: " + randDelay)
        // waitLabel(Turn, randDelay)

        if (bar > 30) {
            println("Larger than 30!")
            foo = bar 
            true 
        } else {
            println("Less or equal to 30!")
            false 
        }
    }

    def main(): Unit = {
        while(true) {
            println("This is agent " + id)
            if (connectedAgents.nonEmpty){
                foo = Random.nextInt(90)
                // neighbors.foreach(n => {
                //     println("Send message! Set value to " + foo)
                //     futures = asyncMessage(() => n.setVal(foo)) :: futures
                // })
                // while (!futures.forall(x => x.isCompleted)){
                //     waitLabel(Turn, 1)
                // } 
                // futures.foreach(x => println(x.popValue))
                // println("Hear back all the responses!")
                // futures = List()
                connectedAgents.foreach(n => {
                    println(id + " sends message to " + n.id)
                    n.asInstanceOf[B].setVal(foo)
                    println(id + " hears back the response! " + n.id)
                })
            }
            handleMessages()
            // waitLabel(Turn, 1)
            val randDelay = scala.util.Random.nextInt(3) + 1
            meta.Util.debug(id + " add arbitrary delay: " + randDelay)
            waitLabel(Turn, randDelay)
        }
    }
}

class BCompile extends FlatSpec {
  import meta.compile._
  import meta.deep.IR.Predef._ 

  "The merged example" should "compile" in {
    val init = code"""
      val a1: B = new B() 
      val a2: B = new B()
      val a3: B = new B()
      val a4: B = new B()

      a3.relaxConsistency = true

      a1.connectedAgents = List(a3)
      a2.connectedAgents = List(a3)
      a4.connectedAgents = List(a3)
    """
  
    val c1: ClassWithObject[B] = B.reflect(IR)

    compileSims(List(c1),
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName(), 
      destFolder = "src/test/scala/generated/split")  
  }
}

// class BRun extends FlatSpec {
//   "The generated code B" should "run" in {
//     generated.meta.test.split.InitData.initActors()
//     new Default(SimulationConfig(actors = List(), totalTurn = 80)).run()
//   }
// }