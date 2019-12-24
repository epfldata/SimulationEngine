package meta.example.supermarket.testItemsOnlyExample

import meta.classLifting.Lifter
import meta.deep.codegen._
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._
import meta.example.supermarket.Customer
import meta.example.supermarket.goods.{Item1, Item2, Item3}

import scala.collection.mutable.ListBuffer

object testItemsOnlyExample extends App {
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val cls2: ClassWithObject[Item1] = Item1.reflect(IR)
  val cls3: ClassWithObject[Item2] = Item2.reflect(IR)
  val cls4: ClassWithObject[Item3] = Item3.reflect(IR)
  val cls5: ClassWithObject[Customer] = Customer.reflect(IR)

  val startClasses: ListBuffer[Clasz[_ <: Actor]] = ListBuffer(cls2, cls3, cls4, cls5)

  val lifter = new Lifter()

  val simulationData = lifter(startClasses.toList, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}
