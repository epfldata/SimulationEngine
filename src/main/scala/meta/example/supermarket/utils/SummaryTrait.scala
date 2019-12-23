package meta.example.supermarket

import meta.example.supermarket.goods.categoryAmount
import meta.example.supermarket.utils.toInt

import scala.util.Random

trait SummaryTrait {
  val rnd = Random.nextGaussian()
  var totalWastedFood: Double = 0
  var wasteSummary: categoryAmount = categoryAmount(0, 0, 0, 0, 0)

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
}
