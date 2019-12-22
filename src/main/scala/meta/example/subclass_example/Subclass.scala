package meta.example.subclass_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Subclass1 extends Actor with ParentClass{
  var name = "subclass1"

  def accessParentClass: Unit = {
    hello
    println(name + " parent token is: " + parentToken)
    println(name + " grandparent token is: " + grandParentToken)
  }

  def main(): Unit = {
    while (true) {
      accessParentClass
      SpecialInstructions.waitTurns(1)
    }
  }
}

@lift
class Subclass2 extends Actor with GrandParentClass{
  var name = "subclass2"

  def accessGrandParentClass: Unit = {
    hello
    println(name + " grandparent token is: " + grandParentToken)
  }

  def main(): Unit = {
  while (true) {
      accessGrandParentClass
      SpecialInstructions.waitTurns(1)
    }
  }
}