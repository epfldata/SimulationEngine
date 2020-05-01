package meta.classLifting
//import scala.concurrent.{ExecutionContext, Future}
import meta.deep.runtime.Future

object SpecialInstructions {

  /** makes the actor wait for a number of turns
    *
    * usage - make actors of different granularity
    */
  def waitTurns(numTurns: Int): Unit = ???

  def asyncMessage[T](message: (()=>T)): Option[Future[T]] = ???
}
