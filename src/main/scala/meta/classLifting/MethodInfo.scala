package meta.classLifting

import meta.deep.IR
import meta.deep.IR.Predef._

/** contains all info about a method except the body of the method
  * used body methods to gather the needed data when calling this method
  *
  * @param symbol   method symbol
  * @param tparams  method type parameters
  * @param vparams  method parameters
  * @param blocking is the method blocking (it will be blocking if return value type [[A]] is anything other than [[NBUnit]]
  * @tparam A return value type
  */
class MethodInfo[A: CodeType](val symbol: IR.MtdSymbol,
                              val tparams: List[IR.TypParam],
                              val vparams: List[List[IR.Variable[_]]],
                              val blocking: Boolean)
