package meta.example.supermarket

import meta.example.supermarket.utils._
import scala.util.Random

trait SummaryTrait {
  val rnd = Random.nextFloat()  // uniformly distributed
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
    println(Console.RESET + Console.RED + "Wasted Amount " + wasteSummary.formatted(
      s"Vegetable: ${to2Dec(wasteSummary.Vegetable)} " +
      s"Meat: ${to2Dec(wasteSummary.Meat)} " +
      s"Snack: ${to2Dec(wasteSummary.Snack)} " +
      s"Grain: ${to2Dec(wasteSummary.Grain)} "+
      s"Dairy: ${to2Dec(wasteSummary.Dairy)}" +
      Console.RESET
    ))
  }
}
