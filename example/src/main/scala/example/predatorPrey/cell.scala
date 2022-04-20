package example
package predatorPrey

import lib.Grid.AgentWithNeighbors

object predatorPrey {
    val emptyEncoding: Int = 0
    val npcEncoding: Int = 1
    val playerEncoding: Int = 2
}

case class Player(isNPC: Boolean) {
    var score: Int = 10
    var time: Int = 0

    // At each step, a player pays 1pt, NPC gains 1pt
    def survive(): Unit = {
        time += 1
        if (!isNPC){
            score -= 1
        } else {
            score += 1
        }
    }

    def fight(other: Player): Player = {
        if (other.isNPC && !isNPC) { // npc fights player
            score += 10
            this
        } else if (!other.isNPC && isNPC) {
            other.score += 10
            other
        } else { // npc fights npc or player fights player, higher score wins
            if (other.score > score) {
                other
            } else {
                this
            }
        }
    }
}

/**
 * Hunter gains 2 pt if catches an NPC
 * Hunter loses 1 pt at each time unit
 * NPC dies when score reaches 0
 * Game over if no NPCs left or hunter dies
 */

@lift
class Cell(val stepUnit: Int) extends AgentWithNeighbors {

    var currentPlayer: Option[Player] = None
    private var player: Player = null
    private var lookAround: List[Future[(Long, Int)]] = List()
    private var counter: Int = 0
    private var emptyCell: Cell = null

    def getIdentity(): (Long, Int) = {
        if (currentPlayer.isEmpty) {
            (id, predatorPrey.emptyEncoding)
        } else if (currentPlayer.get.isNPC){
            (id, predatorPrey.npcEncoding)
        } else {
            (id, predatorPrey.playerEncoding)
        }
    }

    // The player moves to a neighboring cell
    def playerMove(player: Player): Boolean = {
        if (currentPlayer.isEmpty) {
            currentPlayer = Some(player)
        } else {
            currentPlayer = Some(currentPlayer.get.fight(player))
        }
        true
    }

    def main(): Unit = {
        while (true) {
            // At each game time unit
            if (currentPlayer.isDefined){
                lookAround = connectedAgents.map(x => x._2.asInstanceOf[Cell]).toList.map(v => asyncMessage[(Long, Int)](() => v.getIdentity()))

                while (!lookAround.forall(x => x.isCompleted)) {
                    waitAndReply(1)
                    counter = counter + 1
                }

                val neighborIds = lookAround.map(i => i.popValue.get).asInstanceOf[List[(Long, Int)]]
                // Other players may have killed me when I look. Check again.
                if (currentPlayer.isDefined){
                    if (currentPlayer.get.isNPC) {
                        val sortedCells: List[(Long, Int)] = neighborIds.sortBy(_._2)
                        // If sees players and there exists empty cell
                        if (sortedCells.last._2 == predatorPrey.playerEncoding && sortedCells.head._2==predatorPrey.emptyEncoding) {
                            player = currentPlayer.get
                            emptyCell = connectedAgents(sortedCells.head._1).asInstanceOf[Cell]
                            asyncMessage(() => emptyCell.playerMove(player))
                            currentPlayer = None
                        }
                    } else {
                        val sortedCells: List[(Long, Int)] = neighborIds.sortWith((x, y) => (x._2 < predatorPrey.playerEncoding))
                        if (sortedCells.head._2<predatorPrey.playerEncoding) {
                            player = currentPlayer.get
                            emptyCell = connectedAgents(sortedCells.head._1).asInstanceOf[Cell]
                            asyncMessage(() => emptyCell.playerMove(player))
                            currentPlayer = None
                        }
                    }
                }
                waitAndReply(stepUnit - counter)
            } else {
                waitAndReply(stepUnit)
            }

            counter = 0
            if (currentPlayer.isDefined){
                currentPlayer.get.survive()
                if (currentPlayer.get.score == 0) {
                    currentPlayer = None
                }
            }
        }
    }
}