package meta.classLifting

object SpecialInstructions {
  import meta.runtime.Future

  /**
    * Different flavors of synchronization. Regardless of the mode, runtime calculates the min wait value and Sims proceed by the min amount. When a Sim calls waitLabel, then it's ready for the runtime to deliver messages in its mailbox
    */
  sealed trait waitMode

  /**
    * The default synchronization mode. A blocking call contains a wait turn of 1.
    * By convention, turns are integers
    */
  case object Turn extends waitMode {
    override def toString: String = "turn"
  }

  /**
    * Sims can synchronize among a group of them according to a label
    * @param l the group name. Need to define at compile time (in MainInit) the size of the group
    */
  case class Group(l: String) extends waitMode {
    assert(l.toLowerCase != "turn")
    override def toString: String = l
  }

  /**
    * This method synchronizes among Sims where the same label defined
    * @param label
    * @param waitValue the amount to wait for. At each iteration, the runtime calculates the min wait value among Sims; each Sim subtracts this min value. When the wait value reaches 0, Sim unblocks and continues
    */
  def waitLabel(label: waitMode, waitValue: Double): Unit = ???

  /**
    * The syntax for sending an asynchronous message.
    * @param message
    * @tparam T the return type of the message method
    * @return a Future object that holds the return value of the async message for later retrieval
    */
  def asyncMessage[T](message: (()=>T)): Future[T] = ???

  /**
    * The syntax for Sim to check its mailbox.
    */
  def handleMessages(): Unit = ???
}