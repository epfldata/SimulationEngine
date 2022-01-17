package example
package segregation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import lib.Grid.AgentWithNeighbors

@lift
class Person(val world: WorldMap,
            val view: Int, 
            val toleranceThreshold: Double) extends AgentWithNeighbors {

  var location: Int = 0

  var similarity: Double = 1
  val neighborRadius: Int = 1

  var neighborViews: List[Future[Int]] = null

  // happy: same views 50%+
  // notify the world about its similarity

  def getView(): Int = view

  def main(): Unit ={
    asyncMessage(() => world.placeAgent(location, id))

    while(true){
        var sameView: Int = 0

        val neighborIds = world.getNeighbors(location)

        val neighbors = neighborIds.map(x => connectedAgents(x).asInstanceOf[Person])

        neighborViews = neighbors.map(x => asyncMessage(() => x.getView()))

        while (!neighborViews.forall(_.isCompleted)){
          waitLabel(Turn, 1)
        }

        neighborViews.map(x => x.popValue.get.asInstanceOf[Int]).foreach(n => {
          if (n == view){
            sameView = sameView + 1
          }
        })

        val unhappy = if (neighbors.length == 0){
            similarity = 1
            asyncMessage(() => world.report(location, similarity))
            false
        } else {
            similarity = sameView.toDouble/neighbors.length
            asyncMessage(() => world.report(location, similarity))
            sameView < toleranceThreshold * neighbors.length
        }

        if (unhappy){
          location = world.move(location)
        }
        waitLabel(Turn, 1)
      }
    }
}