package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.member.{CodeNodeMtd, CodeNodePos, EdgeInfo}
import meta.deep.algo.{AlgoInfo, CallMethod, Send}
import meta.deep.codegen.CreateActorGraphs._
import meta.deep.member._
import meta.runtime.ResponseMessage
import squid.lib.MutVar
import meta.runtime.Actor
import meta.deep.member.Method

import scala.collection.mutable.{ListBuffer, Map}

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


object utilObj {

  def createCallMethodEdges(methodId: Int,
                            sendEdge: EdgeInfo): ListBuffer[EdgeInfo] = {

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

  def rewriteCallMethod(edges: ListBuffer[EdgeInfo]): ListBuffer[EdgeInfo] = {
    edges.foreach(edge => {
      edge.code = edge.code.rewrite({
        case code"meta.deep.algo.Instructions.saveMethodParam(${Const(a)}, ${Const(
        b)}, $c) " =>
          val stack: ListBuffer[Variable[ListBuffer[Any]]] =
            methodVariableTableStack(a)
          val varstack: Variable[ListBuffer[Any]] = stack(b)

          val variable: MutVarType[_] = methodVariableTable(a)(b)

          variable match {
            case v: MutVarType[a] =>
              code"$varstack.prepend($c); ${v.variable} := $c.asInstanceOf[${v.codeType}]"
            case _ => throw new RuntimeException("Illegal state")
          }
        case code"meta.deep.algo.Instructions.restoreMethodParams(${Const(a)}) " =>
          val stack: ListBuffer[Variable[ListBuffer[Any]]] =
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

  def mtdToPosNodes(graph: ListBuffer[EdgeInfo]): Unit = {
    graph.foreach(edge => {
      edge.from match {
        case c: CodeNodeMtd =>
          val id = c.id
          val methodGraph = graph.filter(edge1 => edge1.methodId1 == id)
          if (!c.end) {
            edge.from =
              CodeNodePos(methodGraph.head.from.asInstanceOf[CodeNodePos].pos)
          }
          //case of interest: jump from the end of the method
          else {
            edge.from =
              CodeNodePos(methodGraph.last.to.asInstanceOf[CodeNodePos].pos)
          }
        case _ =>
      }
      edge.to match {
        case c: CodeNodeMtd =>
          val id = c.id
          val methodGraph = graph.filter(edge1 => edge1.methodId1 == id)
          //case of interest: jump to the beginning of the method
          if (!c.end) {
            edge.to =
              CodeNodePos(methodGraph.head.from.asInstanceOf[CodeNodePos].pos)
          } else {
            edge.to =
              CodeNodePos(methodGraph.last.to.asInstanceOf[CodeNodePos].pos)
          }
        case _ =>
      }
    })
  }

  def getFreePos(graph: ListBuffer[EdgeInfo]): Int = {
    graph.flatMap(edge => edge.from :: edge.to :: Nil)
      .maxBy(node =>
        node match {
          case c: CodeNodeMtd => -1
          case c: CodeNodePos => c.pos
        })
      .getNativeId + 1
  }

  /** used to translate the graph by a moveAmmount, after the moveThreshold
    * for each edge that has a from or to above the moveThreshold, add moveAmmount to it
    *
    * @param moveAmount how much to move the graph
    * @param moveThreshold after which position to start moving
    */
  def moveGraphPositions(graph: ListBuffer[EdgeInfo], moveAmount: Int, moveThreshold: Int): Unit = {
    graph.foreach(edge => {
      edge.from match {
        case c: CodeNodePos =>
          if (c.pos > moveThreshold)
            edge.from = CodeNodePos(c.pos + moveAmount)
        case _ =>
      }
      edge.to match {
        case c: CodeNodePos =>
          if (c.pos > moveThreshold)
            edge.to = CodeNodePos(c.pos + moveAmount)
        case _ =>
      }
    })
  }

  /*
  For two compiled actor graphs, remove instances of each other from all variables
   */
  def mergeVariables(graph1: CompiledActorGraph, graph2: CompiledActorGraph): (List[VarWrapper[_]], List[VarValue[_]]) = {
    val variables1: List[VarWrapper[_]] =
      graph1.variables.filter(x => !(x.A <:< graph2.actorTypes.head.X)) :::
        graph2.variables.filter(x => !(x.A <:< graph1.actorTypes.head.X))

    val variables2: List[VarValue[_]] = graph1.variables2 :::
      graph2.variables2

    (variables1, variables2)
  }

  def newCallMethodEdges(methodId: Int,
                         sendEdge: EdgeInfo): ListBuffer[EdgeInfo] = {
    val newEdges: ListBuffer[EdgeInfo] = utilObj.createCallMethodEdges(methodId, sendEdge)
    utilObj.rewriteCallMethod(newEdges)
  }

  /*
    Replace edges related to the leading send edge with local method calls identified by method id in the leadingSendEdge in graph. Modify the original graph directly
   */
  def replaceSends(graph: ListBuffer[EdgeInfo], graphId: Int, leadingSendEdge: ListBuffer[(Int, EdgeInfo)]): Unit = {

    val lastOffset: Int = 4 // four edges after the leading edge (total four send edges)

    var newEdgesMap: Map[Int, ListBuffer[EdgeInfo]] = Map[Int, ListBuffer[EdgeInfo]]()

    // TODO: add support for analysing whether the lead edges to be removed are truly redundant or bind variable arguments. See example in merge with comm for such case
    val leadingEdgesWithFirstOffset: ListBuffer[(Int, EdgeInfo, Int)] =
      leadingSendEdge.map(edge => {
        var firstOffset: Int = 0
        var found: Boolean = false
        var currentEdge: EdgeInfo = edge._2
        graph.splitAt(graph.indexOf(edge._2))._1.reverse.foreach(
          previousEdge => if (!found) {
            if (currentEdge.from == previousEdge.to){
              currentEdge = previousEdge
              firstOffset = firstOffset + 1 // count the number of edges, not nodes
            } else {
              found = true
            }
          }
        )
      (edge._1, edge._2, firstOffset)
      })

    graph --= leadingEdgesWithFirstOffset
      .map(methodId_sendEdge => {
        val edgeIdx = graph.indexOf(methodId_sendEdge._2)
        newEdgesMap = newEdgesMap + (edgeIdx -> {
          val newEdges: ListBuffer[EdgeInfo] = newCallMethodEdges(methodId_sendEdge._1, methodId_sendEdge._2)
          newEdges.head.from = graph(edgeIdx - methodId_sendEdge._3).from
          newEdges.last.to = graph(edgeIdx + lastOffset).to
          newEdges.foreach(x => {
            x.positionStack = methodId_sendEdge._2.positionStack
            x.graphId = graphId
          })
          newEdges
        })
        (edgeIdx, methodId_sendEdge._3)
      })
      .flatMap(x => Range(x._1-x._2, x._1 + lastOffset + 1)) // Range exclude the last element
      .map(x => graph(x))

    newEdgesMap.values.foreach(x => graph++=x)
    mtdToPosNodes(graph)
  }

  /*
  Return a copy of the subgraph related to methodId.
   */
  def getEdgesByMtdId(graph: ListBuffer[EdgeInfo], methodId: Int): ListBuffer[EdgeInfo] = {
    graph.filter(edge => edge.methodId1== methodId)
      .map(edge => edge.myCopy())
  }

  /*
  Replace "this1" and "return1" for graph with "this2" and "return2". Modify the graph directly
   */
  def resetThisReturn(graph: ListBuffer[EdgeInfo],
                      this1: Variable[Actor],
                      this2: Variable[Actor],
                      return1: Variable[MutVar[Any]],
                      return2: Variable[MutVar[Any]]): Unit ={
    graph.foreach(edge =>
      if (edge.code!= null){
//        edge.code = edge.code.subs(this1).~>(this2.toCode)
        edge.code = edge.code.subs(return1).~>(return2.toCode)
      })
  }

  def copyRuntimeMtd(oldMtdId: Int): Int = {
    val newMtdId: Int = Method.getNextMethodId
    CreateActorGraphs.methodVariableTableStack(newMtdId) = CreateActorGraphs.methodVariableTableStack(oldMtdId)
    CreateActorGraphs.methodVariableTable(newMtdId) = CreateActorGraphs.methodVariableTable(oldMtdId)
    newMtdId
  }
}

