package meta.example.supermarket

import java.io.{BufferedWriter, File, FileWriter}

/*
 * Single threaded code generation for MainInit and example_nameExample
 * Assume a single agent Supermarket, and other agents are Items
 * Can extend to include agents such as customers as well.
 * Need to change the import statements
*/
trait gen_example {
  type agentName = String

  // agents' name first letter needs to be capitalized, Supermarket included
  val agentss: List[agentName] = (1 to 25).toList.map(el=>"Item"+el)
  var agentCounter: Int = 0

  val example_dir: String = "testItemsOnly"
  val example_name: String = example_dir+"Example"
  val init_name: String = "MainInit"
  val package_name: String = s"meta.example.supermarket.${example_name}"

  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"
  val fdir = new File(cwd + s"${example_dir}")

  val storagePathGegenerated: String = "generated/main/scala"
//  val storagePathGegenerated: String = "src/main/scala/meta/example/supermarket/testItemsOnly"

  // If customers are included, need to modify this.
  val agent_import: String =
    s"""
       |import meta.example.supermarket.Supermarket
       |import meta.example.supermarket.goods._
       |""".stripMargin
}

object file_init extends gen_example {
  val import_init: String =
    s"""package ${package_name}
      |
      |import meta.deep.runtime.Actor
      |import squid.quasi.lift
      |import scala.collection.mutable.ListBuffer
      |""".stripMargin +
      agent_import

  var result_s: String =
    s"""@lift
       |class MainInit {
       |  def main(): List[Actor] = {
       |    val l = ListBuffer[Actor]()
       |
       |    val supermarket: Supermarket = new Supermarket()
       |
       |""".stripMargin

  def toValStr(name: agentName): String ={
    val val_name = name.toLowerCase
    assert(val_name!=name)
    agentCounter = agentCounter + 1
    s"""    val ${val_name} = new ${name}
       |    ${val_name}.supermarket = supermarket
       |    l.append(${val_name})
       |
       |""".stripMargin
  }

  agentss.map(agent => result_s = result_s + toValStr(agent))

  result_s = import_init + result_s +
    """    l.toList
      |  }
      |}
      |""".stripMargin
}

object file_example extends gen_example {
  val import_example: String =
    s"""package ${package_name}
      |
      |import meta.classLifting.Lifter
      |import meta.deep.codegen._
      |import meta.deep.runtime.Actor
      |import meta.deep.IR
      |import meta.deep.IR.TopLevel._
      |""".stripMargin +
      agent_import

  var result_s: String =
    s"""
       |object ${example_name}Example extends App {
       |  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)
       |""".stripMargin

  def toValStr(name: agentName): String ={
    agentCounter = agentCounter + 1
    s"  val cls${agentCounter}: ClassWithObject[${name}] = ${name}.reflect(IR) \n"
  }

  agentss.map(agent => result_s = result_s + toValStr(agent))

  val toStartClass: String = "  val startClasses: List[Clasz[_ <: Actor]] " +
    s"= ${(1 until agentCounter+1).map(num => s"cls${num}").toList}"


  result_s = import_example + result_s + toStartClass +
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
}

object gen_example_init extends App with gen_example {
  if (!fdir.exists()){
    fdir.mkdirs()
  }

  val file = new File(cwd + s"${example_dir}/${example_name}.scala")
  val bw = new BufferedWriter(new FileWriter(file))
  bw.write(file_example.result_s)
  bw.close()

  val file2 = new File(cwd + s"${example_dir}/${init_name}.scala")
  val bw2 = new BufferedWriter(new FileWriter(file2))
  bw2.write(file_init.result_s)
  bw2.close()
}
