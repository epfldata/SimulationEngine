package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{EdgeInfo, VarWrapper}
import meta.deep.member.{ActorType, ResponseMessage}
import squid.lib.MutVar

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

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

case class CompiledActorGraph(
    var name: String,
    var graph: ArrayBuffer[EdgeInfo],
    var variables: List[VarWrapper[_]],
    var variables2: List[VarValue[_]],
    var actorTypes: List[ActorType[_]],
    var positionStack: List[Variable[ListBuffer[List[((Int, Int), Int)]]]], //required to generate poping from stack statements at create code
    returnValue: List[Variable[MutVar[Any]]],
    responseMessage: List[Variable[MutVar[ResponseMessage]]],
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
