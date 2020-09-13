package meta.example.stateless_server

object StatelessServerExample extends App {
  import meta.deep.IR
  import IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[RandomPrinter] = RandomPrinter.reflect(IR)
  val cls2: ClassWithObject[RandomNumberServer] = RandomNumberServer.reflect(IR)
  val cls3: ClassWithObject[InitActors] = InitActors.reflect(IR)

  compileSims(List(cls1, cls2), cls3)
}

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  3279097288, 3218413896, 3196279381. best: 3196279381
//100 iterations, 100000 actors: 28772399683, 28164466706, 28342239824. best: 28164466706
