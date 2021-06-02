package meta.deep.member

import scala.collection.mutable.ListBuffer
import meta.deep.IR.Predef._
import squid.lib.MutVar
import meta.runtime.ResponseMessage

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
//  * @param responseMessagess a list of a map of responseMessagess, one for each original actortype
  */
case class CompiledActorGraph(
    var name: String,
    var parentNames: List[String],
    var graph: ListBuffer[EdgeInfo],
    var variables: List[VarWrapper[_]],
    var variables2: List[VarValue[_]],
    var actorTypes: List[ActorType[_]],
    var positionStack: List[Variable[ListBuffer[List[((Int, Int), Int)]]]], //required to generate popping from stack statements at create code
    returnValue: List[Variable[MutVar[Any]]],
    responseMessage: List[Variable[MutVar[ResponseMessage]]]
)