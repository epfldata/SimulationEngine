package meta.example.helloWorld

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable
import scala.util.Random

trait Person extends Actor {
  var name: String
  var outgoing: Boolean
  val friendList: mutable.ListBuffer[Person] = mutable.ListBuffer()

  // Normally, we would define methods in classes rather than traits
  // But we need a trait to mixin
  def notMet(someone: Person): Boolean = {
    !friendList.contains(someone)
  }

  def addFriend(sa: Person): Unit = {
    friendList.append(sa)
    sa.greet(name)
  }

  def greet(nm: String): Unit = {
    println("Hello! " + nm + " My name is " + name)
  }

  def introduce(sa: Person, sb: Person): Unit = {
    if (sa.notMet(sb)){
      sa.addFriend(sb)
      sb.addFriend(sa)
    }
  }
}

@lift
class TimidPerson(var name: String) extends Person{
  var outgoing: Boolean = false

  def main(): Unit = {
    while(true){
      waitTurns(1)
    }
  }
}

@lift
class OutgoingPerson(var name: String) extends Person{
  var outgoing: Boolean = true
  val rand = new Random

  def main(): Unit = {
    while(true){
      val sa: Person = friendList(rand.nextInt(friendList.length))
      val sb: Person = friendList(rand.nextInt(friendList.length))
      if (sa != sb){
        introduce(sa, sb)
      }
      waitTurns(1)
    }
  }
}



