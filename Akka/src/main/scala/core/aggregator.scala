package simulation.akka.core

import scala.collection.immutable
import scala.concurrent.duration.FiniteDuration
import scala.reflect.ClassTag

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.NoSerializationVerificationNeeded

// source: https://doc.akka.io/docs/akka/2.6.12//typed/interaction-patterns.html

object Aggregator {

  sealed trait Command extends NoSerializationVerificationNeeded
  private case object ReceiveTimeout extends Command
  private case class WrappedReply[R](reply: R) extends Command 

  def apply[Reply: ClassTag, Aggregate](
      sendRequests: ActorRef[Reply] => Unit,
      expectedReplies: Int,
      replyTo: ActorRef[Aggregate],
      aggregateReplies: IndexedSeq[Reply] => Aggregate,
      timeout: FiniteDuration): Behavior[Command] = {
    Behaviors.setup { context =>
      context.setReceiveTimeout(timeout, ReceiveTimeout)
      val replyAdapter = context.messageAdapter[Reply](WrappedReply(_))
      sendRequests(replyAdapter)

      def collecting(replies: immutable.IndexedSeq[Reply]): Behavior[Command] = {
        Behaviors.receiveMessage {
          case WrappedReply(reply: Reply) =>
            val newReplies = replies :+ reply
            context.log.debug(f"Received replies ${newReplies.size}")
            if (newReplies.size == expectedReplies) {
              context.log.debug(f"Received all replies!")
              val result = aggregateReplies(newReplies)
              replyTo ! result
              context.log.debug(f"Aggregate replies complete!")
              Behaviors.stopped
            } else
              collecting(newReplies)

          case ReceiveTimeout =>
            throw new Exception(f"Failed to collect all messages! Received ${replies.size}")
            context.log.error("Collect messages time out!")
            val aggregate = aggregateReplies(replies)
            replyTo ! aggregate
            Behaviors.stopped
        }
      }

      collecting(Vector.empty)
    }
  }

}

