package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.mutable.{ListBuffer, Map}
import meta.example.supermarket.people.{frequencySummary,shopperTypeSummary, mealPlanSummary}

object genCustomers extends App {

  val generatingFileName: String = "genCustomers"
  var counter: Int = 1
  var cwd = new File(".").getCanonicalPath()
  val storagePath = cwd + "/src/main/scala/meta/example/supermarket/customers"

  // (frequency, mealplan, habbit)->CustId
  val custMap: Map[(String, String, String), String] = Map()

  private def custString(className: String): String =
    s"""package meta.example.supermarket.people
      |
      |import meta.classLifting.SpecialInstructions
      |import squid.quasi.lift
      |import scala.util.Random
      |
      |/* Auto generated from ${generatingFileName} */
      |
      |@lift
      |${className}
      |  def main(): Unit = {
      |    while(true) {
      |      SpecialInstructions.handleMessages()
      |      customerInfo
      |
      |      buyListedItems(shoppingList.targetItems, (Random.nextFloat < priceConscious))
      |      buyRandItems(shoppingList.randItems)
      |
      |      List.range(0, frequency).foreach(_ => {
      |        consumeFood(mealPlan)
      |        consumeFood
      |        customerInfo
      |        SpecialInstructions.waitTurns(1)
      |      })
      |    }
      |  }
      |}
      |""".stripMargin

  private def newCustomer(frequency: String, mealPlan: String, habbit: String): Unit ={
    val file = new File(storagePath + s"/Customer${counter}.scala")
    val bw = new BufferedWriter(new FileWriter(file))

    val className: String = s"""class Customer${counter} extends People with ${frequency} with ${mealPlan} with ${habbit} {"""
    custMap+=((s""""${frequency}"""", s""""${mealPlan}"""", s""""${habbit}"""")->s""""Customer${counter}"""")

    bw.write(custString(className))
    bw.close()
  }

  def main(): Unit = {
    val fdir = new File(storagePath)
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    frequencySummary.names.foreach(frequency => {
      shopperTypeSummary.names.foreach(habbit => {
        (1 to mealPlanSummary.total).foreach {mealPlanId =>
          newCustomer(frequency, "MealPlan"+mealPlanId, habbit)
          counter = counter + 1
        }
      })
    })

    val mapFile = new File(storagePath + s"/newCustomersMap.scala")
    val mapBW = new BufferedWriter(new FileWriter(mapFile))

    mapBW.write(
      s"""package meta.example.supermarket.goods
         |
         |import scala.collection.mutable.Map
         |
         |/* Auto generated from ${generatingFileName} */
         |
         |object newCustomersMap {
         |  val totalItems: Int = ${counter-1}
         |
         |  // goodsName, itemName
         |  val itemMap: Map[(String, String, String), String] = Map(
         |    ${custMap.mkString(",\n    ")}
         |  )
         |}
         |""".stripMargin
    )
    mapBW.close()
  }

  main()
}


