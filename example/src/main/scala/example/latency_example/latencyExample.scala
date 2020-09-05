package meta.example.latency

object latencyExample extends App{
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.compileSims

  val cls1: ClassWithObject[Server] = Server.reflect(IR)
  val cls2: ClassWithObject[Client] = Client.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()

  compileSims(List(cls1, cls2), mainClass, packageName)
}