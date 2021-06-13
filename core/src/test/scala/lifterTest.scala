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
class MyClass() extends Actor {

    val c: String = "Hello"

    def foo1(): Double = {
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

    val liftMyClass: ClassWithObject[MyClass] = MyClass.reflect(IR)
    // val init = code"val agent: MyClass = new MyClass()"

    "lifting a method" should "preserve the arguments of lifted method" in {
        liftMyClass.methods.foreach(m => {
            println("Method symbol: " + m.symbol)
            println("Method args: " + m.vparamss)
            println("Method body: " + m.body)
        })

        val ms = liftMyClass.methods.filterNot(x => x.symbol.asMtdSymbol == "main").tail
    
        ms.foreach(m => {
            var body = m.body.showScala
            m.vparamss.head.foreach({
                case v =>
                    body = body.replaceAll(v.toString(), v.toString().split("@").head)
            })
            println(body)
        })
    }

    // "Compile methods" should "work" in {
    //     compileSims(List(liftMyClass), mainInit = init, initPkgName = this.getClass().getPackage().getName())
    // }
}