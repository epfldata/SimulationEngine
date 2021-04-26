package meta.deep.member

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.algo.Algo

/**
  * Contains the information of a lifted method
  * Due to current constraints in squid, you have to pass the class and implement mtd of your own.
  *
  * @param symbol   of lifted method
  * @param cls      of lifted method
  * @param body     algorithm of method
  * @param tparams  method type parameters
  * @param vparams  method parameters
  * @param methodId unique id of the method inside a cls
  * @param blocking if method body contains wait or blocking calls B.b()
  * @tparam R type of return value
  */
class LiftedMethod[R](val symbol: String, 
                      val body: Algo[R],
                      val tparams: List[IR.TypParam],
                      val vparams: List[List[IR.Variable[_]]], 
                      val methodId: Int, 
                      val blocking: Boolean)(implicit val R: CodeType[R]) {

  override def hashCode(): Int = symbol.hashCode()

  override def equals(that: Any): Boolean = that match {
    case that: LiftedMethod[_] => that.symbol == symbol
  }

  override def toString =
    symbol 
}

object Method {
  var lastMethodId = 0

  /** Gets the next available id for methods
    *
    * @return next available id for a method
    */
  def getNextMethodId: Int = {
    val tmp = lastMethodId
    lastMethodId = lastMethodId + 1
    tmp
  }
}
