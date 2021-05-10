package meta.runtime
package simulation

import scala.collection.mutable.ArrayBuffer
import meta.runtime.Actor.AgentId

object util {

  def groupAgents(candidates: List[List[AgentId]], actors: Map[AgentId, Actor]): List[Actor] = {
    // meta.Util.debug(s"Fuse agents: ${candidates}")
    candidates.map(x => {
      val c1 = new Container()
      c1.addAgents(x.map(aId => actors(aId)))
      c1
    })
  }

  private def truncDigit(num: Double): Double = {
      BigDecimal(num).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def displayTime(turn: Int, time: Double): String = {
    "(Time " + truncDigit(time) + " Turn " + turn + ")" 
  }

  def bench(code: => Unit ): Unit = {
    val start: Long = System.currentTimeMillis()
    code
    val end: Long = System.currentTimeMillis()
    println(s"Total time: ${end - start} ms")
  }
}
