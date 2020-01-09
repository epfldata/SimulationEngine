package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.mutable.{Map}

object genItems extends App{

  val generatingFileName: String = "genItems"
  var counter: Int = 1
  var cwd = new File(".").getCanonicalPath()
  val storagePath = cwd + "/src/main/scala/meta/example/supermarket/"

  val itemMap: Map[String, String] = Map()

  private def itemString(className: String): String =
    s"""package meta.example.supermarket.goods
       |
       |import meta.classLifting.SpecialInstructions
       |import squid.quasi.lift
       |
       |/* Auto generated from ${generatingFileName} */
       |
       |@lift
       |$className
       |  var age: Int = 0
       |
       |  def main(): Unit = {
       |    while(age < freshUntil && !state.isConsumed) {
       |        itemInfo
       |        SpecialInstructions.waitTurns(1)
       |        age = age + 1
       |    }
       |    cleanExpired()
       |  }
       |}
       |""".stripMargin


  private def newItem(article: String): Unit ={
    val file = new File(storagePath + s"/items/Item${counter}.scala")
    val bw = new BufferedWriter(new FileWriter(file))

    val className: String = s"""class Item${counter} extends Item with ${article} {"""
    itemMap += (s""""${article}""""->s""""Item${counter}"""")

    bw.write(itemString(className))
    bw.close()
  }

  def main(): Unit ={
    val fdir = new File(storagePath + s"/items/")
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    val mapFile = new File(storagePath + s"/items/newItemsMap.scala")
    val mapBW = new BufferedWriter(new FileWriter(mapFile))

    categories.getArticleNames.foreach(
      article => {
        newItem(article)
        counter += 1
      }
    )

    mapBW.write(
      s"""package meta.example.supermarket.goods
         |
         |import scala.collection.mutable.Map
         |
         |/* Auto generated from ${generatingFileName} */
         |
         |trait newItem {
         | var timeVar: Int
         |}
         |
         |object newItemsMap {
         |  val totalItems: Int = ${counter-1}
         |
         |  // goodsName, itemName
         |  val itemMap: Map[String, String] = Map(
         |    ${itemMap.mkString(",\n    ")}
         |  )
         |}
         |""".stripMargin
    )
    mapBW.close()
  }

  main()
}
