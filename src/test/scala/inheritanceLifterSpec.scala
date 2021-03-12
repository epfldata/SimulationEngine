package meta.test
package inheritance

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.Actor
import meta.runtime.simulation.{Default, SimulationConfig}
import org.scalatest.FlatSpec
import scala.util.Random

@lift 
class parent1 extends Actor {
    val var1: Int = 10 

    def pMtd(): Unit = {println("Pmtd 1: " + var1)}
}

trait parentT {
    val someVar: String = "h"
}

@lift 
class parent2 extends parent1 with parentT {
    val var2: String = "h" 

    def pMtd2(): Unit = { println("another pMtd " + var2) }
}

@lift 
class child1 extends parent2 {

  def main(): Unit = {
    while(true) {
      println("This is child1! ")
      pMtd2()
      println("I can access my parents' methods as if my own!")
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}

@lift 
class child2(val c: child1) extends Actor {

  def main(): Unit = {
    while(true) {
      println("This is child 2! sends msg to c1")
      c.pMtd()
      waitLabel(Turn, 1)
    }
  }
}

// Only support override in the leaf agent, not in the branch agent 
class inheritanceLifterSpec extends FlatSpec {

  import meta.classLifting.Lifter 

  val c1: ClassWithObject[parent1] = parent1.reflect(IR)
  val c2: ClassWithObject[parent2] = parent2.reflect(IR)
  val c3: ClassWithObject[child1] = child1.reflect(IR)

  "Init" should "populate the method tables in Lifter" in {
    Lifter.init(List(c1, c2, c3))
    
    assert(Lifter.methodsIdMap.keySet == Set("parent1.pMtd", "parent2.pMtd2", "child1.main"))
    assert(Lifter.methodsIdMap.size == Lifter.methodsMap.size)

    assert(Lifter.MMap("child1") == List("child1.main"))
    assert(Lifter.MMap("parent1") == List("parent1.pMtd"))
    assert(Lifter.MMap("parent2") == List("parent2.pMtd2"))

    assert(Lifter.redirectMap.isEmpty)

    assert(Lifter.branchActors.size == 2)
    assert(Lifter.branchActors.get("parent1").isDefined)
    assert(Lifter.branchActors.get("parent2").isDefined)
    assert(Lifter.leafActors.size == 1)
    assert(Lifter.leafActors.head.name == "child1")
  }

  "Redirect methods" should "add the parents methods to children" in {
    Lifter.addRedirectMethods()

    assert(Lifter.MMap("child1").diff(List("child1.main", "child1.pMtd", "child1.pMtd2")).isEmpty)
    assert(Lifter.MMap("parent2").diff(List("parent2.pMtd1", "parent2.pMtd2")).isEmpty)
    
    assert(Lifter.methodsIdMap.keySet == Set("parent1.pMtd", "parent2.pMtd2", "child1.main", "child1.pMtd", "child1.pMtd2"))
    assert(Lifter.methodsIdMap.size == Lifter.methodsMap.size)

    assert(Lifter.inheritance("parent2") == Set("parent1"))

    // trait parentT is not in the inherited list of child1. Any traits should be directly instantiated by child to be discovered
    assert(Lifter.inheritance("child1") == Set("parent1", "parent2"))
    assert(Lifter.inheritance.get("parent1").isEmpty)
    
    assert(Lifter.redirectMap("parent1.pMtd").diff(List("parent2.pMtd", "child1.pMtd")).isEmpty)
  }

  "States" should "include the states from the parents" in {

    val liftedParent1 = Lifter.liftActor(Lifter.branchActors("parent1"))  
    val liftedParent2 = Lifter.liftActor(Lifter.branchActors("parent2"))    

    assert(liftedParent1.states.head.name == "var1")
    assert(liftedParent1.states.head.init == "10")
    assert(liftedParent1.states.head.tpeRep == "Int")

    assert(liftedParent2.states.head.name == "var2")
    
    val liftedChild = Lifter.liftActor(Lifter.leafActors.head)
    val childrenStates = liftedChild.states

    assert(childrenStates.map(x => x.name).diff(List("var1", "var2")).isEmpty)

    liftedChild.methods.foreach(m => println(m.methodId, m.body))
    liftedParent1.methods.foreach(m => println(m.methodId, m.body))
    liftedParent2.methods.foreach(m => println(m.methodId, m.body))


    println(liftedChild.main)
  }

  "Agents with parameter list" should "also get lifted" in {
    val c: ClassWithObject[child2] = child2.reflect(IR)
    Lifter.init(List(c))
    Lifter.addRedirectMethods()
    assert(Lifter.leafActors.size == 2)
    assert(Lifter.branchActors.size == 2)
    
    val liftedChild2 = Lifter.liftActor(c)
    assert(liftedChild2.methods.isEmpty)
  }

  "The state of method tables after lifting" should "contain all methods" in {
    // no parent2.pMtd
    Lifter.methodsIdMap.foreach(println)
    assert(Lifter.methodsIdMap.size == 6)
  }
}

class inheritanceE2ECompile extends FlatSpec {
  import meta.compile._
  import meta.deep.IR.Predef._ 

  "The example" should "compile" in {
    val init = code"""
      val child: child1 = new child1()
      val child2: child2 = new child2(child)
    """
  
    val c1: ClassWithObject[parent1] = parent1.reflect(IR)
    val c2: ClassWithObject[parent2] = parent2.reflect(IR)
    val c3: ClassWithObject[child1] = child1.reflect(IR)
    val c4: ClassWithObject[child2] = child2.reflect(IR)

    compileSims(List(c1, c2, c3, c4), 
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName(), 
      destFolder = "src/test/scala/generated/inheritance")  
  }
}

class inheritanceE2ERun extends FlatSpec {
  "The generated code" should "run" in {
    generated.meta.test.inheritance.InitData.initActors() 
    new Default(SimulationConfig(totalTurn = 5)).run()
  }
}