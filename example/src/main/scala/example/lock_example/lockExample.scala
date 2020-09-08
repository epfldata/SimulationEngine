package meta.example.lock_example

object lockExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.compileSims

  val cls1: ClassWithObject[Consensus] = Consensus.reflect(IR)
  val cls2: ClassWithObject[Voter] = Voter.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)



  compileSims(List(cls1, cls2), mainClass)
}
