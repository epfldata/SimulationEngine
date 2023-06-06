package cloudcity.lib
package Graph

import meta.runtime.{Message}

trait TopoMessage extends Message {
    val content: Any
}
case class VertexMessage[T](content: T) extends TopoMessage
case class ComponentMessage[T](content: Array[T], val componentId: RowCoord) extends TopoMessage
case class BatchComponentMessage[T](content: Iterable[ComponentMessage[T]]) extends TopoMessage
