package meta.example.server_communication

import meta.classLifting.Lifter
import meta.deep.IR
import meta.deep.IR.TopLevel._
import meta.deep.codegen._
import meta.deep.runtime.Actor

object ServerExampleMerge extends App {
  val cls1: ClassWithObject[BackendServer] = BackendServer.reflect(IR)
  val cls2: ClassWithObject[FrontendServer] = FrontendServer.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2)
  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(
    new CreateActorGraphs(simulationData._1),
    List(
      new ActorMerge(),
      new GraphMerge(),
      new CreateCode(simulationData._2, "generated/main/scala"),
    ))

  pipeline.run()
}

/*
Evaluation with 3 runs:
100 runs:   23562386947 |  24190733500 |  23102046062 ... best: 23102046062 (better as without merge: 2875670015)
1000 runs: 227743748649 | 225266706671 | 225434987462 ... best 225266706671 (better as without merge: 8792827844)
Conclusion: Init array takes a lot of time in beginning for 100000 messages, thus the time benefit for the next 900 runs
is not that big anymore. But merging still shows benefits, the time difference has been increased with more runs
 */