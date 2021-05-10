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
    // radius: optionally, we can have neighbors of radius 2 or more. Right now we define 8 neighbors with radius=1
    
    if (x < 0 || x >= width * height) {
      throw new IndexOutOfBoundsException
    }

    val neighbors: Array[Int] = new Array[Int](8)
    val area = width * height
		neighbors(0) = mod(x-1, area)
		neighbors(1) = mod(x+1, area)
		neighbors(2) = mod(x - width, area)
		neighbors(3) = mod(x + width, area)
		neighbors(4) = mod(neighbors(2)-1, area)
		neighbors(5) = mod(neighbors(2)+1, area)
		neighbors(6) = mod(neighbors(3)-1, area)
		neighbors(7) = mod(neighbors(3)+1, area)

    neighbors.toList
  }

  def mod(a: Int, b: Int): Int = {
		val r = a % b
		if (r < 0) {
			r + b
		} else {
			r
		}
	}
}