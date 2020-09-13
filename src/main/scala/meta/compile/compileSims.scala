package meta.compile 

object compileSims {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline, StateMachineElement, ActorMerge, SSO}
  import meta.deep.runtime.Actor
  import meta.compile.GeneratedPackage._

  def apply(startClasses: List[Clasz[_ <: Actor]], mainClass: Clasz[_], mode: CompilationMode = Vanilla): Unit = {

    val lifter = new Lifter()
    val simulationData = lifter(startClasses, mainClass)

    var statemachineElements: List[StateMachineElement] = List(new EdgeMerge())

    var canonicalName: String = mainClass.getClass.getPackage.getName()

    var pkgName: GeneratedPackage = vanillaPackage(canonicalName)

    statemachineElements = mode match {
      case Vanilla => 
        statemachineElements
      case SimsMerge(namePairs) =>
        pkgName = mergedPackage(canonicalName)
        new ActorMerge(namePairs) :: statemachineElements
      case SimsStateless(statelessServers) =>
        pkgName = ssoPackage(canonicalName)
        new SSO(statelessServers) :: statemachineElements
    }

    statemachineElements = statemachineElements :+ new CreateCode(simulationData._2,
      "example/src/main/scala/generated/" + canonicalName,
      pkgName)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), statemachineElements)

    pipeline.run()
  }
}
