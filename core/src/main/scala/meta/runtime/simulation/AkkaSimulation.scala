package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot

import scala.concurrent.Await
import scala.concurrent.duration._

import akka.NotUsed
import akka.actor.typed.{ActorRef, ActorSystem, Terminated, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object Dispatcher {
  sealed trait DispatcherEvent
  final case class ReceiveFromDispatcher(messages: List[Message], from: ActorRef[SimAgent.AgentEvent]) extends DispatcherEvent
  final case class Stop(from: ActorRef[SimAgent.AgentEvent]) extends DispatcherEvent
  
  private var totalAgents: Int = 0
  private var totalTurn: Int = 0
  private var proxyMap: Map[AgentId, AgentId] = Map()

  private var agentRefMap: Map[AgentId, ActorRef[DispatcherEvent]] = Map()
  val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

  val finalStates: ListBuffer[Actor] = ListBuffer[Actor]()
  var proposedTime: Int = 1

  def apply(maxAgents: Int, maxTurn: Int, pMap: Map[AgentId, AgentId], messages: List[Message]): Behavior[SimAgent.AgentEvent] = {
    totalAgents = maxAgents
    totalTurn = maxTurn
    proxyMap = pMap
    msgBuffer.appendAll(messages)
    dispatcher(0, 0, System.currentTimeMillis())
  }

  private def dispatcher(agentCounter: Int, 
                        currentTurn: Int, 
                        currentTime: Long): Behavior[SimAgent.AgentEvent] =
    Behaviors.receive { (context, message) =>
        message match {
            case SimAgent.SendToDispatcher(agentId: Long, messages: List[Message], elapsedTime: Int, replyTo: ActorRef[Dispatcher.DispatcherEvent]) => 
                val aggAgents = agentCounter+1
                if (elapsedTime > proposedTime){
                    proposedTime = elapsedTime
                }
                msgBuffer.appendAll(messages)
                agentRefMap = agentRefMap + (agentId -> replyTo)

                if (aggAgents == totalAgents) {
                    val groupedMsgs = msgBuffer.map(x => (proxyMap(x.receiverId), x)).foldLeft(Map[AgentId, List[Message]]())((b, a) => { 
                        if (b.get(a._1).isEmpty) { 
                            b + (a._1 -> List(a._2)) 
                        } else {
                            b + (a._1 -> (a._2 :: b(a._1))) 
                        }
                    })

                    val nextTurn = currentTurn + proposedTime
                    proposedTime = 1
                    val t = System.currentTimeMillis()
                    context.log.info("Turn {} Total time: {} ms", currentTurn, t - currentTime)

                    if (nextTurn >= totalTurn) {
                        agentRefMap.foreach(a => {
                            a._2 ! Stop(context.self)
                        })
                    } else {
                        agentRefMap.foreach(a => {
                            a._2 ! ReceiveFromDispatcher(groupedMsgs.getOrElse(a._1, List()), context.self)
                        })

                        msgBuffer.clear()
                        agentRefMap = Map()

                        val newAgents = SimRuntime.newActors.map(a => {
                                context.spawn((new SimAgent).apply(a), f"simAgent${a.id}")})

                        totalAgents += newAgents.size

                        val newProxyMap = SimRuntime.newActors
                            .map(a => (a.proxyIds, a.id))
                            .flatMap(p => p._1.map(i => (i, p._2))).toMap

                        proxyMap = proxyMap ++ newProxyMap
                        SimRuntime.newActors.clear()
                        
                        newAgents.foreach(a => a ! Dispatcher.ReceiveFromDispatcher(List(), context.self))
                    } 
                    dispatcher(0, nextTurn, t)
                } else {
                    dispatcher(aggAgents, 
                            currentTurn, 
                            currentTime)
                }

            case SimAgent.FinalState(actor: Actor) =>
                val aggAgents = agentCounter + 1
                if (aggAgents == totalAgents) {
                    Behaviors.stopped
                } else {
                    finalStates.append(actor)
                    dispatcher(aggAgents, currentTurn, currentTime)
                }
        }
    }
}

object SimAgent {
    sealed trait AgentEvent
    final case class SendToDispatcher(agentId: Long, messages: List[Message], elapsedTime: Int, replyTo: ActorRef[Dispatcher.DispatcherEvent]) extends AgentEvent
    final case class FinalState(actor: Actor) extends AgentEvent
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[Dispatcher.DispatcherEvent] = {
        this.sim = sim
        simAgent()
    }

    private def simAgent(): Behavior[Dispatcher.DispatcherEvent] =
        Behaviors.receive { (context, message) =>
            message match {
                case Dispatcher.ReceiveFromDispatcher(messages, from) => 
                    val agentAPI = sim.run(messages)
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    from ! SendToDispatcher(sim.id, sentMessages, elapsedTime, context.self)
                    simAgent()
                case Dispatcher.Stop(from) =>
                    from ! FinalState(sim)
                    Behaviors.stopped
            }
        }
}

object SimExperiment {

    def apply(totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[NotUsed] = 
        Behaviors.setup { context => 
            val proxyMap = actors
            .map(a => (a.proxyIds, a.id))
            .flatMap(p => p._1.map(i => (i, p._2))).toMap

            val dispatcher = context.spawn(Dispatcher(actors.size, totalTurn, proxyMap, messages), "dispatcher")
            
            val simAgents = actors.map(a => context.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))

            simAgents.foreach(a => a ! Dispatcher.ReceiveFromDispatcher(List(), dispatcher))

            context.watch(dispatcher)

            Behaviors.receiveSignal {
                case(_, Terminated(_)) => 
                    Behaviors.stopped
            }
        }
}

object AkkaRun {
    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message]): SimulationSnapshot = {
        println("Simulation starts!")

        val actorSystem = ActorSystem(SimExperiment(totalTurn, actors, staged, messages), "SimSystem")
        Await.ready(actorSystem.whenTerminated, 10.days)

        println("Simulation ends!")
        Actor.reset
        SimulationSnapshot(Dispatcher.finalStates.toList, Dispatcher.msgBuffer.toList)
    }
}