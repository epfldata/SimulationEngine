package object example {
  type Actor = meta.runtime.Actor
  val Actor = meta.runtime.Actor
  val SimRuntime = meta.runtime.SimRuntime

  type Future[T] = meta.runtime.Future[T]
  val Future = meta.runtime.Future

  // path-dependent type in lifter
  //  val SpecialInstruction = meta.classLifting.SpecialInstructions

  // https://stackoverflow.com/questions/20466161/adding-aliases-for-macro-annotations
  //  type lift = squid.quasi.lift
  val IR = meta.deep.IR

  type ClassWithObject[T] = meta.deep.IR.TopLevel.ClassWithObject[T]

  val compileSims = meta.API.compileSims

  // Common imports from Scala libraries
  type ListBuffer[T] = scala.collection.mutable.ListBuffer[T]
  val Random = scala.util.Random
}