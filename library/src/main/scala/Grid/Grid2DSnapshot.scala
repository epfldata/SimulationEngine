package lib.Grid

class Grid2DSnapshot(input: List[Int], 
                width: Int, 
                height: Int, 
                colorScheme: Map[Int, String]) {
    import Grid2DSnapshot._
    def serialize(): String = {
        // const setting = new Map([${mapAdapter(colorScheme)}]);
f"""
#${width},${height}
${listAdapter(input)}
"""
    }
}

object Grid2DSnapshot {
    // Convert a Scala map to a string representing the content of js map
    def mapAdapter(m: Map[Int, String]): String = {
        m.map(x => f"""[${x._1}, "${x._2}"]""").mkString(",")
    }

    def listAdapter(m: List[Int]): String = {
        m.map(x => x.toString()).mkString(",")
    }
}