package meta.example.supermarket.goods

import scala.util.Random
import meta.example.supermarket.utils._

trait ItemTrait {
  val rnd = Random.nextGaussian()
  var totalWastedFood: Double = 0
  var wasteSummary: categoryAmount = categoryAmount(0, 0, 0, 0, 0)

  // TODO: Add error handling for potential unmatched category type
  def updateWasteSummary(categoryName: String, unitAmount: Int, atHome: Boolean): Unit ={
    categoryName match {
      case "Vegetable" => wasteSummary.Vegetable = wasteSummary.Vegetable + unitAmount - toInt(atHome)*unitAmount*rnd
      case "Meat" => wasteSummary.Meat = wasteSummary.Meat + unitAmount - toInt(atHome)*unitAmount*rnd
      case "Snack" => wasteSummary.Snack = wasteSummary.Snack + unitAmount - toInt(atHome)*unitAmount*rnd
      case "Grain" => wasteSummary.Grain = wasteSummary.Grain + unitAmount - toInt(atHome)*unitAmount*rnd
      case "Dairy" => wasteSummary.Dairy = wasteSummary.Dairy + unitAmount - toInt(atHome)*unitAmount*rnd
      case _ => throw new Exception
    }
  }

  def updateState(newState: String, itemState: ItemState): Unit ={
    newState match {
      case "isPurchased" => itemState.purchase
      case "isDiscarded" => itemState.discard
      case "isConsumed" => itemState.consume
      case _ => throw new Exception
    }
  }
}
