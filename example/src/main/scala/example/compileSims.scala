package meta.example

import meta.deep.codegen.StateMachineElement
import meta.deep.codegen.ActorMerge
import meta.deep.codegen.SSO

sealed trait CompilationMode
// no optimization
case class Vanilla() extends CompilationMode
// merged Sims.
case class SimsMerge(namePairs: List[(String, String)]) extends CompilationMode
// stateless-server optimization
case class SimsStateless(statelessServers: List[String]) extends CompilationMode

object compileSims {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
  import meta.deep.runtime.Actor

  def apply(startClasses: List[Clasz[_ <: Actor]], mainClass: Clasz[_], packageName: String, mode: CompilationMode = Vanilla()): Unit = {

    val lifter = new Lifter()
    val simulationData = lifter(startClasses, mainClass)

    var statemachineElements: List[StateMachineElement] = List(new EdgeMerge())

    statemachineElements = mode match {
      case Vanilla() => statemachineElements
      case SimsMerge(namePairs) => new ActorMerge(namePairs) :: statemachineElements
      case SimsStateless(statelessServers) => new SSO(statelessServers) :: statemachineElements
    }

    statemachineElements = statemachineElements :+ new CreateCode(simulationData._2,
      "example/src/main/scala/generated/" + packageName,
      "generated." + packageName)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), statemachineElements)

    pipeline.run()
  }
}
