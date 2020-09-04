package meta.example.codegen_example

import _root_.meta.classLifting.Lifter
import _root_.meta.deep.IR
import _root_.meta.deep.IR.TopLevel._
import _root_.meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
import _root_.meta.deep.runtime.Actor

object LifterCodegenExample extends App {
  val cls1: ClassWithObject[Market] = Market.reflect(IR)
  val cls2: ClassWithObject[Farmer] = Farmer.reflect(IR)
  val cls3: ClassWithObject[InitClass] = InitClass.reflect(IR)

  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val mainClass = cls3

  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(
    new CreateActorGraphs(simulationData._1),
    List(
      new EdgeMerge(),
      new CreateCode(simulationData._2, "generated/main/scala"),
    ))

  pipeline.run()
}
