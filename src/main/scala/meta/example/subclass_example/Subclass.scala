package meta.example.subclass_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Subclass1 extends Actor with ParentClass{
  var name = "subclass1 "

  def accessParentClass(): Unit = {
    println(name + parentType)
  }

  def main(): Unit = {
    var remainIts: Int = parentType
    while(remainIts > 0) {
      accessParentClass()
      SpecialInstructions.waitTurns(1)
      remainIts = remainIts - 1
    }
  }
}

@lift
class Subclass2 extends Actor with ParentClass{
  var name = "Subclass2 "

  def accessGrandParentClass(remainDays: Int): Unit = {
    println(name + remainDays)
  }

  def main(): Unit = {
    var remainIts: Int = grandParentType
    while(remainIts > 0) {
      accessGrandParentClass(remainIts)
      SpecialInstructions.waitTurns(1)
      remainIts = remainIts - 1
    }
  }
}