package meta.example.helloWorld

import ch.qos.logback.classic.Logger
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import org.slf4j.LoggerFactory
import squid.quasi.lift

import scala.collection.mutable
import scala.util.Random

object Person {
  def greetMessage = (friend: String, me: String) =>
    "Hello, " + friend + "! My name is " + me

  def selfIntroduction = (me: String) =>
    "My name is " + me
}

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
    logger.info(Person.greetMessage(nm, name))
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

  def isTimid(): Unit = {
      logger.info(Person.selfIntroduction(name))
  }

  def main(): Unit = {
    while(true){
      waitTurns(3)
      handleMessages()
    }
  }
}

@lift
class OutgoingPerson(var name: String) extends Person{
  var outgoing: Boolean = true
  val rand = new Random

  def main(): Unit = {
    while(true){
//      val sa: Person = friendList(rand.nextInt(friendList.length))
//      val sb: Person = friendList(rand.nextInt(friendList.length))
      // Change it to deterministic for reproducibility
      val sa: Person = friendList(0)
      val sb: Person = friendList(1)

      // blocking call
      if (sa.isInstanceOf[TimidPerson]){
        sa.asInstanceOf[TimidPerson].isTimid()
      }

      // methods defined in trait outside of the lifter classes return instantaneously
      if (sa != sb){
        introduce(sa, sb)
      }
      waitTurns(2)
      handleMessages()
    }
  }
}



