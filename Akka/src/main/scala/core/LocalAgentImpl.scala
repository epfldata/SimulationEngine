package simulation.akka.core

import meta.runtime.{Actor, Message, JsonSerializable}
import akka.actor.typed.{ActorRef, PostStop, Behavior}
import akka.actor.typed.scaladsl.Behaviors


class LocalAgent {
    import LocalAgentSpec._

    private var sim: Actor = null
    private var start: Long = 0
    private var end: Long = 0

    def apply(sim: Actor): Behavior[AgentEvent] = Behaviors.setup { ctx =>
        this.sim = sim
        simAgent()
    }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case AddMessages(replyTo) => 
                    start = System.currentTimeMillis()
                    // println(f"Agent ${sim.id} receives ${messages.size} messages")
                    ctx.log.debug(f"Agent ${sim.id} receives ${sim.receivedMessages.size} messages")
                    val time = sim.run()
                    // replyTo ! MessagesAdded(time, sim.sendMessages)
                    replyTo ! MessagesAdded(time, sim.sendMessages.toMap.map(i => (i._1, i._2.toList)))
                    end = System.currentTimeMillis()
                    // println(f"Agent ${sim.id} runs ${end-start} ms")
                    Behaviors.same
            }
        }.receiveSignal {
            case (context, PostStop) =>
                context.log.debug(f"Agent ${sim.id} stopped")
                Behaviors.stopped
        }
}