package meta.deep.member 

import meta.deep.IR.Predef._
import squid.lib.MutVar

/**
    * Helper class to save mappings between a variable introduced to a mutable variable, which is used in
    * the program. This is necessary, since it is not possible to define var variable references in squid
    * at the moment, thus we have to use the MutVar Wrapper to change the variable afterwards
    *
    * @param from original variable defined by Algo
    * @param to   new mutation variable created as replacement for original one
    * @param A    code type of original variable
    * @tparam C type of original variable
    */
  case class VarWrapper[C](from: Variable[C], to: Variable[MutVar[C]])(
      implicit val A: CodeType[C])