package meta.compile 

object compileSims {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen._
  import meta.runtime.Actor
  import meta.deep.IR.Predef._ 

  /**
    * Staging, convert Sims from source programs to object programs. 
    * We allow two ways to specify the main class which serve as entrypoint of the object programs, either lift an existing MainClass, or write as OpenCode[] directly.
    * @param startClasses: Sim classes which are staged
    * @param mainInit: Return Option[String]
    * @param initPkgName: the package name of init main. If empty, take the package name of the first agent in start classes
    * @param mode
    * @param destFolder
    */
  def apply(startClasses: List[Clasz[_ <: Actor]], 
            mainInit: Option[String] = None, 
            initPkgName: Option[String] = None, 
            mode: CompilationMode = Vanilla,
            destFolder: String=""): Unit = {

    val simulationData = (new Lifter()).apply(startClasses)

    var statemachineElements: List[StateMachineElement] = 
    List(new EdgeMerge())

    var nameMap: Map[String, String] = Map[String, String]()

    startClasses.foreach(x => {
      nameMap = nameMap + (x.name -> x.getClass.getPackage().getName())
    })

    // Can specify main init either way 
    val canonicalName = initPkgName match {
      case None => startClasses.head.getClass.getPackage.getName
      case Some(x) => x
    }
  
    nameMap = nameMap + ("Main" -> canonicalName)
    
    statemachineElements = mode match {
      case Vanilla => 
        statemachineElements
      case SimsMerge(namePairs) =>
        new ActorMerge(namePairs) :: statemachineElements
    }

    mode.setPackage(nameMap)

    val destFolderName = destFolder match {
      case "" => "generated/src/main/scala/" + 
      mode.pkgName.split("\\.").tail.mkString(".")
      case s => s 
    }

    println(s"DestFolder: $destFolderName\n" +
      s"Package name: ${mode.pkgName}")

    assert(mainInit.isDefined)

    statemachineElements = statemachineElements :+ new CreateCode(mainInit.get,
      destFolderName, 
      mode, 
      simulationData._2, 
      simulationData._3)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), statemachineElements)

    pipeline.run()
  }
}
