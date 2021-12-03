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

class lifterMethodTest extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    val liftMyClass2: ClassWithObject[MyClass2] = MyClass2.reflect(IR)

    assertThrows[Exception]{
        new Lifter().apply(List(liftMyClass2)) 
    }
}