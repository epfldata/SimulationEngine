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

@lift
class MyClass() extends Actor {

    val c: String = "Hello"

    def foo1(): Double = {
        List(1, 2, 3, 4).foreach(i => println(i))
        1.0
    }

    def foo(bar: Int): Double = {
        bar + 1.0
    }

    def z(x: List[Int]): Unit = {
        println(c)
    }

    def main(): Unit = {
        println("Hello world!")
    }
}

class lifterTest extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    val liftMyClass: ClassWithObject[MyClass] = MyClass.reflect(IR)

    "lifting a method" should "preserve the arguments of lifted method" in {
        val x = liftMyClass.methods
        assert(x.map(_.symbol.toString).toSet == Set("MyClass.main", "MyClass.foo", "MyClass.z", "MyClass.foo1"))
    }
}