package meta.classLifting

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

/**
  * LiteLift is a thin wrapper over Scala's quasiquotation instead of Squid
  * 
  * See the test file for more comparison. We use it as an alternative to lift
  * the main class (MainInit) that defines a simulation.
  */
object liteLift {
  def apply[T](x: => T): String = macro impl
  def impl(c: blackbox.Context)(x: c.Tree) = { import c.universe._
    val q"..$methodDef" = x

    val liftedMethodDef = methodDef.map {
      showCode(_).replace("\n", "\n    ")
    }

    if (!liftedMethodDef.mkString(" ").contains("def ")){
      throw new Exception("Invalid lift method! Not a method")
    }

    q"..$liftedMethodDef"
  }
}
