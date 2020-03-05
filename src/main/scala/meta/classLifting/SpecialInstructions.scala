package meta.classLifting

object SpecialInstructions {

  /** makes the actor wait for a number of turns
    *
    * usage - make actors of different granularity
    */
  def waitTurns(numTurns: Int): Unit = ???

//  /** makes the actor handle all received messages */
//  def handleMessages(): Unit = ???

  // Consider batching messages (multicasting)
//  def batchMsg(any: Any): Unit = ???
}
