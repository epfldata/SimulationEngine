package example
package epidemic

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import org.apache.commons.math3.exception.NotFiniteNumberException

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

  var policy: NPI = NoNPI 
  var neighborPolicyFutures: List[Future[NPI]] = List()
  var neighborPolicies: List[NPI] = List()

  def getPolicy(): NPI = {
    policy
  }

  def clearRecord(): Unit = {
    totalInfected = 0
    totalHospitalized = 0
  }

  def report(status: HealthStatus): Unit = {
    if (status == Infectious){
      totalInfected = totalInfected + 1
    } 
    
    if (status == Hospitalized) {
      totalHospitalized = totalHospitalized + 1
    } 
    
    if (status == Deceased) {
      totalDeceased = totalDeceased + 1
    }
  }

  def main(): Unit = {
    while (true) {
      neighborPolicyFutures = otherCountries.map(x => asyncMessage[NPI](() => x.getPolicy()))

      if (neighborPolicyFutures.forall(p => p.isCompleted)) {
        neighborPolicies = neighborPolicyFutures.map(x => x.popValue.get)
      }

      if ((hospitalCapacity > 0.8*totalHospitalized) || neighborPolicies.forall(x => x == Lockdown)) {
        // println("Lock down!")
        policy = Lockdown
        citizens.map(p => asyncMessage[Unit](() => p.learnPolicy(policy)))
      } else if ((hospitalCapacity > 0.5*totalHospitalized) || neighborPolicies.forall(x => x==Quarantine)) {
        // println("Quarantine!")
        policy = Quarantine
        citizens.map(x => asyncMessage(() => x.learnPolicy(policy)))
      }
      clearRecord()
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}