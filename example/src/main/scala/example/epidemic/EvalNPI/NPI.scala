package example
package epidemic.evalNPI


import scala.util.Random

object NPI {
  def contactNumber(health: String, policy: Int): Int = {
    policy match {
      case 0 => List(Random.nextGaussian() + 10, 1).max.toInt // NoNPI
      case 1 =>   // Quarantine
        health match {
          case "Infectious" => List(Random.nextGaussian() + 3, 0).max.toInt
          case "Hospitalized" => 0
          case _ => List(Random.nextGaussian() + 10, 1).max.toInt
        }
      case 2 =>   // Lockdown
        health match {
          case "Infectious" => List(Random.nextGaussian() + 3, 0).max.toInt
          case "Hospitalized" => 0
          case _ => List(Random.nextGaussian() + 3, 0).max.toInt
        }
    }
  }
}