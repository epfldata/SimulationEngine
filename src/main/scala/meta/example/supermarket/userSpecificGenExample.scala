package meta.example.supermarket

import java.io.File

import meta.example.supermarket.goods.newItemsMap

object userSpecificGenExample {
  val exampleDir: String = "testItemOnly"
  val exampleName: String = exampleDir+"Example"
  val packageName: String = s"meta.example.supermarket.${exampleName}"
  val initName: String = "MainInit"

  val totalItems: Int = newItemsMap.itemMap.size
  val custIds: List[Int] = List(1)
  val itemIds: List[Int] = (1 to totalItems).toList

  val storagePathGegenerated: String = "generated/main/scala"

  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"
  val fdir = new File(cwd + s"${exampleDir}")
}
