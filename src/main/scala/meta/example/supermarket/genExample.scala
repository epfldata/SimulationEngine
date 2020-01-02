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
    case 0 => fileInit = mixedStock
    case _ => fileInit = sameStock
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
       |    val l_repeat = ListBuffer[Actor]()
       |
       |""".stripMargin

  private def initCustomer: String = {
    customers match {
      case -1 => ""
      case 0 => ""
      case _ => custIds.map(initToVal(_, customers, "customer")).mkString("\n")
    }
  }

  private def initToVal(agentId: Int, instances: Int, agentType: String): String = {
    agentType.toLowerCase match {
      case "item" =>
        s"""    1.to(${instances}).foreach(_ => l_repeat.append(new Item${agentId}))
           |    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
           |    l ++= l_repeat
           |    l_repeat.clear()
           |""".stripMargin
      case "customer" =>
        s"""    1.to(${instances}).foreach(_ => l_repeat.append(new Customer${agentId}))
           |    l ++= l_repeat
           |    l_repeat.clear()
           |""".stripMargin
      case _ => println("Unknown agent type!"); throw new Exception
    }
  }

  private def initClosing: String =
    """
      |    l.toList
      |  }
      |}
      |""".stripMargin

  private def sameStock: String = {
    initHeader +
      initCustomer + "\n" +
      itemIds.map(initToVal(_, instances, "item")).mkString("\n") + initClosing
  }

  private def mixedStock: String = {
    val iters: Vector[Int] = categories.getArticleStocks
    // offset by 1 when reading the stock amount from iters
    initHeader +
      initCustomer + "\n" +
      itemIds.map(id => initToVal(id, iters(id-1), "item")).mkString("\n") + initClosing
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
