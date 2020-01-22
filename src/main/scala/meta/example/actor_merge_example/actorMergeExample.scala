package meta.example.actor_merge_example

import meta.classLifting.Lifter
import meta.deep.codegen._
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

object actorMergeExample extends App {
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val cls1: ClassWithObject[actor1] = actor1.reflect(IR)
  val cls2: ClassWithObject[actor2] = actor2.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1),
    List(
    new ActorMerge(List(("actor1", "actor2"))),
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala/actorMergeExample", "actorMerge.generated"),
  ))

  pipeline.run()
}
