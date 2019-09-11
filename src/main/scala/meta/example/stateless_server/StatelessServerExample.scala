package meta.example.stateless_server

import meta.classLifting.Lifter
import meta.deep.IR
import meta.deep.codegen.{CreateActorGraphs, CreateCode, GraphMerge, Pipeline}
import meta.deep.runtime.Actor
import IR.TopLevel._

object StatelessServerExample extends App {

  val cls1: ClassWithObject[RandomPrinter] = RandomPrinter.reflect(IR)
  val cls2: ClassWithObject[Serverstateless] = Serverstateless.reflect(IR)
  val cls3: ClassWithObject[InitActors] = InitActors.reflect(IR)

  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val mainClass = cls3

  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(
    new CreateActorGraphs(simulationData._1),
    List(
      new GraphMerge(),
      new CreateCode(simulationData._2, "generated/main/scala"),
    ))

  pipeline.run()
}

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  3658638346, 3270125500, 3372795931. best: 3270125500
//100 iterations, 100000 actors: 29517966109, 28983620441, 30271580639. best: 28983620441
