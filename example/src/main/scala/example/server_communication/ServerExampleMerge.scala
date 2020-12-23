package example
package server_communication

object ServerExampleMerge extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel._
  import meta.compile.{compileSims, SimsMerge}

  val cls1: ClassWithObject[BackendServer] = BackendServer.reflect(IR)
  val cls2: ClassWithObject[FrontendServer] = FrontendServer.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), mainClass, SimsMerge(List(("BackendServer", "FrontendServer"))))
}

/*
Evaluation with 3 runs:
100 runs:   23562386947 |  24190733500 |  23102046062 ... best: 23102046062 (better as without merge: 2875670015)
1000 runs: 227743748649 | 225266706671 | 225434987462 ... best 225266706671 (better as without merge: 8792827844)
Conclusion: Init array takes a lot of time in beginning for 100000 messages, thus the time benefit for the next 900 runs
is not that big anymore. But merging still shows benefits, the time difference has been increased with more runs
 */