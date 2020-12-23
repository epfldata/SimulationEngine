package example
package time_example

object timeExample extends App {
  import meta.deep.IR
  import meta.compile.compileSims
  import IR.TopLevel.ClassWithObject

  val cls1: ClassWithObject[Sim] = Sim.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}
