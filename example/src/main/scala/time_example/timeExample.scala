package meta.example.time_example

object timeExample extends App {
  import meta.deep.IR
  import meta.example.vanillaCompile
  import IR.TopLevel.ClassWithObject

  val cls1: ClassWithObject[Sim] = Sim.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()
  vanillaCompile(List(cls1), mainClass, packageName)
}
