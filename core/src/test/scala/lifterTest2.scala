package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import org.scalatest.FlatSpec
import squid.lib.transparencyPropagating


@lift
case class School(val name: String){
    @transparencyPropagating
    def schoolName(): String = name
}

@lift
class MyClass3(var s: School) extends Actor {

    val c: String = "Hello"
    
    def hello(): String = c

    def main(): Unit = {
        asyncCall(s.schoolName(), 1)
        println("Hello world!")
    }
}


@lift
class MyClass4() extends Actor {
    
    @transparencyPropagating
    def hello(): String = "world"

    def main(): Unit = {
        println("Hello world!")
        handleRPC()
    }
}


@lift
class MyClass5(var s: MyClass4) extends Actor {
    
    def main(): Unit = {
        asyncCall[String](s.hello(), 1)
    }
}

class lifterTest2 extends FlatSpec with org.scalatest.Matchers {
    import meta.classLifting.Lifter

    val liftSchool = School.reflect(IR)
    val liftMyClass4: ClassWithObject[MyClass4] = MyClass4.reflect(IR)
    val liftMyClass3: ClassWithObject[MyClass3] = MyClass3.reflect(IR)
    val liftMyClass5: ClassWithObject[MyClass5] = MyClass5.reflect(IR)

    "A minimal agent" should "compile" in {    
        new Lifter().apply(List(liftMyClass4))
    }

    "Lifting a non-agent" should "not compile" in {
        assertDoesNotCompile("new Lifter().apply(List(liftSchool))")
    }

    "An invalid receiver in async message" should "trigger an exception" in {
        intercept[java.lang.Exception] {
            new Lifter().apply(List(liftMyClass3))
        }
    }

    "Forget to lift receiver agent" should "trigger an exception" in {
        intercept[java.lang.Exception] {
            new Lifter().apply(List(liftMyClass5))
        }
    }

    "Valid receiver with proper liftings" should "compile" in {
        new Lifter().apply(List(liftMyClass5, liftMyClass4))
    }
}