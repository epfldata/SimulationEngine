package lib
package Grid

trait Grid[T] {
  val width: Int
  val height: Int

  def placeAgent(agent: Actor, uniquePlacement: Boolean): Option[T]

  def removeAgent(agentLoc: T, agent: Actor): Unit

  def getAgentNeighbors(agentLoc: T, neighborRadius: Int): List[Actor]
}