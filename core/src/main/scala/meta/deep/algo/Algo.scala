package meta.deep.algo

import meta.deep.IR.Predef._

/**
  * This class is an abstract class used to represent different program fragments of a lifted class
  * It implements a codegen method, which has to be implemented to generated new code of the algorithm.
  *
  * @param tpe type of return value
  * @tparam A return value of Algo
  */
abstract class Algo[A](implicit val tpe: CodeType[A]) {

  /**
    * Generates appropriate code for the statement
    * You have to add the code segments into the AlgoInfo.stateGraph
    */
  def codegen(): Unit
}
