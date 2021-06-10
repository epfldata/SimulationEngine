package meta.deep.member

import meta.deep.IR
import meta.deep.IR.Predef._

/** contains all info about a method except the body of the method
  * used body methods to gather the needed data when calling this method
  * 
  * @param symbol   method symbol
  * @param tparams  method type parameters
  * @param vparams  method parameters
  * @param body method body
  * @param blocking indicates if the method body contains wait or blocking calls B.b()
  * @tparam A return value type
  */

class MethodInfo[A0](val symbol: String,
                    val tparams: List[IR.TypParam],
                    val vparams: List[List[IR.Variable[_]]], 
                    val body: OpenCode[A0], 
                    val blocking: Boolean)(implicit val A: CodeType[A0]) {
  def replica(newSym: String): MethodInfo[A] = {
    new MethodInfo[A](newSym, this.tparams, this.vparams, this.body, this.blocking)(A)
  }

  val mtdName: String = symbol.split("\\.").tail.mkString(".")

  // symbol name, type
  private var bodyStr: String = body.showScala

  private val argSyms: Option[List[(String, String)]]
  =
    vparams match {
      case Nil => None
      case _ => {
        Some(vparams.head.map(x => {
          val recoverName = x.rep.show.split("@").head
          bodyStr = bodyStr.replaceAll(x.rep.show, recoverName)
          (recoverName, x.Typ.rep.toString())
        }))
      }
    }

  // Bind the list of Any in argss to corresponding parameters  
  def toWrapperDeclaration(): String = {
    val argBinds: String = {
      argSyms match {
        case None => 
          f"${mtdName}"
        case Some(x) => 
          f"""
          ${x.zipWithIndex.map(p => "val " + p._1._1  + ": " + p._1._2 + " = " + "args(" + p._2 + ").asInstanceOf[" + p._1._2 + "]").mkString("\n    ")}
          ${mtdName}(${x.map(p => p._1).mkString(",")})
          """
      }
    }

  f"""
  def wrapper_${mtdName}(args: List[Any]): ${A.rep.toString} = {
    ${argBinds}
  }
  """
  }

  def toDeclaration(): String = {
    argSyms match {
      case None =>
  f"""
  def ${mtdName}: ${A.rep.toString} =
      ${bodyStr}

  """
      case Some(x) =>
  f"""
  def ${mtdName}(${x.map(p => p._1 + ": " + p._2).mkString(",")}): ${A.rep.toString} = 
      ${bodyStr}
  """
    }
  }

  def toWrapperInvocation(): String = {
    f"""wrapper_${mtdName}(args)"""
  }

  def toInvocation(): String = {
    argSyms match {
      case None =>
        f"${mtdName}"
      case Some(x) =>
        f"""${mtdName}(${x.map(p => p._1).mkString(",")})"""
    }
  }

  type A = A0
}
