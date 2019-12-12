package meta.example.subclassExample

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

//@lift
// Macro expansion failed, no ctor for class
trait ParentClass {
  val classType: Int = 5
}


@lift
class Subclass1 extends Actor with ParentClass{
  var name = "Children"

  def accessChildClass(): Unit = {
    println(name + classType)
  }

  def main(): Unit = {
    while(true) {
      accessChildClass()
      SpecialInstructions.waitTurns(1)
    }
  }
}

@lift
class Subclass2 extends Actor with ParentClass{
  var name = "Pets"

  def accessChildClass(): Unit = {
    println(name + classType)
  }

  def main(): Unit = {
    while(true) {
      accessChildClass()
      SpecialInstructions.waitTurns(1)
    }
  }
}