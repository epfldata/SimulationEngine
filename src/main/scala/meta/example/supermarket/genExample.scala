package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}

object genExample extends App {
  import userSpecific._

  assert(totalItems>0)
  assert(itemIds==itemIds.sorted)
  assert(itemIds(0)>0 && itemIds.last<=totalItems)

  if (!fdir.exists()){
    fdir.mkdirs()
  }

  println(s"Please enter the number of items for each article. 0 if non-uniform. There are total ${totalItems} items")
  val instances = scala.io.StdIn.readInt()
  println("Please enter the number of customers you would like to include. -1 if non-uniform")
  val customers = scala.io.StdIn.readInt()

  assert(instances >=0)
  assert(customers >=0)

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
       |/* Auto generated */
       |
       |@lift
       |class MainInit {
       |  def main(): List[Actor] = {
       |    val l = ListBuffer[Actor]()
       |    var ctr: Int = 0
       |""".stripMargin

  private def initCustomer: String = {
    customers match {
      case -1 => ""
      case 0 => ""
      case _ =>
        initLoopOpening(customers) +
          custIds.map(initCustToVal(_)).mkString("\n") +
          initLoopClosing + "\n"
    }
  }

  private def initLoopOpening(instances: Int): String =
    s"""
       |    ctr = 0
       |    while (ctr < ${instances}) {
       |""".stripMargin

  private def initItemToVal(itemId: Int): String = {
    val itemName = "Item" + itemId
    val valName = itemName.toLowerCase
    s"""      val ${valName} = new ${itemName}
       |      Supermarket.store.add(${valName}.asInstanceOf[Item])
       |      l.append(${valName})
       |""".stripMargin
  }

  private def initCustToVal(custId: Int): String = {
    val custName = "Customer" + custId
    val valName = custName.toLowerCase
    s"""      val ${valName} = new ${custName}
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
    initHeader + initCustomer +
      initLoopOpening(instances) +
      itemIds.map(initItemToVal(_)).mkString("\n") +
      initLoopClosing + initClosing
  }

  private def mixedLoop: String = {
    val iters: List[Int] = categories.getArticleStocks
    // offset by 1 when reading the stock amount from iters
    initHeader + initCustomer +
      itemIds.map(id => initLoopOpening(iters(id-1))+initItemToVal((id))+initLoopClosing).mkString("\n") +
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

  private def itemToVal(itemId: Int): String ={
    val itemName: String = "Item" + itemId
    s"  val cls${itemId}: ClassWithObject[${itemName}] = ${itemName}.reflect(IR)"
  }

  private def customerToVal(custId: Int): String = {
    val custName: String = "Customer" + custId
    customers match {
      case -1 => ""
      case 0 => ""
      case _ => s"  val clsCust${custId}: ClassWithObject[${custName}] = ${custName}.reflect(IR)"
    }
  }

  private def toStartClass: String =
    s"""
      |  val startClasses: List[Clasz[_ <: Actor]] = ${itemIds.map("cls"+_) ++ (if (customers>0 || customers==(-1)) custIds.map("clsCust"+_) else List())}
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
    customers match {
      case 0 =>
        exampleHeader +
        itemIds.map(itemToVal(_)).mkString("\n") +
        toStartClass +
        exampleClosing
      case _ =>
        exampleHeader +
        itemIds.map(itemToVal(_)).mkString("\n") + "\n" +
        custIds.map(customerToVal(_)).mkString("\n") +
        toStartClass +
        exampleClosing
    }
  }

  main()
}
