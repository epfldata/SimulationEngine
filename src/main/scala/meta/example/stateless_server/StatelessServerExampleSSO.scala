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
  val cls2: ClassWithObject[Serverstateless] = Serverstateless.reflect(IR)
  val cls3: ClassWithObject[InitActors] = InitActors.reflect(IR)

  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val mainClass = cls3

  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(
    new CreateActorGraphs(simulationData._1),
    List(
      new SSO,
      new EdgeMerge(),
      new CreateCode(simulationData._2, "generated/main/scala"),
    ))

  pipeline.run()
}

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  1819690544, 1945948394, 2037225377. best: 1819690544
//100 iterations, 100000 actors: 14475712676, 14506989436, 14960590355. best: 14475712676
