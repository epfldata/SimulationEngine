package meta.example.stateless_server

import meta.classLifting.Lifter
import meta.deep.IR
import meta.deep.codegen.{CreateActorGraphs, CreateCode, EdgeMerge, Pipeline}
import meta.deep.runtime.Actor
import IR.TopLevel._

object StatelessServerExample extends App {

  val cls1: ClassWithObject[RandomPrinter] = RandomPrinter.reflect(IR)
  val cls2: ClassWithObject[RandomNumberServer] = RandomNumberServer.reflect(IR)
  val cls3: ClassWithObject[InitActors] = InitActors.reflect(IR)

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

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  3279097288, 3218413896, 3196279381. best: 3196279381
//100 iterations, 100000 actors: 28772399683, 28164466706, 28342239824. best: 28164466706
