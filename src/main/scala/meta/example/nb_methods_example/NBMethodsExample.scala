package meta.example.nb_methods_example

import meta.classLifting.Lifter
import meta.deep.codegen._
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

object NBMethodsExample extends App {
  val cls1: ClassWithObject[Object1] = Object1.reflect(IR)
  val cls2: ClassWithObject[Object2] = Object2.reflect(IR)
  val cls3: ClassWithObject[Object3] = Object3.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2, cls3)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}
