package meta.example.supermarket

import java.io.File

import meta.example.supermarket.goods.newItemsMap

object userSpecific {
  val exampleDir: String = "testShoppingList"
  val exampleName: String = exampleDir+"Example"
  val packageName: String = s"meta.example.supermarket.${exampleName}"
  val initName: String = "MainInit"

  val totalItems: Int = newItemsMap.itemMap.size
//  val itemIds: List[Int] = (1 to 3).toList
  val custIds: List[Int] = List(1)
  val itemIds: List[Int] = (1 to totalItems).toList
  //  val itemIds: List[Int] = List(1, 7, 11, 18, 23)

  val storagePathGegenerated: String = "generated/main/scala"
  //  val storagePathGegenerated: String = "src/main/scala/meta/example/supermarket/testItemsOnly"

  var cwd = new File(".").getCanonicalPath()
  cwd = cwd + "/src/main/scala/meta/example/supermarket/"
  val fdir = new File(cwd + s"${exampleDir}")
}
