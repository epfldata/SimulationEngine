package meta.example.supermarket.testItemOnlyExample

import meta.classLifting.Lifter
import meta.deep.codegen._
import meta.deep.runtime.Actor
import meta.deep.IR
import meta.deep.IR.TopLevel._

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
import meta.example.supermarket.people._

object testItemOnlyExample extends App {
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
  val cls1: ClassWithObject[Item1] = Item1.reflect(IR)
  val cls2: ClassWithObject[Item2] = Item2.reflect(IR)
  val cls3: ClassWithObject[Item3] = Item3.reflect(IR)
  val cls4: ClassWithObject[Item4] = Item4.reflect(IR)
  val cls5: ClassWithObject[Item5] = Item5.reflect(IR)
  val cls6: ClassWithObject[Item6] = Item6.reflect(IR)
  val cls7: ClassWithObject[Item7] = Item7.reflect(IR)
  val cls8: ClassWithObject[Item8] = Item8.reflect(IR)
  val cls9: ClassWithObject[Item9] = Item9.reflect(IR)
  val cls10: ClassWithObject[Item10] = Item10.reflect(IR)
  val cls11: ClassWithObject[Item11] = Item11.reflect(IR)
  val cls12: ClassWithObject[Item12] = Item12.reflect(IR)
  val cls13: ClassWithObject[Item13] = Item13.reflect(IR)
  val cls14: ClassWithObject[Item14] = Item14.reflect(IR)
  val cls15: ClassWithObject[Item15] = Item15.reflect(IR)
  val cls16: ClassWithObject[Item16] = Item16.reflect(IR)
  val cls17: ClassWithObject[Item17] = Item17.reflect(IR)
  val cls18: ClassWithObject[Item18] = Item18.reflect(IR)
  val cls19: ClassWithObject[Item19] = Item19.reflect(IR)
  val cls20: ClassWithObject[Item20] = Item20.reflect(IR)
  val cls21: ClassWithObject[Item21] = Item21.reflect(IR)
  val cls22: ClassWithObject[Item22] = Item22.reflect(IR)
  val cls23: ClassWithObject[Item23] = Item23.reflect(IR)
  val cls24: ClassWithObject[Item24] = Item24.reflect(IR)
  val cls25: ClassWithObject[Item25] = Item25.reflect(IR)
  val cls26: ClassWithObject[Item26] = Item26.reflect(IR)
  val cls27: ClassWithObject[Item27] = Item27.reflect(IR)
  val cls28: ClassWithObject[Item28] = Item28.reflect(IR)
  val cls29: ClassWithObject[Item29] = Item29.reflect(IR)
  val cls30: ClassWithObject[Item30] = Item30.reflect(IR)
  val cls31: ClassWithObject[Item31] = Item31.reflect(IR)
  val startClasses: List[Clasz[_ <: Actor]] = List(cls1, cls2, cls3, cls4, cls5, cls6, cls7, cls8, cls9, cls10, cls11, cls12, cls13, cls14, cls15, cls16, cls17, cls18, cls19, cls20, cls21, cls22, cls23, cls24, cls25, cls26, cls27, cls28, cls29, cls30, cls31)

  val lifter = new Lifter()
  val simulationData = lifter(startClasses, mainClass)

  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
    new EdgeMerge(),
    new CreateCode(simulationData._2, "generated/main/scala"),
  ))

  pipeline.run()
}
