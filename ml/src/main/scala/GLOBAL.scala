import Simulation.SimO

import scala.util.Random
package GLOBAL {

class Dummy;

}

package object GLOBAL {
  val silent = true
  val strongSilence = true
  val rnd = new Random()

  val allAgents = Array("Person", "Farm", "Mill", "Bakery", "CattleFarm", "OilField", "Refinery", "Landlord")

  def isAgent(sim: SimO): Boolean = allAgents.contains(getAgentTypeFromClass(sim.getClass.getName))

  def getAgentTypeFromClass(className: String): String = {
    className match {
      case "Simulation.Person" => "Person"
      case name if name.startsWith("Simulation.SimLib.") => name.substring("Simulation.SimLib.".length)
    }
  }

  def mapopt[A,B](l: List[A], f: A => Option[B]) : List[B] =
    l.flatMap((a: A) => f(a) match {
      case Some(b) => List(b)
      case None    => List()
    })

  def println(x: Any): Unit = {
    if(!strongSilence)
      Console.out.println(x)
  }

  def println(): Unit = {
    if (!strongSilence)
      Console.out.println()
  }

  def print(x: Any): Unit = {
    if(!strongSilence)
      Console.out.print(x)
  }

  def print(): Unit = {
    if (!strongSilence)
      Console.out.print()
  }
}

