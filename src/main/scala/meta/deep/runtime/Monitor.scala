package meta.deep.runtime

import scala.collection.mutable.{ListBuffer, Map}

object Monitor {
  type eventType = Double
  val aggregates: Map[String, eventType] = Map[String, eventType]()
  val timeseries: Map[String, ListBuffer[eventType]] = Map[String, ListBuffer[eventType]]()

  private var daily_aggregate: Map[String, eventType] = Map[String, eventType]()

  def logAggregate(attr: String, num: eventType = 1): Unit = {
    if (!aggregates.get(attr).isDefined){
      aggregates += (attr -> num)
    } else {
      aggregates += (attr -> List(aggregates.get(attr).get, num).sum)
    }
  }

  def logTimeseries(attr: String, num: eventType = 1): Unit = {
    if (!daily_aggregate.get(attr).isDefined){
      daily_aggregate += (attr -> num)
    } else {
      daily_aggregate += (attr -> List(daily_aggregate.get(attr).get, num).sum)
    }
  }

  def initTimeseries(attr: String *): Unit = {
    attr.foreach(x =>
      timeseries += (x -> ListBuffer())
    )
  }

  // consider handling aggregates at different granularity
  private def timeElapse(): Unit = {
    timeseries.foreach(x =>
      x._2.append(daily_aggregate.getOrElse(x._1, 0))
    )
    daily_aggregate.clear()
  }

  def eachIteration(action:()=>Unit =
                    ()=>{println("Monitor stats: " + aggregates)}): Unit = {
    (action())
    timeElapse()
  }

  def onCompletion(action:()=>Unit =
                   ()=>println("Summary: \n" + aggregates
                     + "\nTimeseries:\n" + timeseries)): Unit = {
    (action())
  }
}
