package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.simulation.{Base, SimulationConfig}
import org.scalatest.FlatSpec
import scala.util.Random
import meta.runtime.Future
import meta.compile._
import meta.deep.IR.Predef._
import meta.deep.IR.Predef.base.MethodApplication

trait Agent

object specialInst {
    def asyncSend[T](msg: (() => T)): Future[T] = ???
}

object Opt {
    def opt[T](pgrm: OpenCode[T]): Unit = pgrm match {
        case code"meta.test.specialInst.asyncSend[$t]((() => ${MethodApplication(ma)}:t))" => 
            println("Match the delayed execution!")
            println(ma.symbol)
            println(ma.args)
            println(ma.rebuild(new base.SelfCodeTransformer { // TODO extract and make common with above
                def transform[T1,C1](code: Code[T1,C1]): Code[T1,C1] = {
                    code
                }
            }))

        case code"${MethodApplication(ma)}" => 
            println("Match method application!")
            println(ma.symbol)
            println(ma.args)
        case _ => 
            println(pgrm + " No match!")
    }
}

@lift
class A() extends Agent {
    val n: B = new B()
    val bar: Int = 980
    var someString: String = "hi"

    val l = List(1, 2, 3)

    def a2(): Unit = {

        // specialInst.asyncSend[Unit](n, "B.b2", List(bar, "hello"))

        // l.foreach(x => {
        //     specialInst.asyncSend[Unit](n, "B.b2", List(x, "h"))
        //     specialInst.asyncSend[Unit](n, "B.b2", List(x, someString))
        // })

        specialInst.asyncSend[Unit](() => n.b())

        // l.foreach(x => {
        //     B.b2(x, someString)
        //     B.b2(x, "hello")
        // })

        // squid.utils.Lazy(B.b())
        // squid.utils.Lazy(B.b2(bar, someString))
        // squid.utils.Lazy(B.b2(10, someString))
    }

    def a3(): Unit = {
        specialInst.asyncSend[Unit](() => n.b2(bar, someString))
    }

    def a4(): Unit = {
        specialInst.asyncSend[Unit](() => n.b2(10, someString))
    }
    // def a(): Int = 10
}

@lift
class B() extends Agent {
    val foo: Int = 10

    def b(): String = "hello"
    def b2(a: Int, b: String): String = a + foo.toString
}

class lazyTest extends FlatSpec {

    val liftMyClass: ClassWithObject[A] = A.reflect(IR)

    "lazy val lifting" should "preserve the method symbols" in {
        liftMyClass.methods.foreach(m => {
            Opt.opt(m.body.asOpenCode)
            // println("Method symbol: " + m.symbol)
            // println("Method args: " + m.vparamss)
            // println("Method body: " + m.body)
        })
    }
}