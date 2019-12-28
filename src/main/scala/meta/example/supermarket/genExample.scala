package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}
import meta.example.supermarket.goods.newItemsMap

object genExample extends App {

  val exampleDir: String = "testItemsOnly"
  val exampleName: String = exampleDir+"Example"
  val packageName: String = s"meta.example.supermarket.${exampleName}"
  val initName: String = "MainInit"

  val totalItems: Int = newItemsMap.itemMap.size
  val itemIds: List[Int] = List(1, 7, 11, 18, 23)

  assert(totalItems>0)
  assert(itemIds==itemIds.sorted)
  assert(itemIds(0)>0 && itemIds.last<=totalItems)

  val storagePathGegenerated: String = "generated/main/scala"
  //  val storagePathGegenerated: String = "src/main/scala/meta/example/supermarket/testItemsOnly"

  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"
  val fdir = new File(cwd + s"${exampleDir}")

  if (!fdir.exists()){
    fdir.mkdirs()
  }

  println("Please enter the number of items for each article. 0 if non-uniform")
  val instances = scala.io.StdIn.readInt()

  var fileInit: String = ""
  instances match {
    case 0 => fileInit = mixedLoop
    case _ => fileInit = singleLoop
  }

  def main(): Unit = {
    val file = new File(cwd + s"${exampleDir}/${exampleName}.scala")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(fileExample)
    bw.close()

    val file2 = new File(cwd + s"${exampleDir}/${initName}.scala")
    val bw2 = new BufferedWriter(new FileWriter(file2))
    bw2.write(fileInit)
    bw2.close()
  }

  private def initHeader: String =
    s"""package ${packageName}
       |
       |import meta.deep.runtime.Actor
       |import squid.quasi.lift
       |import scala.collection.mutable.ListBuffer
       |
       |import meta.example.supermarket.Supermarket
       |import meta.example.supermarket.goods._
       |import meta.example.supermarket.people._
       |
       |/* Auto generated from file genExample */
       |
       |@lift
       |class MainInit {
       |  def main(): List[Actor] = {
       |    val l = ListBuffer[Actor]()
       |    var ctr: Int = 0
       |""".stripMargin

  private def initLoopOpening(instances: Int): String =
    s"""
       |    ctr = 0
       |    while (ctr < ${instances}) {
       |""".stripMargin

  private def initToVal(itemId: Int): String = {
    val itemName = "Item" + itemId
    val valName = itemName.toLowerCase
    s"""      val ${valName} = new ${itemName}
       |      Supermarket.store.add(${valName}.asInstanceOf[Item])
       |      l.append(${valName})
       |""".stripMargin
  }

  private def initLoopClosing: String =
    """      ctr = ctr + 1
      |    }""".stripMargin

  private def initClosing: String =
    """
      |    l.toList
      |  }
      |}
      |""".stripMargin

  private def singleLoop: String = {
    initHeader +
      initLoopOpening(instances) +
      itemIds.map(initToVal(_)).mkString("\n") +
      initLoopClosing + initClosing
  }

  private def mixedLoop: String = {
    val iters: List[Int] = categories.getArticleStocks
    // offset by 1 when reading the stock amount from iters
    initHeader +
      itemIds.map(id => initLoopOpening(iters(id-1))+initToVal((id))+initLoopClosing).mkString("\n") +
      initClosing
  }

  private def exampleHeader: String =
    s"""package ${packageName}
       |
       |import meta.classLifting.Lifter
       |import meta.deep.codegen._
       |import meta.deep.runtime.Actor
       |import meta.deep.IR
       |import meta.deep.IR.TopLevel._
       |
       |import meta.example.supermarket.Supermarket
       |import meta.example.supermarket.goods._
       |import meta.example.supermarket.people._
       |
       |object ${exampleName} extends App {
       |  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
       |""".stripMargin

  private def exampleToVal(itemId: Int): String ={
    val itemName: String = "Item" + itemId
    s"  val cls${itemId}: ClassWithObject[${itemName}] = ${itemName}.reflect(IR)"
  }

  private def toStartClass: String =
    s"""
      |  val startClasses: List[Clasz[_ <: Actor]] = ${itemIds.map("cls"+_)}
      |""".stripMargin

  private def exampleClosing: String =
    s"""
       |  val lifter = new Lifter()
       |  val simulationData = lifter(startClasses, mainClass)
       |
       |  val pipeline = Pipeline(new CreateActorGraphs(simulationData._1), List(
       |    new EdgeMerge(),
       |    new CreateCode(simulationData._2, "${storagePathGegenerated}"),
       |  ))
       |
       |  pipeline.run()
       |}
       |""".stripMargin

  private def fileExample: String  = {
    exampleHeader +
      itemIds.map(exampleToVal(_)).mkString("\n") +
      toStartClass +
      exampleClosing
  }

  main()
}
