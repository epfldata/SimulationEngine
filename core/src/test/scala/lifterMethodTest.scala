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
import meta.compile._

@lift
class MyClass2(var neighbor: MyClass2) extends Actor {

    val c: String = "Hello"

    def foo1(): Double = {
        scala.util.Random.nextDouble()
    }

    def k(): Unit = {
        asyncMessage[Double](() => neighbor.foo1())
    }

    def main(): Unit = {
        k()
        asyncMessage[Double](() => neighbor.foo1())
        println("Hello world!")
    }
}

class lifterMethodTest extends FlatSpec with org.scalatest.Matchers {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    val thrown = the [Exception] thrownBy {
        val liftMyClass2: ClassWithObject[MyClass2] = MyClass2.reflect(IR)
        new Lifter().apply(List(liftMyClass2)) 
    }

    "Use async message in a method" should "trigger an exception" in {
        thrown.getMessage should endWith ("asyncMessage!")
    }
}