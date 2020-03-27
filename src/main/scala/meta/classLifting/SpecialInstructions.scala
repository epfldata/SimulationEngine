package meta.classLifting

object SpecialInstructions {

  /** makes the actor wait for a number of turns
    *
    * usage - make actors of different granularity
    */
  def waitTurns(numTurns: Int): Unit = ???

  def batchMessages(message: (()=> Any)*): Unit = ???
}
