package meta.classLifting


object SpecialInstructions {

  /** makes the actor wait for a number of turns
    *
    * usage - make actors of different granularity
    */

  // Create a synchronization label for the selected Sims.
  def waitLabel(label: String, waitValue: Double): Unit = ???

  // Handle incoming messages
  def handleMessages(): Unit = ???

  // Interrupt for time
  def interrupt(interval: Double, method: (()=>Any)): Unit = ???
}
