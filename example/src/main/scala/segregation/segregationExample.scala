package meta.example.segregation

import meta.classLifting.Lifter
import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

object segregationExample extends App{
  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val cls2: ClassWithObject[WorldMap] = WorldMap.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls2, cls1)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}