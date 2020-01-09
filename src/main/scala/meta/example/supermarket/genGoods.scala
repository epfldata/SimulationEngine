package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}
import meta.example.supermarket.categories._

object genGoods extends App {

  val generatingFileName: String = "genGoods"
  private var parentName: String = ""
  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"

  // Simple function that only returns int, boolean, double, string
  private def bindType(attrVal: Any): String = {
    attrVal match {
      case _: Int => s"Int = ${attrVal}"
      case _: Double => s"Double = ${attrVal}"
      case _: Boolean => s"Boolean = ${attrVal}"
      case _ => s"""String = "${attrVal}""""
    }
  }

  private def toFile(category: Category): Unit ={
    parentName = category.name.capitalize

    val fdir = new File(cwd + s"/goods/")
    if (!fdir.exists()){
      fdir.mkdirs()
    }

    val file = new File(cwd + s"/goods/${parentName}.scala")
    val bw = new BufferedWriter(new FileWriter(file))

    val valueStr: String = toValueStr(category.fields)

    val parentStr: String =
      s"""package meta.example.supermarket.goods
         |
         |/* Auto generated from ${generatingFileName}
         | Please adjust file categories for modification
         | */
         |
         |trait ${parentName} {
         |  val category: String = "${parentName}"
         |${valueStr}
         |}
         |""".stripMargin

    bw.write(parentStr)
    category.children.foreach(child => bw.write(toChildStr(child)))
    bw.close()
  }

  private def toValueStr(attrs: Vector[Attr]): String = {
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

  // Take a case class definition and convert it to List[Attr]. Product is a default Scala type
  private def toAttrss(cc: Product): Vector[Attr] = {
    utils.ccArgToVector(cc).map( attr => Attr(attr._1, attr._2) )
  }

  private def toArticless(namePrice: namePriceUnit): Vector[Article]= {
    namePrice.map(
      pair => Article(
        name = pair._1,
        fields = toAttrss(ArticleFields(pair._2, pair._3, pair._4, pair._5))
      ))
  }

  def main(): Unit = {
    categories.getSummary.foreach(
      tup => toFile(Category(tup._1, toAttrss(tup._2), toArticless(tup._3)))
    )
  }

  main()
}

