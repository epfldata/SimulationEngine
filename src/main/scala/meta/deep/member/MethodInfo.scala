package meta.deep.member

import meta.deep.IR
import meta.deep.IR.Predef._

/** contains all info about a method except the body of the method
  * used body methods to gather the needed data when calling this method
  * 
  * @param symbol   method symbol
  * @param tparams  method type parameters
  * @param vparams  method parameters
  * @tparam A return value type
  */

class MethodInfo[A0: CodeType](val symbol: String,
                    val tparams: List[IR.TypParam],
                    val vparams: List[List[IR.Variable[_]]], 
                    val body: OpenCode[A0]) {
  def replica(newSym: String): MethodInfo[A0] = {
    new MethodInfo[A0](newSym, this.tparams, this.vparams, this.body)
  }

  type A = A0 
}
