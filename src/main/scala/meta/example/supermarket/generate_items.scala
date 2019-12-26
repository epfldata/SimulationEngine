package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}

import meta.example.supermarket.goods.categories


// Don't use Product, which is a default Scala's type used later

// TODO: track all the values that have been declared and check for possible name clashing
object generateItems extends App{

  var agentCounter: Int = 1
  val category = new categories
  var cwd = new File(".").getCanonicalPath()
  val storagePath = cwd + "/src/main/scala/meta/example/supermarket/"

  def main(): Unit ={
    val fdir = new File(storagePath + s"/items/")
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    val headerStr: String =
      s"""package meta.example.supermarket.goods
         |
         |import meta.classLifting.SpecialInstructions
         |import squid.quasi.lift
         |
         |@lift
         |""".stripMargin

    val bodyStr: String =
      """
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

    category.getCategoryNames.foreach(
      categoryName =>{
        category.getArticleNames(categoryName).foreach{
          item => {
            val file = new File(storagePath + s"/items/Item${agentCounter}.scala")
            val bw = new BufferedWriter(new FileWriter(file))
            bw.write(headerStr + toValueStr(item) + bodyStr)
            bw.close()
          }
        }
      }
    )
  }

  private def toValueStr(name: String): String = {
    val result = s"""class Item${agentCounter} extends Item with ${name} {"""
    agentCounter += 1
    result
  }

  main()
}
