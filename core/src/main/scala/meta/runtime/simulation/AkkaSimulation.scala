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

import com.typesafe.config.ConfigFactory
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}

object Dispatcher {
    sealed trait DispatcherEvent
    final case class CollectMessages(agentId: Long, messages: List[Message], elapsedTime: Int, from: ActorRef[SimAgent.AgentEvent]) extends DispatcherEvent
    final case class FinalState(actor: Actor) extends DispatcherEvent
    final case class AddNewAgents() extends DispatcherEvent

    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var proxyMap: Map[AgentId, AgentId] = Map()

    private var agentRefMap: Map[AgentId, ActorRef[SimAgent.AgentEvent]] = Map()
    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    val finalStates: ListBuffer[Actor] = ListBuffer[Actor]()
    var proposedTime: Int = 1

    def apply(maxAgents: Int, maxTurn: Int, pMap: Map[AgentId, AgentId]): Behavior[DispatcherEvent] = {
        totalAgents = maxAgents
        totalTurn = maxTurn
        proxyMap = pMap
        finalStates.clear()
        msgBuffer.clear()
        dispatcher(0, 0, System.currentTimeMillis())
    }

  private def dispatcher(agentCounter: Int, 
                        currentTurn: Int, 
                        currentTime: Long): Behavior[DispatcherEvent] =
    Behaviors.receive { (context, message) =>
        message match {
            case CollectMessages(agentId: Long, messages: List[Message], elapsedTime: Int, replyTo: ActorRef[SimAgent.AgentEvent]) => 
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
                            a._2 ! SimAgent.Stop(context.self)
                        })
                    } else {
                        agentRefMap.foreach(a => {
                            a._2 ! SimAgent.AddMessages(groupedMsgs.getOrElse(a._1, List()), context.self)
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
                        
                        newAgents.foreach(a => a ! SimAgent.AddMessages(List(), context.self))
                    } 
                    dispatcher(0, nextTurn, t)
                } else {
                    dispatcher(aggAgents, 
                            currentTurn, 
                            currentTime)
                }

            case FinalState(actor: Actor) =>
                val aggAgents = agentCounter + 1
                finalStates.append(actor)
                if (aggAgents == totalAgents) {
                    Behaviors.stopped
                } else {
                    dispatcher(aggAgents, currentTurn, currentTime)
                }
        }
    }
}

object SimAgent {
    val AgentServiceKey1 = ServiceKey[AddMessages]("SimAgent")
    val AgentServiceKey2 = ServiceKey[Stop]("SimAgent")

    sealed trait AgentEvent
    final case class AddMessages(messages: List[Message], from: ActorRef[Dispatcher.DispatcherEvent]) extends AgentEvent
    final case class Stop(from: ActorRef[Dispatcher.DispatcherEvent]) extends AgentEvent
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = 
        Behaviors.setup { ctx =>
            ctx.log.info("Register agent with receptionist")
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey1, ctx.self)
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey2, ctx.self)
            this.sim = sim
            simAgent()
        }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive { (context, message) =>
            message match {
                case AddMessages(messages, from) => 
                    val agentAPI = sim.run(messages)
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    from ! Dispatcher.CollectMessages(sim.id, sentMessages, elapsedTime, context.self)
                    simAgent()
                case Stop(from) =>
                    from ! Dispatcher.FinalState(sim)
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

            val dispatcher = context.spawn(Dispatcher(actors.size, totalTurn, proxyMap), "dispatcher")
            
            val simAgents = actors.map(a => context.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))
            
            val simIds = actors.map(a => a.id)
            
            // Buffered messages are dispatched at the initialization
            val collectedMessages = messages.groupBy(_.receiverId)

            (simAgents zip simIds).foreach(x => {
                x._1 ! SimAgent.AddMessages(collectedMessages.getOrElse(x._2, List()), dispatcher)
            })

            context.watch(dispatcher)

            Behaviors.receiveSignal {
                case(_, Terminated(_)) => 
                    Behaviors.stopped
            }
        }
}

object AkkaRun {

    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message]): SimulationSnapshot = {
        def startup(role: String, port: Int): Unit ={
            val config = ConfigFactory
            .parseString(s"""
                akka.remote.artery.canonical.port=$port
                akka.cluster.roles = [$role]
                """)

            val actorSystem = ActorSystem(SimExperiment(totalTurn, actors, staged, messages), "SimsCluster", config)
            Await.ready(actorSystem.whenTerminated, 10.days)
        }

        println("Simulation starts!")
        startup("Frontend", 2551)
        println("Simulation ends!")
        Actor.reset 
        SimulationSnapshot(Dispatcher.finalStates.toList, Dispatcher.msgBuffer.toList)
    }
}