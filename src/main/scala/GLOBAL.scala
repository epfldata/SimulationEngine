import scala.util.Random
package GLOBAL {

class Dummy;

}

package object GLOBAL {
  var silent = false
  var strongSilence = false
  val rnd = new Random(19)

  def agentClass(agentType: String): String = {
    agentType match {
      case "Person" => "Simulation.Person"
      case "Farm" => "Simulation.SimLib.Farm"
      case "Mill" => "Simulation.SimLib.Mill"
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

