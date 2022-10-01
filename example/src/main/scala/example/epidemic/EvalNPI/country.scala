package example
package epidemic.evalNPI

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import scala.collection.mutable.Map
import squid.lib.transparencyPropagating

// Countries would summarize their total infected patients everyday 
// and communicate it with other countries, so they can react accordingly
// Countries communicate through news and other information channels
@lift 
class Country(val hospitalCapacity: Int, val dayUnit: Int) extends Actor {
  var otherCountries: List[Country] = null
  var citizens: List[Person] = null

  val records: Map[String, Int] = Map[String, Int]()
  var totalHospitalized: Int = 0

  var policy: Int = 0 
  var neighborPolicyFutures: List[Future[Int]] = List()
  var neighborPolicies: List[Int] = List()

  def getPolicy(): Int = {
    policy
  }

  @transparencyPropagating
  def report(status: String): Boolean = {
    val current = records.get(status)
    if (!current.isDefined){
      records(status) = 1
    } else {
      records(status) = current.get + 1
    }
    true
  }

  def main(): Unit = {
    while (true) {
      // Obtaining neighbors' policies takes 10 rounds
      neighborPolicyFutures = otherCountries.map(x => asyncCall[Int](() => x.getPolicy(), 10))

      if (neighborPolicyFutures.forall(p => p.isCompleted)) {
        neighborPolicies = neighborPolicyFutures.map(x => x.popValue.get)
      }

      totalHospitalized = records.getOrElse("Hospitalized", 0)
      // Policy change takes 5 days to propagate
      if ((hospitalCapacity > 0.8*totalHospitalized) || neighborPolicies.forall(x => x == 2)) {
        // Lock down
        policy = 2
        citizens.foreach(p => callAndForget(p.learnPolicy(policy), 5))
      } else if ((hospitalCapacity > 0.5*totalHospitalized) || neighborPolicies.forall(x => x==1)) {
        // Quarantine
        policy = 1
        citizens.foreach(x => callAndForget(x.learnPolicy(policy), 5))
      }
      // println(records)
      records.clear()
      waitAndReply(dayUnit)
    }
  }
}