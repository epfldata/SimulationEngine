package meta.runtime

import meta.classLifting.SpecialInstructions.waitMode
import scala.collection.mutable.{ListBuffer, Map}

object SimRuntime {
  val newActors: ListBuffer[Actor] = ListBuffer[Actor]()

  /**
   * A map tracks the status of the future objects. Each future object is indexed by its own id. 
   */ 
  private var async_messages: Map[String, Future[Any]] = Map[String, Future[Any]]()

  // track the number of Sims waiting for each label at each iteration. Set once
  private val waitLabels: Map[String, Long] = Map[String, Long]()

  // track the min value that each label group should advance by
  val labelVals: Map[String, ListBuffer[Double]] = Map[String, ListBuffer[Double]]()
  var proceedLabel: Map[String, Double] = Map[String, Double]()

  def registerLabel(group: waitMode, totalSubscribers: Long): Unit = {
    waitLabels += (group.toString -> totalSubscribers)
  }

  /**
   * Add a future object to the async_messages map 
   */ 
  def addFuture(id: String, future: Future[Any]) = {
    async_messages += (id -> future)
  }

  /**
   * Check whether a future object has received its value   
   */ 
  def isCompleted(future_obj: Future[Any]): Boolean = {
    // println(s"Current async map: ${async_messages}")
    async_messages.get(future_obj.id).isDefined
  }

  /**
   * Return the value of the future object and remove it from the internal map async_messages. None if the value is not ready yet
   */ 
  def popFutureValue[T](future_obj: Future[T]): Option[T] = {
    if (!isCompleted(future_obj)){
      None 
    } else {
      val res: T = async_messages.get(future_obj.id).get.value.get.asInstanceOf[T]
      async_messages -= (future_obj.id)
      Some(res)
    }
  }

  def initLabelVals(): Unit = {
    waitLabels("time") = 0
    waitLabels("turn") = 0
    waitLabels.keys.foreach(k => {
      labelVals(k) = ListBuffer[Double]()
      proceedLabel(k) = 0
    })
  }

  def proceedGroups(): Unit = {
    waitLabels.keys.foreach(k => {
      proceedLabel(k) = k match {
        case "turn" => {
          // turn may be empty, if Sims are sync with blocking calls
          if (labelVals(k).nonEmpty) {
            labelVals(k).min
          } else {
            1
          }
        }
        case _ => {
          val foo = labelVals(k)
          if (foo.length == waitLabels(k) && waitLabels(k)!=0) {
            foo.min
          } else {
            0
          }
        }
      }
      labelVals(k).clear()
    })
  }
}
