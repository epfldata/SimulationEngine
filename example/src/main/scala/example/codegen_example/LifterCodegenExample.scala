package meta.example.codegen_example

object LifterCodegenExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel._
  import meta.example.compileSims

  val cls1: ClassWithObject[Market] = Market.reflect(IR)
  val cls2: ClassWithObject[Farmer] = Farmer.reflect(IR)
  val cls3: ClassWithObject[InitClass] = InitClass.reflect(IR)

  compileSims(List(cls1, cls2), cls3)
}