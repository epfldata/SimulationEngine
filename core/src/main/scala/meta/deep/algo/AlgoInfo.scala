package meta.deep.algo

import meta.deep.IR.Predef._
import meta.runtime.{Future, ResponseMessage}
import squid.lib.MutVar

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

import meta.deep.member.VarWrapper

/**
  * This object contains shared data between the algo codegens
  * so that it can be accessed from every algo subclass
  */
object AlgoInfo {

  /**
    * Variable indicates whether to proceed
    * Set to false if waiting 
    * finishing the current step
    */
  val unblockFlag: Variable[MutVar[Boolean]] = Variable[MutVar[Boolean]]

  /**
    * Variable containing the current position of the program.
    * It needs to be modified if the next step is not the next
    * program fragment, but a jump to somewhere else
    */
  val positionVar: Variable[MutVar[Int]] = Variable[MutVar[Int]]

  /**
    * Stores the edges to build up a state transition graph
    */
  val stateGraph: ListBuffer[EdgeInfo] = ListBuffer[EdgeInfo]()

  /**
    * This stack is used to save a position onto it, so that it can be used to jump
    * to it in a later part. For example, to jump back after a method call
    */
  var positionStack: Variable[ListBuffer[List[((Int, Int), Int)]]] =
    Variable[ListBuffer[List[((Int, Int), Int)]]]

  /**
    * This variables is used as a register to store the return value of a call.
    */
  var returnValue: Variable[MutVar[Any]] = Variable[MutVar[Any]]

  /**
    * This variable is used to save data about the response message, if a blocking call is made
    */
  var responseMessage: Variable[MutVar[ResponseMessage]] =
    Variable[MutVar[ResponseMessage]]

//  /**
//    * This variables saves data about the wait status of the blocking call
//    */
//  var waitMessage: Variable[MutVar[WaitMessage]] = Variable[MutVar[WaitMessage]]

  /**
    * List, saving all variables, which should be defined at the beginning
    */
  var variables: List[VarWrapper[_]] = List()

  /**
    * List, which saves all variables already defined, so that another definition of the variable is not required
    * is not necessary inside the used fragment.
    */
  var varSavers: List[VarWrapper[_]] = List[VarWrapper[_]]()

  /**
    * Current position/code fragment
    */
  var posCounter = 0

  /**
    * IF inside method, set to true, so that graph knows about that (just for displaying in a different color atm)
    */
  var isMethod = false
  var methodId: Int = -1

  //This resets the variables which have to be unique per actorType
  def resetData(): Unit = {
    this.stateGraph.clear()
    this.variables = List()
    this.varSavers = List()
    this.posCounter = 0
    this.positionStack = Variable[ListBuffer[List[((Int, Int), Int)]]]
    this.returnValue = Variable[MutVar[Any]]
    this.responseMessage = Variable[MutVar[ResponseMessage]]
  }

  /**
    * Helper, for jumping relatively to a different position
    *
    * @param offset relative jumping offset
    * @return Code containing the position modification
    */
  def jump(offset: Int) =
    code"$positionVar := ($positionVar!) + ${Const(offset)}"

  /**
    * Go to next code fragment
    */
  def nextPos() {
    posCounter += 1
  }

  def convertStageGraph(methodLookupTable: Map[Int, Int],
                        methodLookupTableEnd: Map[Int, Int]) {
    AlgoInfo.stateGraph.foreach(
      _.convertToPosOnly(methodLookupTable, methodLookupTableEnd))
  }
}
