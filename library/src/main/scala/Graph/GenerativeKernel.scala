package cloudcity.lib
package Graph

trait GenerativeKernel[V] extends Component[V] {
    val neighborKernel: Array[Coordinate2D]

    override def gather(x: Int, y: Int): Iterable[V] = {
        neighborKernel.map(i => {
            currentBoard(i.x + x)((i.y + y + cols) % cols)
        })
    }
}