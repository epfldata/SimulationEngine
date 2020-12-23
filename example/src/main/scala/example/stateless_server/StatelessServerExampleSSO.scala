// package example
package stateless_server


// To fix, the compiled code doesn't work right now

//object StatelessServerExampleSSO extends App {
//  import example.{compileSims, SimsStateless}
//  import meta.deep.IR
//  import IR.TopLevel.ClassWithObject
//
//  val cls1: ClassWithObject[RandomPrinter] = RandomPrinter.reflect(IR)
//  val cls2: ClassWithObject[RandomNumberServer] = RandomNumberServer.reflect(IR)
//  val cls3: ClassWithObject[InitActors] = InitActors.reflect(IR)
//
//  compileSims(List(cls1, cls2), cls3, this.getClass.getPackage.getName + "_sso", SimsStateless(List("RandomNumberServer")))
//}

//Evaluation with 3 runs:
//1000 iterations, 1000 actors:  1834609610, 1784664607, 1877337379. best: 1784664607
//100 iterations, 100000 actors: 15300657512, 15051753104, 15022346471. best: 15022346471
