package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodePos, EdgeInfo, VarWrapper}
import meta.deep.algo.{AlgoInfo, CallMethod, Send}
import meta.deep.codegen.CreateActorGraphs._
import meta.deep.member.ActorType
import meta.deep.runtime.ResponseMessage
import squid.lib.MutVar

import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}

/**
  * This is a class which contains the generation pipeline
  * @param convertElement a code fragment which converts the deep representation to the graph representation
  * @param stateMachineElements elements, which are applied on the deep representation.
  */
case class Pipeline(convertElement: ConvertElement,
                    stateMachineElements: List[StateMachineElement]) {
  def run(): Unit = {
    var stateMachine = convertElement.run()
    stateMachineElements.foreach(x => {
      stateMachine = x.run(stateMachine)
    })
  }
}

/**
  * One element in the pipeline
  */
abstract class PipelineElement() {}

/**
  * A subtype, which handles the conversion between the deep and the graph representation
  * @param actorTypes a list of actortypes, which should be handled
  */
abstract class ConvertElement(actorTypes: List[ActorType[_]])
    extends PipelineElement() {

  /**
    * This function runs the conversion step
    * @return a graph representation of the code
    */
  def run(): List[CompiledActorGraph]
}

/**
  * A subtype, which handles a graph representation conversion from one graph to another
  */
abstract class StateMachineElement() extends PipelineElement() {

  /**
    * This runs the conversion step
    * @param compiledActorGraphs a list of graphs from the previous step
    * @return modified graph
    */
  def run(
      compiledActorGraphs: List[CompiledActorGraph]): List[CompiledActorGraph]
}

/**
  * This class represents the graph representation used for working in StateMachineElement
  * @param name the name of the actortype
  * @param parentNames a list of parents' names
  * @param graph the graph of EdgeInfos containing the actual code
  * @param variables a list of variables which have been used in code and needs a conversion to MutVar
  * @param variables2 a list of variables which have been additionally added, where no conversion was necessary
  * @param actorTypes a list of actorTypes represented in this graph represented actortype
  * @param positionStack a list of positionStack, one for each original actortype
  * @param returnValue a list of returnValue, one for each original actortype
  * @param responseMessage a list of responseMessage, one for each original actortype
  * @param responseMessagess a list of a map of responseMessagess, one for each original actortype
  */
case class CompiledActorGraph(
    var name: String,
    var parentNames: List[String],
    var parameterList: List[(String, String)],
    var graph: ArrayBuffer[EdgeInfo],
    var variables: List[VarWrapper[_]],
    var variables2: List[VarValue[_]],
    var actorTypes: List[ActorType[_]],
    var positionStack: List[Variable[ListBuffer[List[((Int, Int), Int)]]]], //required to generate popping from stack statements at create code
    returnValue: List[Variable[MutVar[Any]]],
    responseMessage: List[Variable[MutVar[ResponseMessage]]],
    responseMessagess: List[Variable[Map[String, ResponseMessage]]]
)

/**
  * This class is used to create the variable stack for each parameter variable of a method
  *
  * @param variable to be used in code
  * @param init     code to init variable
  * @param A        type of variable
  * @tparam C variable type
  */
case class VarValue[C](variable: Variable[C], init: OpenCode[C])(
    implicit val A: CodeType[C]) {}

object utilObj {

  def createCallMethodEdges(methodId: Int,
                            sendEdge: EdgeInfo): ArrayBuffer[EdgeInfo] = {

    AlgoInfo.resetData()
    CallMethod[Any](methodId, sendEdge.sendInfo._1.argss).codegen()

    val newEdges = AlgoInfo.stateGraph.map(edge1 => {
      edge1.methodId1 = sendEdge.methodId1
      edge1.from match {
        case c: CodeNodePos =>
          edge1.from =
            CodeNodePos(c.pos + sendEdge.from.asInstanceOf[CodeNodePos].pos)
        case _ =>
      }
      edge1.to match {
        case c: CodeNodePos =>
          edge1.to =
            CodeNodePos(c.pos + sendEdge.from.asInstanceOf[CodeNodePos].pos)
        case _ =>
      }
      edge1
    })
    AlgoInfo.resetData()
    newEdges
  }

  def rewriteCallMethod(edges: ArrayBuffer[EdgeInfo]): ArrayBuffer[EdgeInfo] = {
    edges.foreach(edge => {
      edge.code = edge.code.rewrite({
        case code"meta.deep.algo.Instructions.setMethodParam(${Const(a)}, ${Const(
        b)}, $c) " =>
          val variable: MutVarType[_] = methodVariableTable(a)(b)

          variable match {
            case v: MutVarType[a] =>
              code"${v.variable} := $c.asInstanceOf[${v.codeType}]"
            case _ => throw new RuntimeException("Illegal state")
          }
        case code"meta.deep.algo.Instructions.saveMethodParam(${Const(a)}, ${Const(
        b)}, $c) " =>
          val stack: ArrayBuffer[Variable[ListBuffer[Any]]] =
            methodVariableTableStack(a)
          val varstack: Variable[ListBuffer[Any]] = stack(b)
          code"$varstack.prepend($c);"
        case code"meta.deep.algo.Instructions.restoreMethodParams(${Const(a)}) " =>
          val stack: ArrayBuffer[Variable[ListBuffer[Any]]] =
            methodVariableTableStack(a)
          val initCode: OpenCode[Unit] = code"()"
          stack.zipWithIndex.foldRight(initCode)((c, b) => {
            val variable: MutVarType[_] = methodVariableTable(a)(c._2)
            val ab = c._1
            variable match {
              case v: MutVarType[a] =>
                code"$ab.remove(0); if(!$ab.isEmpty) {${v.variable} := $ab(0).asInstanceOf[${v.codeType}]}; $b; ()"
              case _ => throw new RuntimeException("Illegal state")
            }
          })
      })
    })
    edges
  }

  /*
   Surround the graphs with 'wait edge' though there needs not to be latency penalty
   */
  def addGlue(edges: ArrayBuffer[EdgeInfo],
                 methodId: Int,
                 removeWait: Boolean = true): ArrayBuffer[EdgeInfo] = {
    val firstFrom = edges.head.from
    edges.foreach(edge1 => {
      edge1.to match {
        case c: CodeNodePos =>
          edge1.to = CodeNodePos(c.pos + 1)
        case _ =>
      }
      edge1.from match {
        case c: CodeNodePos =>
          edge1.from = CodeNodePos(c.pos + 1)
        case _ =>
      }
    })
    val w1 = AlgoInfo.EdgeInfo("wait",
      firstFrom,
      edges.head.from,
      code"()",
      waitEdge = !removeWait,
      methodId1 = methodId)
    val w2 = AlgoInfo.EdgeInfo("wait",
      edges.last.to,
      CodeNodePos(edges.last.to.asInstanceOf[CodeNodePos].pos + 1),
      code"()",
      waitEdge = !removeWait,
      methodId1 = methodId)
    edges.prepend(w1)
    edges.append(w2)
    edges
  }
}