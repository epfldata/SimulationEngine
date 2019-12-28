package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}


object genItems extends App{

  var itemCounter: Int = 1
  var cwd = new File(".").getCanonicalPath()
  val storagePath = cwd + "/src/main/scala/meta/example/supermarket/"

  private def mapHeader: String = {
    s"""package meta.example.supermarket.goods
       |
       |import scala.collection.mutable.Map
       |
       |/* Auto generated */
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

  private def itemHeader: String = {
    s"""package meta.example.supermarket.goods
       |
       |import meta.classLifting.SpecialInstructions
       |import squid.quasi.lift
       |
       |/* Auto generated */
       |
       |@lift
       |""".stripMargin
  }

  private def itemBody: String = {
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
    val file = new File(storagePath + s"/items/Item${itemCounter}.scala")
    val bw = new BufferedWriter(new FileWriter(file))
    val className: String = s"""class ${item} extends Item with ${article} {"""
    bw.write(itemHeader + className + itemBody)
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

    categories.getArticleNames.foreach(
      article => {
        val itemName: String = s"Item${itemCounter}"
        newItem(itemName, article)
        mapBody += mapAdd(itemName, article)
        itemCounter += 1
      }
    )

    mapBW.write(mapHeader + mapBody.dropRight(2) + ")" +
      """
        |}
        |""".stripMargin)

    mapBW.close()
  }

  main()
}
