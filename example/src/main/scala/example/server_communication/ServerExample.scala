package meta.example.server_communication

object ServerExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.compileSims

  val cls1: ClassWithObject[BackendServer] = BackendServer.reflect(IR)
  val cls2: ClassWithObject[FrontendServer] = FrontendServer.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()

  compileSims(List(cls1, cls2), mainClass, packageName)
}

/*
Evaluation with 3 runs:
100 runs:   27815563790 |  27625830142 |  25977716077 ... best:  25977716077
1000 runs: 234590483896 | 242222868950 | 234059534515 ... best: 234059534515
 */