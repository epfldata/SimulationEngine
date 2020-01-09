package meta.example.supermarket

import meta.example.supermarket.people
import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.mutable.{ListBuffer, Map}

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
      |/* Auto generated from genMealPlans */
      |""".stripMargin

  val mealsPerDay: Vector[Int] = Vector(1, 2, 3)
  val mealType: Vector[String] = Vector("Vegetarian", "Carnivore") // names of objects defined
  val mealPlanMap: Map[(String, Int), Vector[String]] = Map()

  var cnt: Int = 0;

  def genTrait(mealType: String, mealsPerDay: Int, mealPlan: String): (String, String) = {
    val mealAcronym: Char = mealType(0)
    (s"""
      |trait MealPlan${mealAcronym}${mealsPerDay}_${cnt} {
      |  val preference: String = "${mealType}"
      |  val mealCnt: Int = ${mealsPerDay}
      |  val mealPlan: Vector[(articleName, gram)] = ${mealPlan}
      |  val randShoppingList: categoryAmount = ${mealType}.getRandShoppingList()
      |  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
      |}
      |""".stripMargin,
      s"MealPlan${mealAcronym}${mealsPerDay}_${cnt}")
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

  val mealPlanNames: ListBuffer[String] = new ListBuffer()
  var mealPlanTraits: String = ""

  mealsPerDay.foreach( meals_per_day => {
    mealType.foreach( meal_type => {
      mealPlanPerm(meal_type, meals_per_day).foreach( meal_plan => {
        cnt = cnt + 1
        val bar: (String, String) = genTrait(meal_type, meals_per_day, meal_plan)
        mealPlanTraits += bar._1 + "\n"
        mealPlanNames.append(s""""${bar._2}"""")
      })
      mealPlanMap+=((s""""$meal_type"""", meals_per_day) -> mealPlanNames.toVector)
      mealPlanNames.clear()
    })
  })

  bw.write(headerStr + mealPlanTraits)

  bw.write(
    s"""object MealPlanSummary {
      |  val total: Int = ${cnt}
      |
      |  val mealPlanMap: Map[(String, Int), Vector[String]] = Map(
      |    ${mealPlanMap.mkString(",\n    ")}
      |  )
      |}
      |""".stripMargin
  )
  bw.close()
}
