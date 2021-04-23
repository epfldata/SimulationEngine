package lib
package Grid

import scala.util.Random

import Torus2D._

class Torus2D(val width: Int, val height: Int) extends Grid[Int] {
  private val capacity: Int = width*height
  private val emptyLoc: Array[Int] = Array.fill[Int](capacity)(1)
  private var currentPlacement: Map[Int, List[Actor]] = Map()
  private var load: Int = 0

  private val maxAttempt: Int = 5

  override def placeAgent(agent: Actor, uniquePlacement: Boolean): Option[Int] = {
    if (load >= capacity && uniquePlacement) {
      return None
    }
    placeAgentHelper(agent, uniquePlacement, 0)
  }

  private def placeAgentHelper(agent: Actor, uniquePlacement: Boolean, attempt: Int): Option[Int] = {
    if (attempt >= maxAttempt){
      return None
    }
    val randIdx: Int = Random.nextInt(capacity)
    if (emptyLoc(randIdx) == 1 || !uniquePlacement) {
      emptyLoc(randIdx) = 0
      val existingPlacement: List[Actor] = currentPlacement.getOrElse(randIdx, List())
      currentPlacement = currentPlacement + (randIdx -> (agent :: existingPlacement))
      load += 1
      Some(randIdx)
    } else {
      placeAgentHelper(agent, uniquePlacement, attempt+1)
    }
  }

  override def removeAgent(agentLoc: Int, agent: Actor): Unit = {
    currentPlacement.get(agentLoc) match {
      case None =>
//      case List() =>
      case Some(x) => {
        currentPlacement = currentPlacement + (agentLoc -> x.filter(a => a!=agent))
        if (x.length == 1) { emptyLoc(agentLoc) = 1 }
        load -= 1
      }
      case _ => assert(false)
    }
  }

  override def getAgentNeighbors(agentLoc: Int, neighborRadius: Int): List[Actor] = {
    val neighborCells: List[Int] = getNeighborCells(width, height)(agentLoc, neighborRadius)
    neighborCells.flatMap(x =>
      currentPlacement.get(x).getOrElse(List()))
  }
}

object Torus2D {
  def getNeighborCells(width: Int, height: Int)(x: Int, radius: Int): List[Int] = {
    if (x < 0 || x >= width * height) {
      throw new IndexOutOfBoundsException
    }

    // 4 neighbor cells
    val neighbor4: Set[Int] = Set("N", "S", "E", "W").map(d => directionalCell(width, height)(d, x))
    // 8 neighbor cells (+ NW, SE, NE, SW)
    val neighbor8: Set[Int] = Set("W", "E", "N", "S").zip(neighbor4).map(pair => directionalCell(width, height)(pair._1, pair._2)) ++ neighbor4

    val perIt: Set[Int] = neighbor8

    if (radius > 1) {
      perIt ++ perIt.flatMap(c => getNeighborCells(width, height)(c, radius - 1))
    } else {
      perIt
    }
    perIt.toList
  }

  // Return the cell directly at the given direction of the center idx
  def directionalCell(width: Int, height: Int)(s: String, idx: Int): Int = {
    val f = (x: Int, isUpper: Boolean, boundary: Int, wrapped: Int) => {
      if (isUpper) {
        if (x > boundary) wrapped else x
      } else {
        if (x < boundary) wrapped else x
      }
    }

    s match {
      case "N" =>
        val foo: Int = idx - width
        f(foo, false, 0, foo + width * height)
      case "S" =>
        val max: Int = width * height - 1
        val foo: Int = idx + width
        f(foo, true, max, foo % width)
      case "E" =>
        val rBound: Int = (1 + idx / width) * width - 1
        val foo: Int = idx + 1
        f(foo, true, rBound, foo - width)
      case "W" =>
        val lBound: Int = (idx / width) * width
        val foo: Int = idx - 1
        f(foo, false, lBound, foo + width)
    }
  }
}