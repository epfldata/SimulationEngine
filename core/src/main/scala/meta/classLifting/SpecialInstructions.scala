package meta.classLifting

import meta.deep.IR.Predef._

object SpecialInstructions {
  import meta.runtime.Future

  /**
    * This method synchronizes among Sims where the same label defined, equal to wait(1)
    */
  def barrierSync(): Unit = ???

  /**
    * Wait for total rounds. 
    * @param n Total number of rounds. Do nothing at each round.
    */
  def waitRounds(n: Int): Unit = ???
  
  /**
    * Wait for total rounds. 
    * @param n Total number of rounds. At each round, process all messages
    */
  def waitAndReply(n: Int): Unit = ???

  /**
    * The syntax for sending an asynchronous message.
    * @param message
    * @tparam T the return type of the message method
    * @return a Future object that holds the return value of the async message for later retrieval
    */
  def asyncCall[T](message: () => T, latency: Int): Future[T] = ???

  /**
    * Annotate the RPC with transparencyPropagation. All references to the RPC will be delayed, including local calls
    *
    * @param message
    * @return
    */
  def asyncCall[T](message: T, latency: Int): Future[T] = ???

  def callAndForget[T](message: T, latency: Int): Unit = ???

  /**
    * Helper methods to inform lifter which variables or methods require private or override modifier
    *
    * @param fieldName
    */
  def markPrivate(fieldName: String*): Unit = {}
  
  def markOverride(fieldName: String*): Unit = {}

  def markAllowDirectAccess(methodName: String*): Unit = {}
}