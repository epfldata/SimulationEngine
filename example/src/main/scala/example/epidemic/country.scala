package example
package epidemic

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

// Countries would summarize their total infected patients everyday 
// and communicate it with other countries, so they can react accordingly
// Countries communicate through news and other information channels
@lift 
class Country(val hospitalCapacity: Int) extends Actor {
  var otherCountries: List[Country] = null
  var citizens: List[Person] = null

  var totalInfected: Int = 0
  var totalHospitalized: Int = 0
  var totalDeceased: Int = 0

  var policy: Int = 0 
  var neighborPolicyFutures: List[Future[Int]] = List()
  var neighborPolicies: List[Int] = List()

  def getPolicy(): Int = {
    policy
  }

  def report(status: String): Int = {
    if (status == "Infectious"){
      totalInfected = totalInfected + 1
    } 
    
    if (status == "Hospitalized") {
      totalHospitalized = totalHospitalized + 1
    } 
    
    if (status == "Deceased") {
      totalDeceased = totalDeceased + 1
    }
    totalInfected
  }

  def main(): Unit = {
    while (true) {
      neighborPolicyFutures = otherCountries.map(x => asyncMessage[Int](() => x.getPolicy()))

      if (neighborPolicyFutures.forall(p => p.isCompleted)) {
        neighborPolicies = neighborPolicyFutures.map(x => x.popValue.get)
      }

      if ((hospitalCapacity > 0.8*totalHospitalized) || neighborPolicies.forall(x => x == 2)) {
        // Lock down
        policy = 2
        citizens.map(p => asyncMessage(() => p.learnPolicy(policy)))
      } else if ((hospitalCapacity > 0.5*totalHospitalized) || neighborPolicies.forall(x => x==1)) {
        // Quarantine
        policy = 1
        citizens.map(x => asyncMessage(() => x.learnPolicy(policy)))
      }
      totalInfected = 0
      totalHospitalized = 0
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}