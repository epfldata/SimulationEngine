package example
package epidemic.hierarchical

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import squid.lib.transparencyPropagating

@lift
class Country(val cities: List[City], var priority: Int, var delayInResponse: Int) extends Actor {
    var infectious_ratios: List[Double] = List()
    var fs: List[Future[Double]] = List()
    var concern_factor: Int = 0
    var updated_concern: Int = 0

    def main(): Unit = {
        // priority 1 has the highest concern factor
        concern_factor = 100 / priority
        println("Country "+ id + " has priority " + priority)
        while (true) {
            fs = cities.map(i => asyncMessage(() => i.getLatestInfectiousRatio()))
            while (fs.exists(p => !p.isCompleted)){
              waitLabel(Turn, 1)
            }
            infectious_ratios = fs.map(i => i.popValue.get)
            
            // dynamic zero-case
            if (priority == 1) {
              if (infectious_ratios.exists(p => p > 0)){
                infectious_ratios.zipWithIndex.foreach(i => if (i._1 > 0) {
                  updated_concern = List(3, (i._1 * concern_factor).toInt).max
                  onesideSend(cities(i._2).nationalConcern(updated_concern))
                })
              }
            } else if (priority == 2) {  // some what care
              if (infectious_ratios.exists(p => p > 0.1)){
                infectious_ratios.zipWithIndex.foreach(i => if (i._1 > 0.1) {
                  updated_concern = List(3, (i._1 * concern_factor).toInt).max
                  onesideSend(cities(i._2).nationalConcern(updated_concern))
                })
              }
            } else { // only if necessary
              if (infectious_ratios.exists(p => p > 0.3)){
                infectious_ratios.zipWithIndex.foreach(i => if (i._1 > 0.3) {
                  updated_concern = List(3, (i._1 * concern_factor).toInt).max
                  onesideSend(cities(i._2).nationalConcern(updated_concern))
                })
              }
            }
            waitAndReply(delayInResponse)
        }
    }
}