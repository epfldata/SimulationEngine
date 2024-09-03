package meta.API

import meta.runtime.{Actor, Message}
import scala.collection.mutable.Buffer
 
// The distinction is for performance
sealed trait SimulationData {
  def sims: Traversable[Actor] = ???
  def messages: Traversable[Message] = ???
  def timeseries: Iterable[Iterable[Serializable]] = ???
}

sealed case class Snapshot(override val sims: Traversable[Actor], override val messages: Traversable[Message]=List()) extends SimulationData
sealed case class Timeseries(override val timeseries: Iterable[Iterable[Serializable]]) extends SimulationData

sealed trait SimulationDataBuilder {
  def addAgents(agents: IndexedSeq[Actor]): Unit = {}
  def addMessages(msgs: IndexedSeq[Message]): Unit = {}
  def addTimeseries(ts: Iterable[Iterable[Serializable]]): Unit = {}
  def build(): SimulationData
}

sealed class SnapshotBuilder extends SimulationDataBuilder {
  private val p_sims: Buffer[Actor] = Buffer[Actor]()
  private val p_messages: Buffer[Message] = Buffer[Message]()

  override def addAgents(agents: IndexedSeq[Actor]): Unit = {
      p_sims ++= agents 
  }

  override def addMessages(msgs: IndexedSeq[Message]): Unit = {
    p_messages ++= msgs
  }

  override def build(): Snapshot = {
    Snapshot(p_sims.toList, p_messages.toList)
  }
}

sealed class TimeseriesBuilder(val strategy: DeforestationStrategy) extends SimulationDataBuilder {
  private var timeseries: Iterable[Iterable[Serializable]] = Iterable.empty

  override def addTimeseries(ts: Iterable[Iterable[Serializable]]): Unit = {
    timeseries = timeseries ++ ts
  }

  override def build(): Timeseries = {
    Timeseries(timeseries)
  }
}