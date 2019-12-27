package meta.example.supermarket.testItemsOnlyExample

import meta.classLifting.Lifter
import meta.deep.codegen._
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._

object testItemsOnlyExample extends App {
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val cls1: ClassWithObject[Item1] = Item1.reflect(IR) 
  val cls2: ClassWithObject[Item2] = Item2.reflect(IR) 
  val cls3: ClassWithObject[Item3] = Item3.reflect(IR) 
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2, cls3)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}
