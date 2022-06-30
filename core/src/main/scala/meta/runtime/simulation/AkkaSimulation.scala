package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import SimRuntime._
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Await
import scala.concurrent.duration._

import akka.NotUsed
import akka.actor.typed.{ActorRef, ActorSystem, Terminated, PostStop, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout

import akka.cluster.typed.Cluster
import com.typesafe.config.ConfigFactory
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}

object Dispatcher {
    sealed trait DispatcherEvent extends NoSerializationVerificationNeeded
    final case object InitializeSims extends DispatcherEvent
    final case object RoundStart extends DispatcherEvent
    final case class RoundEnd(messages: List[Message], elapsedTime: Int) extends DispatcherEvent 
}

class Dispatcher {
    import Dispatcher._
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0

    private var agentsAddMessages: Set[ActorRef[SimAgent.AddMessages]] = Set()
    private var agentsStop: Set[ActorRef[SimAgent.Stop]] = Set()

    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    def apply(maxAgents: Int, maxTurn: Int, messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        totalAgents = maxAgents
        totalTurn = maxTurn
        currentTurn = 0
        msgBuffer.clear()
        msgBuffer.appendAll(messages)

        var serviceKey1Complete: Boolean = false
        var serviceKey2Complete: Boolean = false

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgent.AgentServiceKey1.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Recorded all agents that need to send messages!")
                    agentsAddMessages = agents
                    serviceKey1Complete = true
                    if (serviceKey2Complete){
                        RoundStart
                    } else {
                        InitializeSims
                    }
                } else{ 
                    InitializeSims
                } 
            case SimAgent.AgentServiceKey2.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Recorded all agents that need to be stopped!")
                    agentsStop = agents
                    serviceKey2Complete = true
                    if (serviceKey1Complete){
                        RoundStart
                    } else {
                        InitializeSims
                    }
                    RoundStart
                } else{ 
                    InitializeSims
                } 
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey1, subscriptionAdapter)
        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey2, subscriptionAdapter)
        dispatcher()
    }

    def dispatcher(): Behavior[DispatcherEvent] = 
        Behaviors.receive[DispatcherEvent] { (ctx, message) => 
            message match { 
                case InitializeSims =>
                    dispatcher()

                case RoundStart => {
                    ctx.log.warn(f"Round ${currentTurn}")
                    ctx.spawnAnonymous(
                        Aggregator[SimAgent.MessagesAdded, RoundEnd](
                            sendRequests = { replyTo =>
                                agentsAddMessages.map(a => {
                                    a ! SimAgent.AddMessages(msgBuffer.toList, replyTo)
                                })
                            },
                            expectedReplies = totalAgents,
                            ctx.self,
                            aggregateReplies = replies => {
                                val ans = replies.foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b.messages), if (a._2 > b.elapsedTime) a._2 else b.elapsedTime))
                                RoundEnd(ans._1, ans._2)
                            },
                            timeout=10.seconds))
                    dispatcher()
                }

                case RoundEnd(messages: List[Message], elapsedTime: Int) =>
                    // Add new agents to the system 
                    val newAgents = SimRuntime.newActors.map(a => 
                        ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))
                    totalAgents += newAgents.size
                    SimExperiment.totalAgentsInPartition += newAgents.size
                    ctx.log.debug(f"Total agents in the system ${totalAgents}")

                    SimRuntime.newActors.clear()

                    if (currentTurn + elapsedTime >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.debug(f"Simulation completes! Stop the dispatcher")
                            AkkaRun.lastWords = messages
                            agentsStop.foreach(a => a ! SimAgent.Stop())
                        }
                    } else {
                        currentTurn += elapsedTime
                        msgBuffer.clear()
                        msgBuffer.appendAll(messages)
                        if (newAgents.size == 0) {
                            ctx.self ! RoundStart
                        }
                        dispatcher()
                    }
            }
        }
}

