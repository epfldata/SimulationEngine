package meta.runtime.simulation

import scala.collection.immutable
import scala.concurrent.duration.FiniteDuration
import scala.reflect.ClassTag

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.NoSerializationVerificationNeeded

import scala.collection.mutable.ListBuffer
// source: https://doc.akka.io/docs/akka/2.6.12//typed/interaction-patterns.html

object Aggregator {

  sealed trait Command extends NoSerializationVerificationNeeded
  private case object ReceiveTimeout extends Command
  private case class WrappedReply[R](reply: R) extends Command 

  def apply[Reply: ClassTag, Aggregate](
      sendRequests: ActorRef[Reply] => Unit,
      expectedReplies: Int,
      replyTo: ActorRef[Aggregate],
      aggregateReplies: ListBuffer[Reply] => Aggregate,
      timeout: FiniteDuration): Behavior[Command] = {
    Behaviors.setup { context =>
      context.setReceiveTimeout(timeout, ReceiveTimeout)
      val replyAdapter = context.messageAdapter[Reply](WrappedReply(_))
      sendRequests(replyAdapter)

      val replies: ListBuffer[Reply] = new ListBuffer[Reply]()

      def collecting(): Behavior[Command] = {
        Behaviors.receiveMessage {
          case WrappedReply(reply: Reply) =>
            replies.append(reply)
            context.log.debug(f"Total replies ${replies.size}")
            if (replies.size == expectedReplies) {
              context.log.warn(f"Received all replies!")
              val result = aggregateReplies(replies)
              replyTo ! result
              context.log.warn(f"Aggregate replies complete!")
              Behaviors.stopped
            } else {
              collecting()
            }
          case ReceiveTimeout =>
            context.log.error("Collect messages time out!")
            val aggregate = aggregateReplies(replies)
            replyTo ! aggregate
            Behaviors.stopped
        }
      }

      collecting()
    }
  }

}