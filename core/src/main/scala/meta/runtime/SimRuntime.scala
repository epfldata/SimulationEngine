package meta.runtime

import meta.classLifting.SpecialInstructions.waitMode
import scala.collection.mutable.{ListBuffer, Map}

object SimRuntime {
  val newActors: ListBuffer[Actor] = ListBuffer[Actor]()

  // track the number of Sims waiting for each label at each iteration. Set once
  private val waitLabels: Map[String, Long] = Map[String, Long]()

  // track the min value that each label group should advance by
  val labelVals: Map[String, ListBuffer[Double]] = Map[String, ListBuffer[Double]]()
  var proceedLabel: Map[String, Double] = Map[String, Double]()

  def registerLabel(group: waitMode, totalSubscribers: Long): Unit = {
    waitLabels += (group.toString -> totalSubscribers)
  }

  def initLabelVals(): Unit = {
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
