package meta.example.helloWorld

object helloWorldExample extends App{
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.vanillaCompile

  val cls1: ClassWithObject[TimidPerson] = TimidPerson.reflect(IR)
  val cls2: ClassWithObject[OutgoingPerson] = OutgoingPerson.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()

  vanillaCompile(List(cls1, cls2), mainClass, packageName)
}