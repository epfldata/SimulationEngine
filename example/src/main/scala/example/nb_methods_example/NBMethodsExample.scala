package example
package nb_methods_example

object NBMethodsExample extends App {

  val cls1: ClassWithObject[Object1] = Object1.reflect(IR)
  val cls2: ClassWithObject[Object2] = Object2.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)



  compileSims(List(cls2, cls1), mainClass)
}
