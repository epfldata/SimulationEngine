package example.epidemic

import scala.util.Random

// non-pharmaceutical intervention
trait NPI {
  def contactNumber(health: HealthStatus): Int 
} 

case object NoNPI extends NPI {
  def contactNumber(health: HealthStatus): Int = {
    health match {
      case _ => List(Random.nextGaussian() + 10, 1).max.toInt
    }
  }
}

case object Quarantine extends NPI {
  def contactNumber(health: HealthStatus): Int = {
    health match {
      case Infectious => List(Random.nextGaussian() + 3, 0).max.toInt
      case Hospitalized => 0
      case _ => List(Random.nextGaussian() + 10, 1).max.toInt
    }
  }
}

case object Lockdown extends NPI {
  def contactNumber(health: HealthStatus): Int = {
    health match {
      case Infectious => List(Random.nextGaussian() + 3, 0).max.toInt
      case Hospitalized => 0
      case _ => List(Random.nextGaussian() + 3, 0).max.toInt
    }
  }  
}