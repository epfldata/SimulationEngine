package object example {
  type Actor = meta.deep.runtime.Actor
  val Actor = meta.deep.runtime.Actor
  // path-dependent type in lifter
  //  val SpecialInstruction = meta.classLifting.SpecialInstructions
  // https://stackoverflow.com/questions/20466161/adding-aliases-for-macro-annotations
  //  type lift = squid.quasi.lift
  val IR = meta.deep.IR

  type ClassWithObject[T] = meta.deep.IR.TopLevel.ClassWithObject[T]

  val compileSims = meta.compile.compileSims

  // Common imports from Scala libraries
  type ListBuffer[T] = scala.collection.mutable.ListBuffer[T]
  val Random = scala.util.Random
}
