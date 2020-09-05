package meta.example.monitor_example.agent_monitor

object monitorExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.example.compileSims

  val cls1: ClassWithObject[object1] = object1.reflect(IR)
  val cls2: ClassWithObject[monitorSim] = monitorSim.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  val packageName: String = this.getClass.getPackage.getName()

  compileSims(List(cls1, cls2), mainClass, packageName)
}