package cloudcity.lib
package Graph

trait TopoCoordinate

case class VertexCoord(x: Int) extends TopoCoordinate
case class Coordinate2D(x: Int, y: Int) extends TopoCoordinate

trait ComponentIdType extends TopoCoordinate {
    def getRowId(): Int = ???
}

case class ComponentCoord(ul: Coordinate2D, lr: Coordinate2D) extends ComponentIdType {
    override def getRowId(): Int = {
        ul.x
    }
}

case class VectorCoord(startRow: Int, endRow: Int) extends ComponentIdType {
    override def getRowId(): Int = {
        startRow
    }
}

case class RowCoord(row: Int) extends ComponentIdType {
    override def getRowId(): Int = {
        row
    }
}