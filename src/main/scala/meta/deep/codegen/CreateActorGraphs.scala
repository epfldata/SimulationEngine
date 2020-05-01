package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodePos, EdgeInfo}
import meta.deep.algo.{Algo, AlgoInfo, NoOp, ScalaCode}
import meta.deep.member.ActorType
import squid.lib.MutVar

import scala.annotation.tailrec
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object CreateActorGraphs {

  /** for each method, maps its id to an array buffer of [[MutVarType]] that it uses
    *
    */
  val methodVariableTable
    : collection.mutable.Map[Int, ArrayBuffer[MutVarType[_]]] =
    collection.mutable.Map[Int, ArrayBuffer[MutVarType[_]]]()

  /** for each method, maps its id to a stack of the parameters that it uses
    *
    */
  val methodVariableTableStack
    : collection.mutable.Map[Int, ArrayBuffer[Variable[ListBuffer[Any]]]] =
    collection.mutable.Map[Int, ArrayBuffer[Variable[ListBuffer[Any]]]]()

  /** a wrapper that holds the type of the inner MutVar
    *
    * @tparam A type of variable
    */
  case class MutVarType[A](variable: Variable[MutVar[A]], codeType: CodeType[A])

}

class CreateActorGraphs(actorTypes: List[ActorType[_]])
    extends ConvertElement(actorTypes) {

  import CreateActorGraphs._

  /**
    * Contains the mapping between method id and start position of the method in graph
    */
  private val methodLookupTable: collection.mutable.Map[Int, Int] =
    collection.mutable.Map[Int, Int]()

  /**
    * Contains the mapping between method id and the end position of the method in graph
    */
  private val methodLookupTableEnd: collection.mutable.Map[Int, Int] =
    collection.mutable.Map[Int, Int]()

  /**
    * Is used to save variables, which need to be globally initialized
    */
  private var variables: List[VarValue[_]] = List()

  override def run(): List[CompiledActorGraph] = {
    val graphs = actorTypes.map(createCompiledActorGraph)
    //graphs.foreach(g => GraphDrawing.drawGraph(g.graph, g.name + "_original"))
    graphs
  }

  /**
    * This code generates the complete code fragments of the actor
    *
    * @return a list of code, containing all code fragments of main code and method codes + initialized variable tables
    */
  def createCompiledActorGraph(actorType: ActorType[_]): CompiledActorGraph = {
    AlgoInfo.resetData()

    this.variables = List()

    // Generate code for main
    createCode(actorType.main.asInstanceOf[Algo[Any]], isMethod = false)

    // Generate code for methods
    val methodData = actorType.methods.map({ method =>
      methodLookupTable(method.methodId) = AlgoInfo.posCounter
      createCode(method.body.asInstanceOf[Algo[Any]],
                 isMethod = true,
                 method.methodId)
      methodLookupTableEnd(method.methodId) = AlgoInfo.posCounter - 1

      val varList = ListBuffer[MutVarType[_]]()

      method.mtd.vparamss.foreach(paramList => {
        paramList.foreach({
          case param: Variable[v] =>
            //To be honest, dont know why this is working now (Type)
            def ttt[Z](variable: Variable[Z]): Unit = {
              val cT = variable.Typ
              val methodArgsMut: Variable[MutVar[variable.Typ]] =
                Variable[MutVar[variable.Typ]]
              AlgoInfo.variables = AlgoInfo
                .VarWrapper[Z](variable, methodArgsMut) :: AlgoInfo.variables
              varList.append(MutVarType(methodArgsMut, cT))
            }

            ttt(param)
        })
      })
      (varList, method.methodId)
    })

    // Fill the tables
    createVariableTable(methodData.map(x => (x._2, x._1.toList)))
    createVariableTableStack(methodData.map(x => (x._2, x._1.length)))

    AlgoInfo.convertStageGraph(methodLookupTable.toMap,
                               methodLookupTableEnd.toMap)

    utilObj.rewriteCallMethod(AlgoInfo.stateGraph)

    expandEndNodes()

    variables = VarValue(AlgoInfo.returnValue, code"MutVar[Any](null)") :: VarValue(
      AlgoInfo.positionStack,
      code"ListBuffer[List[((Int,Int),Int)]]()") :: VarValue(
      AlgoInfo.responseMessage,
      code"MutVar[meta.deep.runtime.ResponseMessage](null)") :: VarValue(
      AlgoInfo.responseMessagess,
      code"collection.mutable.Map[String, meta.deep.runtime.ResponseMessage]()") :: variables

    CompiledActorGraph(
      actorType.name,
      actorType.parentNames,
      actorType.parameterList,
      AlgoInfo.stateGraph.clone(),
      AlgoInfo.variables,
      variables,
      List[ActorType[_]](actorType),
      List[Variable[ListBuffer[List[((Int, Int), Int)]]]](
        AlgoInfo.positionStack),
      List(AlgoInfo.returnValue),
      List(AlgoInfo.responseMessage),
      List(AlgoInfo.responseMessagess)
    )
  }

  /**
    * expand a graph with a loop at the end, so that we wait at the end until simulation is finished (no dead end)
    */
  def expandEndNodes(): Unit = {
    val graphEnd = AlgoInfo.stateGraph.groupBy(_.to.getNativeId)
    val graphStart = AlgoInfo.stateGraph.groupBy(_.from.getNativeId)
    AlgoInfo.methodId = -1
    graphEnd.foreach(x => {
      if (!graphStart.contains(x._1)) {
        AlgoInfo.stateGraph.append(
          EdgeInfo("endWait",
                   CodeNodePos(x._1),
                   CodeNodePos(AlgoInfo.posCounter),
                   code"()",
                   waitEdge = true))
        AlgoInfo.stateGraph.append(
          EdgeInfo("endWait r",
                   CodeNodePos(AlgoInfo.posCounter),
                   CodeNodePos(x._1),
                   code"()"))
        AlgoInfo.nextPos()
      }
    })
  }

  /**
    * Inits the method variable lookup table
    *
    * @param data list of (methodId, list of parameter variables)
    */
  @tailrec
  private def createVariableTable(
      data: List[(Int, List[MutVarType[_]])]): Unit = data match {
    case x :: xs =>
      methodVariableTable(x._1) = ArrayBuffer(x._2: _*)
      createVariableTable(xs)
    case Nil => ()
  }

  /**
    * Inits the stack for the method variable storage
    *
    * @param data format List[(methodId, amount of variables)]
    */
  @tailrec
  private def createVariableTableStack(data: List[(Int, Int)]): Unit =
    data match {
      case x :: xs =>
        val a = ArrayBuffer[Variable[ListBuffer[Any]]]()
        for (_ <- 0 until x._2) {
          val x = Variable[ListBuffer[Any]]
          a.append(x)
          variables = VarValue(x, code"ListBuffer[Any]()") :: variables
        }
        methodVariableTableStack(x._1) = a
        createVariableTableStack(xs)
      case Nil => ()
    }

  /**
    * Generate the stepwise code for the given algo
    *
    * @param algo     contains the code, which should be generated
    * @param isMethod if true, then a restore position is added at the end
    * @return a list of code steps
    */
  private def createCode(algo: Algo[_],
                         isMethod: Boolean,
                         methodId: Int = -1): Unit = {
    AlgoInfo.isMethod = isMethod
    AlgoInfo.methodId = methodId

    //Method body is empty
    if (algo.isInstanceOf[NoOp[_]]) {
      ScalaCode(code"()").codegen()
    } else {
      algo.codegen()
    }
    AlgoInfo.nextPos()
  }

}
