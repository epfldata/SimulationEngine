package meta.example

object vanillaCompile {
  import meta.classLifting.Lifter
  import meta.deep.IR.TopLevel._
  import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
  import meta.deep.runtime.Actor

  def apply(startClasses: List[Clasz[_ <: Actor]], mainClass: Clasz[_], packageName: String): Unit = {
    val lifter = new Lifter()
    val simulationData = lifter(startClasses, mainClass)

    val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
      new EdgeMerge(),
      new CreateCode(simulationData._2, "generated/main/scala/" + packageName, "generated." + packageName),
    ))

    pipeline.run()
  }
}
