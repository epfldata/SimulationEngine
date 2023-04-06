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
  * @param defInGeneratedCode indicates if the method should be defined in the generated code or compiled away
  * @tparam A return value type
  */

class MethodInfo[A0](val name: String,
                    val tparams: List[IR.TypParam],
                    val vparams: List[List[IR.Variable[_]]], 
                    val body: OpenCode[A0], 
                    var defInGeneratedCode: Boolean)(implicit val A: CodeType[A0]) extends FieldOrMethod {
  def replica(newSym: String, inSubclass: Boolean): MethodInfo[A0] = {
    if (!inSubclass || modifiers.contains("override")){
      new MethodInfo[A0](newSym, this.tparams, this.vparams, this.body, this.defInGeneratedCode)(A)
    } else {
      val ans = new MethodInfo[A0](newSym, this.tparams, this.vparams, this.body, this.defInGeneratedCode)(A)
      "override" +=: ans.modifiers
      ans
    }
  }

  // symbol name, type
  private var bodyStr: String = body.showScala
  
  // A hack to get around the weired inlining CodeType
  private var returnType: String = A.rep.toString
  private val inlinedPrimitives: List[String] = List("Int", "Boolean", "Double", "String", "Long", "Short", "Float", "Char").map(x => f"${x}(")
  if (inlinedPrimitives.exists(x => returnType.startsWith(x))) {
    returnType = returnType.split("\\(").head
  }

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
          s"${name}"
        case Some(x) => 
          s"""
          ${x.zipWithIndex.map(p => "val " + p._1._1  + ": " + p._1._2 + " = " + "args(" + p._2 + ").asInstanceOf[" + p._1._2 + "]").mkString("\n    ")}
          ${name}(${x.map(p => p._1).mkString(",")})
          """
      }
    }

  s"""
  private def wrapper_${name}(args: List[Any]): ${returnType} = {
    ${argBinds}
  }
  """
  }

  def toDeclaration(): String = {
    argSyms match {
      case None =>
  f"""
  ${modifiers.mkString(" ")} def ${name} : ${returnType} =
      ${bodyStr}
  """
      case Some(x) =>
  f"""
  ${modifiers.mkString(" ")} def ${name}${vparams.map(vps => vps.map(vp =>
          s"${vp.`internal bound`.name}: ${vp.Typ.rep}"
        ).mkString("(",",",")")).mkString} : ${returnType} = 
      ${bodyStr}
  """
    }
  }

  def toWrapperInvocation(): String = {
    f"wrapper_${name}(args)"
  }

  def toInvocation(): String = {
    argSyms match {
      case None =>
        f"${name}"
      case Some(x) =>
        f"${name}(${x.map(_._1).mkString(",")})"
    }
  }

  type A = A0
}
