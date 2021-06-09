package example
package segregation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Person(val world: WorldMap, val view: Int, val toleranceThreshold: Double) extends Actor {

  var location: Option[Int] = None
  var similarity: Double = 1
  val neighborRadius: Int = 1

  // happy: same views 50%+
  // notify the world about its similarity
  def unhappy(): Boolean = {
    var sameView: Int = 0
    val neighbors: List[Person] = world.getNeighbors(location.get, neighborRadius)

    neighbors.foreach(n => {
      if (n.view == view){
        sameView = sameView + 1
      }})
    // If only one person, similarity = 1, happy
    if (neighbors.length == 0){
      similarity = 1
      asyncMessage(() => world.report(location, similarity))
      false
    } else {
      similarity = sameView.toDouble/neighbors.length
      asyncMessage(() => world.report(location, similarity))
      sameView < toleranceThreshold * neighbors.length
    }
  }

  def main(): Unit ={
    while(true){
//      println("Person " + id)
      if (!location.isDefined){
        location = world.placeAgent(this)
      } else {
        if (unhappy()){
          location = Some(world.move(location.get, this))
        }
        waitLabel(Turn, 1)
      }
//      println("Person " + id + " finished! Next turn!")
      handleMessages()
    }
  }
}