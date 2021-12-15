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

case class School(val name: String){
    def schoolName(): String = name
}

@lift
class MyClass3(var s: School) extends Actor {

    val c: String = "Hello"
    
    def main(): Unit = {
        asyncMessage[String](() => s.schoolName())
        println("Hello world!")
    }
}


@lift
class MyClass4() extends Actor {
    
    def main(): Unit = {
        println("Hello world!")
    }
}

class lifterMethodTest2 extends FlatSpec with org.scalatest.Matchers {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "A minimal agent without any method" should "compile" in {
        val liftMyClass: ClassWithObject[MyClass4] = MyClass4.reflect(IR)
        new Lifter().apply(List(liftMyClass))
    }

    val thrown = the [java.lang.Exception] thrownBy {
        val liftMyClass3: ClassWithObject[MyClass3] = MyClass3.reflect(IR)
        new Lifter().apply(List(liftMyClass3))
    }

    "An invalid receiver in async message" should "trigger an exception" in {
        thrown.getMessage should startWith ("Error in asyncMessage!")
    }
}