package meta.test
package liftSome

import java.lang.Thread.sleep

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.Actor
import meta.runtime.simulation.{Default, SimulationConfig}

import scala.util.Random

object liftSomeUtil {
  case class Foo(id: Int)
  case class Bar[T](bar: T)
}

import liftSomeUtil.{Foo, Bar}

@lift
class liftSomeSpec extends Actor {

  def foo(): Option[Bar[Foo]] = {
    if (Random.nextBoolean()) {
      Some(Bar(Foo(95)))
    } else {
      None
    }
  }

  def main(): Unit = {
    while(true) {
      println(foo())
      waitLabel(Turn, 1)
    }
  }
}


@lift
class MainInit {
  def init(): List[Actor] = {
    val foo = new liftSomeSpec()
    List(foo)
  }
}

object someSpec {
  import meta.compile._

  def compile(): Unit = {
    val testSome: ClassWithObject[liftSomeSpec] = liftSomeSpec.reflect(IR)
    val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
    compileSims(List(testSome), Some(mainClass), destFolder = "src/test/scala/generated/")
  }

  def main(args: Array[String]): Unit = {
    compile()
    sleep(100)
    new Default(SimulationConfig(generated.meta.test.liftSome.InitData.initActors, 0, 1, 0, 1)).run()
  }
}
