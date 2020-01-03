package meta.example.supermarket

import meta.example.supermarket.utils._
import scala.util.Random

trait SummaryTrait {
  val rnd = Random.nextFloat()  // uniformly distributed
  var totalWastedFood: Double = 0
  var wasteSummary: categoryAmount = categoryAmount(0, 0, 0, 0, 0)

  def updateWasteSummary(categoryName: String, wastedAmount: Int): Unit ={
    categoryName match {
      case "Vegetable" => wasteSummary.Vegetable = wasteSummary.Vegetable + wastedAmount
      case "Meat" => wasteSummary.Meat = wasteSummary.Meat + wastedAmount
      case "Snack" => wasteSummary.Snack = wasteSummary.Snack + wastedAmount
      case "Grain" => wasteSummary.Grain = wasteSummary.Grain + wastedAmount
      case "Dairy" => wasteSummary.Dairy = wasteSummary.Dairy + wastedAmount
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
