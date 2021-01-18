package example
package latency

object latencyExample extends App{

  val cls1: ClassWithObject[Server] = Server.reflect(IR)
  val cls2: ClassWithObject[Client] = Client.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), Some(mainClass))
}