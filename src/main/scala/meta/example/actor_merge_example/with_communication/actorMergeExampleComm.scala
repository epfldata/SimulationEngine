package meta.example.actor_merge_example.with_communication

import meta.classLifting.Lifter
import meta.deep.codegen.{ActorMerge, SSO, CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

object actorMergeExampleComm extends App{
  val cls1: ClassWithObject[object1] = object1.reflect(IR)
  val cls2: ClassWithObject[object2] = object2.reflect(IR)
//  val cls3: ClassWithObject[object3] = object3.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  //  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2, cls3)

  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
//    new SSO(List("object1")),
    new ActorMerge(List(("object1", "object2"))),
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}
