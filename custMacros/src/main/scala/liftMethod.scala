package custMacros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

// Lift the MainInit of each example

object liftMethod {
  def apply[T](x: => T): String = macro impl
  def impl(c: blackbox.Context)(x: c.Tree) = { import c.universe._
    val q"..$methodDef" = x

    val liftedMethodDef = methodDef.map {
      showCode(_).replace("\n", "\n    ")
    }

    q"..$liftedMethodDef"
  }
}
