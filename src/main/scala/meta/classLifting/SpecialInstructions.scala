package meta.classLifting

object SpecialInstructions {
  import meta.runtime.Future

  sealed trait waitMode
  case object Turn extends waitMode {
    override def toString: String = "turn"
  }

  case object Time extends waitMode {
    override def toString: String = "time"
  }

  case class Group(l: String) extends waitMode {
    assert(l != "time" && l != "turn")
    override def toString: String = l
  }

  def waitLabel(label: waitMode, waitValue: Double): Unit = ???

  def asyncMessage[T](message: (()=>T)): Option[Future[T]] = ???

  def handleMessages(): Unit = ???

  // Interrupt for time
  def interrupt(interval: Double, method: (()=>Any)): Unit = ???
}