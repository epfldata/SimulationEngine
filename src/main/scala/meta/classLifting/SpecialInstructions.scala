package meta.classLifting


object SpecialInstructions {
  import meta.runtime.Future
  import Broadcast._

  /** makes the actor wait for a number of turns
    *
    * usage - make actors of different granularity
    */

  // Create a synchronization label for the selected Sims.
  def waitLabel(label: String, waitValue: Double): Unit = ???

  def asyncMessage[T](message: (()=>T)): Option[Future[T]] = ???

  def broadcast[A, T](recipients: List[A], mtd: A=>T, mode: broadcastMode): Option[List[Future[T]]] = ???

  // Handle incoming messages
  def handleMessages(): Unit = ???

  // Interrupt for time
  def interrupt(interval: Double, method: (()=>Any)): Unit = ???
}

object Broadcast {
  sealed trait broadcastMode
  case object waitUntilRecipientsReceived extends broadcastMode
  case object waitUntilRecipientsReplied extends broadcastMode
  case object noWait extends broadcastMode
}