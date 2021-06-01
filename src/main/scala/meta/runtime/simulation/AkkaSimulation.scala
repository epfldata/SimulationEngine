package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId

import akka.actor.typed.{ActorRef, ActorSystem, Terminated, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object Dispatcher {
  final case class SendToDispatcher(agentId: Long, messages: List[Message], replyTo: ActorRef[ReceiveFromDispatcher])
  final case class ReceiveFromDispatcher(messages: List[Message], from: ActorRef[SendToDispatcher])
  
  private var totalAgents: Int = 0
  private var totalTurn: Int = 0
  private var proxyMap: Map[AgentId, AgentId] = Map()

  private var agentRefMap: Map[AgentId, ActorRef[ReceiveFromDispatcher]] = Map()
  private val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

  def apply(maxAgents: Int, maxTurn: Int, pMap: Map[AgentId, AgentId]): Behavior[SendToDispatcher] = {
    totalAgents = maxAgents
    totalTurn = maxTurn
    proxyMap = pMap
    dispatcher(0, 0, System.currentTimeMillis())
  }

  private def dispatcher(agentCounter: Int, 
                        currentTurn: Int, 
                        currentTime: Long): Behavior[SendToDispatcher] =
    Behaviors.receive { (context, message) =>
      val aggAgents = agentCounter+1
      msgBuffer.appendAll(message.messages)
      agentRefMap = agentRefMap + (message.agentId -> message.replyTo)

      if (aggAgents == totalAgents) {
        val groupedMsgs = msgBuffer.map(x => (proxyMap(x.receiverId), x)).foldLeft(Map[AgentId, List[Message]]())((b, a) => { 
					if (b.get(a._1).isEmpty) { 
						b + (a._1 -> List(a._2)) 
					} else { 
						b + (a._1 -> (a._2 :: b(a._1))) 
					}
				})

        agentRefMap.foreach(a => {
          a._2 ! ReceiveFromDispatcher(groupedMsgs.getOrElse(a._1, List()), context.self)
        })

        val nextTurn = currentTurn + 1
        if (nextTurn == totalTurn) {
          Behaviors.stopped
        } else {
          val t = System.currentTimeMillis()
          msgBuffer.clear()
          agentRefMap = Map()
          context.log.info("Total time: {} ms", t - currentTime)
          dispatcher(0, nextTurn, t)
        } 
      } else {
        dispatcher(aggAgents, 
                currentTurn, 
                currentTime)
      }
    }
}

object SimAgent {

  def apply(sim: Actor): Behavior[Dispatcher.ReceiveFromDispatcher] = {
    simAgent(sim, 0)
  }

  private def simAgent(sim: Actor, currentTurn: Int): Behavior[Dispatcher.ReceiveFromDispatcher] =
    Behaviors.receive { (context, message) =>
        val next = currentTurn + 1
        val sentMessages = sim.run(message.messages)
        message.from ! Dispatcher.SendToDispatcher(sim.id, sentMessages, context.self)
        simAgent(sim, next)
    }
}


object SimExperiment {

  final case class StartSimulation(totalTurn: Int, actors: List[Actor])

  def apply(): Behavior[StartSimulation] =
    Behaviors.receive[StartSimulation] {  (context, message) =>
      message match {
        case StartSimulation(totalTurn, actors) => 
          val proxyMap = actors
          .map(a => (a.proxyIds, a.id))
          .flatMap(p => p._1.map(i => (i, p._2))).toMap

          context.log.info(f"Total actors: ${actors.size}")

          val dispatcher = context.spawn(Dispatcher(actors.size, totalTurn, proxyMap), "dispatcher")

          // val dispatcher = context.spawn(Dispatcher(actors.size, totalTurn, proxyMap), "dispatcher", DispatcherSelector.fromConfig("my-pinned-dispatcher"))
          
          actors.foreach(a => {
            val simAgent = context.spawn(SimAgent(a), f"simAgent${a.id}")
            // val simAgent = context.spawn(SimAgent(a), f"simAgent${a.id}", DispatcherSelector.fromConfig("my-pinned-dispatcher"))
            simAgent ! Dispatcher.ReceiveFromDispatcher(List(), dispatcher)
          })

          context.log.info(f"All agents started!")
          context.watch(dispatcher)
          Behaviors.same
      }
    }
    .receiveSignal {
      case (context, Terminated(ref)) => 
        context.log.info("Dispatcher has stopped!")
        Behaviors.stopped
    }
}

object AkkaRun {
  def apply(config: SimulationConfig): Unit = {
    val system: ActorSystem[SimExperiment.StartSimulation] =
        ActorSystem(SimExperiment(), "Sim System")
    system ! SimExperiment.StartSimulation(config.totalTurn, config.actors)
  }
}