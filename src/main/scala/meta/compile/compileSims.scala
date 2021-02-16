package meta.compile 

object compileSims {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline, StateMachineElement, ActorMerge, SSO}
  import meta.runtime.Actor
  import meta.deep.IR.Predef._ 

  /**
    * Staging, convert Sims from source programs to object programs. 
    * We allow two ways to specify the main class which serve as entrypoint of the object programs, either lift an existing MainClass, or write as OpenCode[] directly.
    * @param startClasses: Sim classes which are staged
    * @param mainClass: Return List[Actor] including *all* Sims needed when simulation starts
    * @param mainInit: Return OpenCode[Unit]
    * @param initPkgName: the package name of init main. If empty, take the package name of the first agent in start classes
    * @param mode
    * @param destFolder
    */
  def apply(startClasses: List[Clasz[_ <: Actor]], 
            mainClass: Option[Clasz[_]] = None, 
            mainInit: Option[OpenCode[Unit]] = None, 
            initPkgName: String = "", 
            mode: CompilationMode = Vanilla, 
            destFolder: String=""): Unit = {

    val simulationData = Lifter(startClasses)

    var statemachineElements: List[StateMachineElement] = List(new EdgeMerge())

    var nameMap: Map[String, String] = Map[String, String]()

    var canonicalName: String = ""

    startClasses.foreach(x => {
      nameMap = nameMap + (x.name -> x.getClass.getPackage().getName())
    })

    // Can specify main init either way 
    (mainClass, mainInit) match {
      case (Some(x), _) => {
        canonicalName = x.getClass.getPackage.getName()
      }
      case (None, Some(x)) => {
        if(initPkgName.isEmpty()){
          canonicalName = startClasses.head.getClass.getPackage.getName()
        } else {
          canonicalName = initPkgName 
        }
      }
      case (None, None) => throw new Exception("MainInit not defined!") 
    }
  
    nameMap = nameMap + ("Main" -> canonicalName)
    
    statemachineElements = mode match {
      case Vanilla => 
        statemachineElements
      case SimsMerge(namePairs) =>
        new ActorMerge(namePairs) :: statemachineElements
      case SimsStateless(statelessServers) =>
        new SSO(statelessServers) :: statemachineElements
    }

    mode.setPackage(nameMap)

    val destFolderName = destFolder match {
      case "" => "generated/src/main/scala/" + 
      mode.pkgName.split("\\.").tail.mkString(".")
      case s => s 
    }

    println(s"DestFolder: $destFolderName\n" +
      s"Package name: ${mode.pkgName}")
    
    val stagedMain: OpenCode[_] = mainInit match {
      case None => Lifter.liftInitCode(mainClass.get)
      case Some(x) => x
    }

    statemachineElements = statemachineElements :+ new CreateCode(stagedMain,
      destFolderName, 
      mode)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData), statemachineElements)

    pipeline.run()
  }
}
