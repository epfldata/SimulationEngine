package meta.example.supermarket

import meta.example.supermarket.people
import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.mutable.ListBuffer

object genMealPlans extends App {

  val mealPlanFileName: String = "MealPlanGenerated"
  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/people/"

  val file = new File(cwd + s"${mealPlanFileName}.scala")
  val bw = new BufferedWriter(new FileWriter(file))

  val headerStr: String =
    """package meta.example.supermarket.people
      |
      |import meta.example.supermarket.categories.{articleName, gram}
      |import meta.example.supermarket.{Carnivore, ShoppingList, Vegetarian, categoryAmount}
      |
      |""".stripMargin

  val mealsPerDay: Vector[Int] = Vector(1, 2, 3)
  val mealType: Vector[String] = Vector("Vegetarian", "Carnivore") // names of objects defined

  var cnt: Int = 0;

  def genTrait(mealType: String, mealsPerDay: Int, mealPlan: String): String = {
    val mealAcronym: Char = mealType(0)
    s"""
      |trait MealPlan${mealAcronym}${mealsPerDay}_${cnt} {
      |  val mealPlan: Vector[(articleName, gram)] = ${mealPlan}
      |  val randShoppingList: categoryAmount = ${mealType}.getRandShoppingList()
      |  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
      |}
      |""".stripMargin
  }

  def mealPlanPerm(mealType: String, mealsPerDay: Int): Vector[String] = {
    var result: ListBuffer[String] = new ListBuffer[String]()
    mealType match {
      case "Vegetarian" =>
        (0 to (Vegetarian.mealPlan.size-1)).toVector.combinations(mealsPerDay).foreach(comb =>
          result += s"$comb.flatMap(num => Vegetarian.mealPlan(num))"
        )
      case "Carnivore" =>
        (0 to Carnivore.mealPlan.size-1).toVector.combinations(mealsPerDay).foreach(comb =>
          result += s"$comb.flatMap(num => Carnivore.mealPlan(num))"
        )
      case _ => throw new IllegalArgumentException
    }
    result.toVector
  }

  bw.write(headerStr +
    mealsPerDay.flatMap( meals_per_day => {
      mealType.flatMap( meal_type => {
        mealPlanPerm(meal_type, meals_per_day).map( meal_plan => {
          cnt = cnt + 1
          genTrait(meal_type, meals_per_day, meal_plan)
        })
      })
    }).mkString("\n")
  )
  bw.close()
}
