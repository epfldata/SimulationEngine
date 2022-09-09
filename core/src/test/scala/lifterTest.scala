package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import scala.util.Random
import meta.runtime.Future
import squid.lib.transparencyPropagating

@lift
class MyClass() extends Actor {

    val c: String = "Hello"

    private val d: Int = 50

    private var neighbors: List[Actor] = List()

    @transparencyPropagating
    def foo1(a: Int): Double = {
        List(1, 2, 3.2, 4).map(i => a + i).sum
    }

    def testLocalVarBindings1(): Unit = {
        neighbors.foreach(i => {
            val tmp = scala.util.Random.nextInt()
            async_call(i.asInstanceOf[MyClass].foo1(tmp), 1).isCompleted
        })
    }

    def testLocalVarBindings2(): List[Boolean] = {
        // Trigger exception
        // neighbors.map(i => {
        //     val tmp = scala.util.Random.nextInt()
        //     async_call(() => i.asInstanceOf[MyClass].foo(tmp, 30, 43)).isCompleted
        // })
        neighbors.map(i => {
            val tmp = scala.util.Random.nextInt()
            async_call(i.asInstanceOf[MyClass].foo(tmp, 30, 43), 1).isCompleted
        })
    }

    @transparencyPropagating
    def foo(bar: Int, bar2: Int, bar3: Int): Int = {
        bar + bar2 + bar3
    }

    def z(x: List[Int]): Unit = {
        println(c)
    }

    def main(): Unit = {
        neighbors = (1 to d).map(i => new MyClass()).toList
        markPrivate("d")
        println("Hello world!")
    }
}

class lifterTest extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    val liftMyClass: ClassWithObject[MyClass] = MyClass.reflect(IR)

    "lifting a method" should "preserve the arguments of lifted method" in {
        val x = liftMyClass.methods
        assert(x.map(_.symbol.toString).toSet == Set("MyClass.main", "MyClass.foo", "MyClass.z", "MyClass.foo1", "MyClass.testLocalVarBindings1", "MyClass.testLocalVarBindings2"))
    }

    "The body of lifted methods" should "not contain keyword SpecialInstructions" in {
        val liftedRes = new Lifter().apply(List(liftMyClass)) 
        liftedRes._1.head.methods.foreach(x => {
            assert(!x.body.toString.contains("SpecialInstructions"))
        })
    }
}