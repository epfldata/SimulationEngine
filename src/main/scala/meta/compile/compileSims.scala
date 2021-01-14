package meta.compile 

object compileSims {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline, StateMachineElement, ActorMerge, SSO}
  import meta.runtime.Actor

  def apply(startClasses: List[Clasz[_ <: Actor]], mainClass: Clasz[_], mode: CompilationMode = Vanilla, destFolder: String=""): Unit = {

    val simulationData = Lifter(startClasses, mainClass)

    var statemachineElements: List[StateMachineElement] = List(new EdgeMerge())

    var nameMap: Map[String, String] = Map[String, String]()
    
    val canonicalName: String = mainClass.getClass.getPackage.getName()

    nameMap = nameMap + ("Main" -> canonicalName)

    startClasses.foreach(x => {
      nameMap = nameMap + (x.name -> x.getClass.getPackage().getName())
    })
    
    statemachineElements = mode match {
      case Vanilla => 
        statemachineElements
      case SimsMerge(namePairs) =>
        new ActorMerge(namePairs) :: statemachineElements
      case SimsStateless(statelessServers) =>
        new SSO(statelessServers) :: statemachineElements
    }

    val destFolderName = destFolder match {
      case "" => "generated/src/main/scala/" + canonicalName
      case s => s 
    }

    mode.setPackage(nameMap)
    
    statemachineElements = statemachineElements :+ new CreateCode(simulationData._2,
      destFolderName, 
      mode)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), statemachineElements)

    pipeline.run()
  }
}
