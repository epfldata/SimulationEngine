package meta.test
package merge 


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
 * This example demonstrates how to properly initialize the runtime to group a collection of agents into a container agent. Within a container agent, blocking messages addressed to peer agents who are also within the same container are dispatched and responded immediately, mimicing the behaviour of a direct method call. However, the blocking and unblocking restricts the behaviour to minimal agents only (i.e. agents with no actions besides handling messages and wait)   
 */ 
@lift 
class A() extends Actor {
    var neighbors: List[A] = Nil 
    var foo: Int = Random.nextInt(90)

    def setVal(bar: Int): Unit = {
        if (bar > 30) {
            println("Larger than 30!")
        } else {
            println("Less or equal to 30!")
        }
        foo = bar 
    }

    def mtd2(bar: Int): Unit = {
        if (!neighbors.isEmpty) {
            neighbors.head.setVal(10)
        }
    }

    def main(): Unit = {
        while(true) {
            // neighbors.foreach(n => asyncMessage(() => n.setVal(foo)))
            if (neighbors.nonEmpty){
                foo = Random.nextInt(90)
                println("Send message! Set value to " + foo)
                neighbors.head.setVal(foo)
                println("Hear back the response!")
            }
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}

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
        while(true) {
            if (neighbors.nonEmpty){
                foo = Random.nextInt(90)
                neighbors.foreach(n => {
                    println("Send message! Set value to " + foo)
                    futures = asyncMessage(() => n.setVal(foo)) :: futures
                })
                while (!futures.forall(x => x.isCompleted)){
                    waitLabel(Turn, 1)
                } 
                futures.foreach(x => println(x.popValue))
                println("Hear back all the responses!")
                
                // neighbors.foreach(n => {
                //     println("Send message! Set value to " + foo)
                //     n.setVal(foo)
                //     println("Hear back the response!")
                // })
            }
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}

class ACompile extends FlatSpec {
  import meta.compile._
  import meta.deep.IR.Predef._ 

  "The merged example" should "compile" in {
    val init = code"""
      val a1: A = new A() 
      val a2: A = new A()
      val a3: A = new A()

      a1.neighbors = List(a2)
      a2.neighbors = List(a3)

      // newActors in SimRuntime contains the new agents a1, and a2. We add them to the container agent instead. 

      val group: List[Actor] = SimRuntime.newActors.toList 
      SimRuntime.newActors.clear()

      val c: Container = new Container()
      c.addAgents(group)
      SimRuntime.newActors.append(c)
    """
  
    val c1: ClassWithObject[A] = A.reflect(IR)

    compileSims(List(c1),
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName(), 
      destFolder = "src/test/scala/generated/merge")  
  }
}

class ARun extends FlatSpec {
  "The generated code" should "run" in {
    generated.meta.test.merge.InitData.initActors()
    new Default(SimulationConfig(actors = List(), totalTurn = 5)).run()
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
      a2.neighbors = List(a3)

      // newActors in SimRuntime contains the new agents a1, and a2. We add them to the container agent instead. 

      val group: List[Actor] = SimRuntime.newActors.toList 
      SimRuntime.newActors.clear()

      val c: Container = new Container()
      c.addAgents(group)
      SimRuntime.newActors.append(c)
    """
  
    val c1: ClassWithObject[B] = B.reflect(IR)

    compileSims(List(c1),
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName()+"Broadcast", 
      destFolder = "src/test/scala/generated/mergeBroadcast")  
  }
}

class BRun extends FlatSpec {
  "The generated code B" should "run" in {
    generated.meta.test.mergeBroadcast.InitData.initActors()
    new Default(SimulationConfig(actors = List(), totalTurn = 3)).run()
  }
}