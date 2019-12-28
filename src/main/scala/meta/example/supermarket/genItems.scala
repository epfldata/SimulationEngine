package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}

//import meta.example.supermarket.goods.newItemsMap
// Don't use Product, which is a default Scala's type used later

object generateItems extends App{

  var agentCounter: Int = 1
  var cwd = new File(".").getCanonicalPath()
  val storagePath = cwd + "/src/main/scala/meta/example/supermarket/"

  private def mapHeaderStr: String = {
    s"""package meta.example.supermarket.goods
       |
       |import scala.collection.mutable.Map
       |
       |/* Auto generated from file generateItems */
       |
       |object newItemsMap {
       |  type itemName = String
       |  type goodsName = String
       |
       |  val itemMap: Map[itemName, goodsName] = Map(
       |""".stripMargin
  }

  private def mapAdd(itemName: String, article: String): String = {
    s"""   "${itemName}" -> "${article}",
       |""".stripMargin
  }

  private def itemHeaderStr: String = {
    s"""package meta.example.supermarket.goods
       |
       |import meta.classLifting.SpecialInstructions
       |import squid.quasi.lift
       |
       |/* Auto generated from file generateItems */
       |
       |@lift
       |""".stripMargin
  }

  private def itemBodyStr: String = {
    s"""
       |  var age: Int = 0
       |
       |  def main(): Unit = {
       |    while(age < freshUntil && !state.isConsumed) {
       |        itemInfo
       |        SpecialInstructions.waitTurns(1)
       |        age = age + 1
       |    }
       |    cleanExpired
       |  }
       |}
       |""".stripMargin
  }

  private def newItem(item: String, article: String): Unit ={
    val file = new File(storagePath + s"/items/Item${agentCounter}.scala")
    val bw = new BufferedWriter(new FileWriter(file))
    val className: String = s"""class ${item} extends Item with ${article} {"""
    bw.write(itemHeaderStr + className + itemBodyStr)
    bw.close()
  }

  def main(): Unit ={
    val fdir = new File(storagePath + s"/items/")
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    val mapFile = new File(storagePath + s"/items/newItemsMap.scala")
    val mapBW = new BufferedWriter(new FileWriter(mapFile))

    var mapBody: String = ""

    categories.getCategoryNames.foreach(
      categoryName => {
        categories.getArticleNames(categoryName).foreach{
          article => {
            val itemName: String = s"Item${agentCounter}"
            newItem(itemName, article)
            mapBody += mapAdd(itemName, article)
            agentCounter += 1
          }
        }
      }
    )

    mapBW.write(mapHeaderStr + mapBody.dropRight(2) + ")" +
      """
        |}
        |""".stripMargin)

    mapBW.close()
  }

  main()
}
