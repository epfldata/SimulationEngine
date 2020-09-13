package meta.example.latency

object latencyExample extends App{
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[Server] = Server.reflect(IR)
  val cls2: ClassWithObject[Client] = Client.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), mainClass)
}