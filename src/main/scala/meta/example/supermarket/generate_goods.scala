package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}
import meta.example.supermarket.categories._

// TODO: track all the values that have been declared and check for possible name clashing
class generateGoods(storagePath: String) {
  private var parentName: String = ""

  // Simple function that only returns int, boolean, double, string
  private def bindType(attrVal: Any): String = {
    attrVal match {
      case _: Int => s"Int = ${attrVal}"
      case _: Double => s"Double = ${attrVal}"
      case _: Boolean => s"Boolean = ${attrVal}"
      case _ => s"""String = "${attrVal}""""
    }
  }

  def apply(baseClass: Category): Unit ={
    parentName = baseClass.name.capitalize

    val fdir = new File(storagePath + s"/goods/")
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    val file = new File(storagePath + s"/goods/${parentName}.scala")
    val bw = new BufferedWriter(new FileWriter(file))

    val valueStr: String = toValueStr(baseClass.fields)

    val parentStr: String =
      s"""package meta.example.supermarket.goods
         |
         |trait ${parentName} {
         |  val category: String = "${parentName}"
         |${valueStr}
         |}
         |""".stripMargin

    bw.write(parentStr)
    baseClass.children.foreach(child => bw.write(toChildStr(child)))
    bw.close()
  }

  private def toValueStr(attrs: List[Attr]): String = {
    attrs.map(attr => s"  val ${attr.name}: ${bindType(attr.attrVal)}")
      .mkString("\n")
      .stripLineEnd
  }

  private def toChildStr(product: Article): String = {
    s"""
       |trait ${product.name.capitalize} extends ${parentName} {
       |  val name: String = "${product.name.capitalize}"
       |${toValueStr(product.fields)}
       |}
       |""".stripMargin
  }
}

object generateGoods extends App{

  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"

  // Take a case class definition and convert it to List[Attr]
  def toAttrss(cc: Product): List[Attr] = {
    utils.ccArgToList(cc).map( attr => Attr(attr._1, attr._2) )
  }

  // TODO: may want to extend it to handle other fields besides price
  def toArticless(namePrice: namePriceUnit): List[Article]= {
    namePrice.map(
      pair => Article(
        name = pair._1,
        fields = toAttrss(ArticleFields(pair._2, pair._3))
      ))
  }

  def genFile(name: String, fields: CategoryFields, namePricess: namePriceUnit): Unit ={
    new generateGoods(cwd)(Category(name, toAttrss(fields), toArticless(namePricess)))
  }

  val category = new categories

  category.getSummary.foreach(
    tup => genFile(tup._1, tup._2, tup._3)
  )
}

