package example
package server_communication

object ServerExample extends App {

  val cls1: ClassWithObject[BackendServer] = BackendServer.reflect(IR)
  val cls2: ClassWithObject[FrontendServer] = FrontendServer.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), mainClass)
}

/*
Evaluation with 3 runs:
100 runs:   27815563790 |  27625830142 |  25977716077 ... best:  25977716077
1000 runs: 234590483896 | 242222868950 | 234059534515 ... best: 234059534515
 */