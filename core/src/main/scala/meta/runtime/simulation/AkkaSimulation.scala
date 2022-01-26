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
    final case class SimsInitialized(agents: Set[ActorRef[SimAgent.AddMessages]]) extends DispatcherEvent

    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var proxyMap: Map[AgentId, AgentId] = Map()

    private var agentRefMap: Map[AgentId, ActorRef[SimAgent.AgentEvent]] = Map()
    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    val finalStates: ListBuffer[Actor] = ListBuffer[Actor]()
    var proposedTime: Int = 1

    def apply(maxAgents: Int, maxTurn: Int, pMap: Map[AgentId, AgentId], messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        totalAgents = maxAgents
        totalTurn = maxTurn
        proxyMap = pMap
        finalStates.clear()
        msgBuffer.clear()
        msgBuffer.appendAll(messages)

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgent.AgentServiceKey1.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Sims are initialized!")
                    agents.foreach(a => {
                        a ! SimAgent.AddMessages(msgBuffer.toList, ctx.self)
                    })
                    msgBuffer.clear()
                }
                SimsInitialized(agents)
        } 
        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey1, subscriptionAdapter)

        dispatcher(0, 0, System.currentTimeMillis())
    }

  private def dispatcher(agentCounter: Int, 
                        currentTurn: Int, 
                        currentTime: Long): Behavior[DispatcherEvent] =
    Behaviors.receive { (ctx, message) =>
        message match {
            case SimsInitialized(agents) => 
                Behaviors.same
            case CollectMessages(agentId: Long, messages: List[Message], elapsedTime: Int, replyTo: ActorRef[SimAgent.AgentEvent]) => 
                val aggAgents = agentCounter+1
                if (elapsedTime > proposedTime){
                    proposedTime = elapsedTime
                }
                msgBuffer.appendAll(messages)
                agentRefMap = agentRefMap + (agentId -> replyTo)

                if (aggAgents == totalAgents) {
                    val nextTurn = currentTurn + proposedTime
                    proposedTime = 1
                    val t = System.currentTimeMillis()
                    ctx.log.info("Turn {} Total time: {} ms", currentTurn, t - currentTime)

                    if (nextTurn >= totalTurn) {
                        agentRefMap.foreach(a => {
                            a._2 ! SimAgent.Stop(ctx.self)
                        })
                    } else {
                        agentRefMap.foreach(a => {
                            a._2 ! SimAgent.AddMessages(msgBuffer.toList, ctx.self)
                        })

                        msgBuffer.clear()
                        agentRefMap = Map()

                        val newAgents = SimRuntime.newActors.map(a => {
                                ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")})

                        totalAgents += newAgents.size

                        val newProxyMap = SimRuntime.newActors
                            .map(a => (a.proxyIds, a.id))
                            .flatMap(p => p._1.map(i => (i, p._2))).toMap

                        proxyMap = proxyMap ++ newProxyMap
                        SimRuntime.newActors.clear()
                        
                        newAgents.foreach(a => a ! SimAgent.AddMessages(List(), ctx.self))
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
        Behaviors.receive { (ctx, message) =>
            message match {
                case AddMessages(messages, from) => 
                    val agentAPI = sim.run(messages.filter(_.receiverId == sim.id))
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    from ! Dispatcher.CollectMessages(sim.id, sentMessages, elapsedTime, ctx.self)
                    simAgent()
                case Stop(from) =>
                    from ! Dispatcher.FinalState(sim)
                    Behaviors.stopped
            }
        }
}

object SimExperiment {

    def apply(totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[NotUsed] = 
        Behaviors.setup { ctx => 
            val proxyMap = actors
            .map(a => (a.proxyIds, a.id))
            .flatMap(p => p._1.map(i => (i, p._2))).toMap
            val dispatcher = ctx.spawn(Dispatcher(actors.size, totalTurn, proxyMap, messages), "dispatcher")
            val simAgents = actors.map(a => ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))
            ctx.watch(dispatcher)
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