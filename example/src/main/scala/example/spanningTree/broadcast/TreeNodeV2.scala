package example
package spanningTree.broadcast

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

/**
 * Given a rooted spanning tree
 */
@lift
class TreeNodeV2(val root: Boolean) extends Actor {

  var parent: Option[TreeNodeV2] = None
  var children: Set[TreeNodeV2] = Set()

  def go(p: TreeNodeV2): Unit = {
    println(s"Child $id receives msg from parent ${p.id}!")
    parent = Some(p)
    children.foreach(c => asyncMessage(() => c.go(this)))
  }

  def main(): Unit = {
    if (root) {
      parent = Some(this)
      children.foreach(c =>  asyncMessage(() => c.go(this)))
    }
    handleMessages()
  }
}
