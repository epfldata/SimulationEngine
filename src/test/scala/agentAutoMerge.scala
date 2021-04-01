package meta.test
package merge.auto


import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Container, SimRuntime}
import meta.runtime.simulation.{Default, SimulationConfig}
import org.scalatest.FlatSpec
import scala.util.Random
import meta.runtime.Future 



/**
 * This example demonstrates how we turn a sequence of blocking calls to broadcast to members within a container group. Also work for the broadcasting using asynchronous calls. 
 */ 
@lift 
class B() extends Actor {
    var neighbors: List[B] = Nil 
    var foo: Int = Random.nextInt(90)

    var futures: List[Future[Boolean]] = List() 

    def setVal(bar: Int): Boolean = {
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
        println("Agent " + id + " neighbors " + neighbors)
        while(true) {
            println("This is " + id)
            if (neighbors.nonEmpty){
                foo = Random.nextInt(90)
                // neighbors.foreach(n => {
                //     println("Send message! Set value to " + foo)
                //     futures = asyncMessage(() => n.setVal(foo)) :: futures
                // })
                // while (!futures.forall(x => x.isCompleted)){
                //     waitLabel(Turn, 1)
                // } 
                // futures.foreach(x => println(x.popValue))
                // println("Hear back all the responses! " + id)
                // futures = List()
                neighbors.foreach(n => {
                    println(id + " Send message! Set value to " + foo)
                    n.setVal(foo)
                    println(id + " Hear back the response!")
                })
            }
            handleMessages()
            waitLabel(Turn, 1)
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

      a1.neighbors = List(a2, a3)

      val a4: B = new B()
      val a5: B = new B()

      a4.neighbors = List(a5)
    """
  
    val c1: ClassWithObject[B] = B.reflect(IR)

    compileSims(List(c1),
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName(), 
      destFolder = "src/test/scala/generated/mergeAuto")  
  }
}

class BRun extends FlatSpec {
  "The generated code B" should "run" in {
    generated.meta.test.merge.auto.InitData.initActors()
    new Default(SimulationConfig(actors = List(), totalTurn = 50)).run()
  }
}