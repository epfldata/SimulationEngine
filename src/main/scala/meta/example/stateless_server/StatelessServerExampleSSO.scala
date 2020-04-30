package meta.example.stateless_server

import meta.classLifting.Lifter
import meta.deep.IR
import meta.deep.codegen.{
  CreateActorGraphs,
  CreateCode,
  EdgeMerge,
  Pipeline,
  SSO
}
import meta.deep.runtime.Actor
import IR.TopLevel._

object StatelessServerExampleSSO extends App {

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
      new SSO(List("RandomNumberServer")),
      new EdgeMerge(),
      new CreateCode(simulationData._2, "generated/main/scala"),
    )
  )

  pipeline.run()
}

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  1834609610, 1784664607, 1877337379. best: 1784664607
//100 iterations, 100000 actors: 15300657512, 15051753104, 15022346471. best: 15022346471
