package meta.example.nb_methods_example

object NBMethodsExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.vanillaCompile

  val cls1: ClassWithObject[Object1] = Object1.reflect(IR)
  val cls2: ClassWithObject[Object2] = Object2.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()

  vanillaCompile(List(cls2, cls1), mainClass, packageName)
}
