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

    def getVarName(s: Tree): String = {
      s match {
        case ValDef(mods: Modifiers, name: TermName, tpt: Tree, rhs: Tree) => name.toString()
        case _ => ""
      }
    }

    methodDef.head match {
      case q"$mods def $name[..$tparams](...$argss): $tpt = $body" if argss.nonEmpty =>

        if (argss.length > 1)
          throw new Exception("We do not support currying")

        val wrapper = 
          f"""
          // def wrapper(args: List[Int]): $tpt = {
          //   $name(${(0 to argss(0).length-1).map(x => "args("+x+")").mkString(",")})
          // }
          
          def writeSchema(pw: java.io.PrintWriter): Unit = {
            pw.write("${argss(0).map(x => getVarName(x)).mkString(",")}")
            pw.flush()
          }
          """

        val ans = f"""
        ${showCode(methodDef.head)} 
        ${wrapper}
        """

        q"..$ans"

      case _ => throw new Exception("Invalid lift method! Not a method")
    }
  }
}