object SimAgent {
    val AgentServiceKey1 = ServiceKey[AddMessages]("SimAgent")
    val AgentServiceKey2 = ServiceKey[Stop]("StopAgent")

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "addMessages"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "messagesAdded"),
        new JsonSubTypes.Type(value = classOf[Stop], name = "stop")))
    sealed trait AgentEvent extends JsonSerializable
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int) extends AgentEvent
    final case class Stop() extends AgentEvent
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = 
        Behaviors.setup { ctx =>
            // ctx.log.debug("Register agent with receptionist")
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey1, ctx.self)
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey2, ctx.self)
            this.sim = sim
            simAgent()
        }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case AddMessages(messages, replyTo) => 
                    val agentAPI = sim.run(messages.filter(m => sim.proxyIds.contains(m.receiverId)))
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    replyTo ! MessagesAdded(sentMessages, elapsedTime)
                    Behaviors.same
                case Stop() =>
                    Behaviors.stopped
            }
        }.receiveSignal {
            case (ctx, PostStop) => 
                ctx.log.debug(f"Stop agent ${sim.id}")
                AkkaRun.addStoppedAgent(sim)
                Behaviors.stopped
        }
}

object SimExperiment {
    sealed trait Command
    final case class SpawnDispatcher(totalAgents: Int, totalTurn: Int, messages: List[Message]) extends Command
    final case class SpawnSim(actor: Actor) extends Command
    final case class DispatcherStopped() extends Command
    final case class ActorStopped() extends Command

    var cluster: Cluster = null
    var totalAgents: Int = 0
    var totalAgentsInPartition: Int = 0
    var terminatedAgents: Int = 0

    def apply(totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            totalAgentsInPartition = actors.size

            if (cluster.selfMember.hasRole("Sims")) {
                actors.foreach(a => ctx.self ! SpawnSim(a))
            } 
            
            if (cluster.selfMember.hasRole("Dispatcher")) {
                actors.foreach(a => ctx.self ! SpawnSim(a))
                ctx.self ! SpawnDispatcher(totalAgents, totalTurn, messages)
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                totalAgents = actors.size
                actors.foreach(a => ctx.self ! SpawnSim(a))
                ctx.self ! SpawnDispatcher(totalAgents, totalTurn, messages)
            }
            waitTillFinish
        }

    def waitTillFinish(): Behavior[Command] = {
        Behaviors.receive { (ctx, message) => 
            message match {
                case SpawnDispatcher(totalAgents, totalTurn, messages) => 
                    val dispatcher = ctx.spawn((new Dispatcher).apply(totalAgents, totalTurn, messages), "dispatcher")
                    ctx.watchWith(dispatcher, DispatcherStopped())
                    Behaviors.same

                case SpawnSim(a) =>
                    val sim = ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")
                    ctx.watchWith(sim, ActorStopped())
                    Behaviors.same
                
                case DispatcherStopped() =>
                    Behaviors.same
                
                case ActorStopped() =>
                    terminatedAgents += 1
                    ctx.log.debug("Actor stop! Total terminated agents are " + terminatedAgents + " total agents in partition is " + totalAgentsInPartition)

                    if (terminatedAgents >= totalAgentsInPartition){
                        Behaviors.stopped {() =>
                            ctx.system.terminate()
                        }
                    } else {
                        Behaviors.same
                    }
            }

        }
    }
}

object AkkaRun {

    private val stoppedAgents: ListBuffer[Actor] = ListBuffer[Actor]()
    var lastWords: List[Message] = List()

    def addStoppedAgent(agent: Actor): Unit = synchronized {
        stoppedAgents.append(agent)
    }

    def initialize(): Unit = {
        stoppedAgents.clear()
        lastWords = List()
    }

    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message], 
            role: String= "Standalone", port: Int = 25251): SimulationSnapshot = {
        def startup(role: String, port: Int): Unit ={
            val config = ConfigFactory.parseString(s"""
                akka.remote.artery.canonical.port=$port
                akka.cluster.roles = [$role]
                """).withFallback(ConfigFactory.load("application"))
            val actorSystem = ActorSystem(SimExperiment(totalTurn, actors, staged, messages), "SimsCluster", config)
            Await.ready(actorSystem.whenTerminated, 10.days)
        }
        initialize()    
        println("Simulation starts!")
        startup(role, port)
        println("Simulation ends!")
        // Actor.reset 
        SimulationSnapshot(stoppedAgents.toList, lastWords)
    }
}