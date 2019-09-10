package meta.example.server_communication

import meta.classLifting.Lifter
import meta.deep.codegen.{ActorMerge, CreateActorGraphs, CreateCode, GraphMerge, Pipeline}
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

object ServerExample extends App {
  val cls1: ClassWithObject[BackendServer] = BackendServer.reflect(IR)
  val cls2: ClassWithObject[FrontendServer] = FrontendServer.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new GraphMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}

/*
Evaluation with 3 runs:
100 runs:   27815563790 |  27625830142 |  25977716077 ... best:  25977716077
1000 runs: 234590483896 | 242222868950 | 234059534515 ... best: 234059534515
 */