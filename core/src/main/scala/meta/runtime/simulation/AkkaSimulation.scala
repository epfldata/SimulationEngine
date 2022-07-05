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
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeMessageMap], name = "initializeMessageMap")))
    final case class InitializeMessageMap(agentId: Long, replyTo: ActorRef[SimAgent.AddMessages]) extends DispatcherEvent with JsonSerializable
    final case object InitializeSims extends DispatcherEvent with NoSerializationVerificationNeeded
    final case object RoundStart extends DispatcherEvent with NoSerializationVerificationNeeded
    final case class RoundEnd(elapsedTime: Int) extends DispatcherEvent with NoSerializationVerificationNeeded

    val dispatcherServiceKey = ServiceKey[InitializeMessageMap]("Dispatcher")
}

object MessageMap {
    private val messageMap: MutMap[AgentId, ListBuffer[Message]] = MutMap[AgentId, ListBuffer[Message]]()

    def add(ms: List[Message]): Unit = {
        ms.groupBy(_.receiverId).foreach(i => {
            messageMap.getOrElseUpdate(i._1, ListBuffer()).appendAll(i._2)
        })
    }

    def pop(agentId: Long): List[Message] = {
        messageMap.remove(agentId).getOrElse(List()).toList
    }

    def clear(): Unit = {
        messageMap.clear()
    }

    def lastWords(): List[Message] = {
        messageMap.values.flatten.toList
    }
}

class Dispatcher {
    import Dispatcher._
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0

    private var agentsStop: Set[ActorRef[SimAgent.Stop]] = Set()

    val agentLookup: MutMap[AgentId, ActorRef[SimAgent.AddMessages]] = MutMap[AgentId, ActorRef[SimAgent.AddMessages]]()

    def apply(maxAgents: Int, maxTurn: Int, messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        ctx.system.receptionist ! Receptionist.Register(dispatcherServiceKey, ctx.self)

        totalAgents = maxAgents
        totalTurn = maxTurn
        currentTurn = 0
        MessageMap.clear()
        agentLookup.clear()
        MessageMap.add(messages)

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgent.AgentServiceKey2.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Recorded all agents that need to be stopped!")
                    agentsStop = agents
                    RoundStart
                } else{ 
                    InitializeSims
                } 
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey2, subscriptionAdapter)
        dispatcher()
    }

    def dispatcher(): Behavior[DispatcherEvent] = 
        Behaviors.receive[DispatcherEvent] { (ctx, message) => 
            message match { 
                case InitializeSims =>
                    dispatcher()

                case InitializeMessageMap(id, reply) =>
                    agentLookup += (id -> reply)
                    dispatcher()

                case RoundStart => {
                    ctx.log.warn(f"Round ${currentTurn} Expected replies ${totalAgents}")
                    ctx.spawnAnonymous(
                        Aggregator[SimAgent.MessagesAdded, RoundEnd](
                            sendRequests = { replyTo =>
                                agentLookup.map(a => {
                                    a._2 ! SimAgent.AddMessages(MessageMap.pop(a._1), replyTo)
                                })
                            },
                            expectedReplies = totalAgents,
                            ctx.self,
                            aggregateReplies = replies => {
                                var passedRounds: Int = 1
                                for (r <- replies) {
                                    MessageMap.add(r.messages)

                                    if (r.elapsedTime > passedRounds){
                                        passedRounds = r.elapsedTime
                                    }
                                }
                                RoundEnd(passedRounds)
                            },
                            timeout=10.seconds))
                    dispatcher()
                }

                case RoundEnd(elapsedTime: Int) =>
                    ctx.log.warn(f"Round ${currentTurn}")
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
                            AkkaRun.lastWords = MessageMap.lastWords()
                            agentsStop.foreach(a => a ! SimAgent.Stop())
                        }
                    } else {
                        currentTurn += elapsedTime
                        if (newAgents.size == 0) {
                            ctx.self ! RoundStart
                        }
                        dispatcher()
                    }
            }
        }
}

object SimAgent {
    val AgentServiceKey2 = ServiceKey[Stop]("StopAgent")

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "addToMessageMap"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "messagesAdded"),
        new JsonSubTypes.Type(value = classOf[Stop], name = "stop")))
    sealed trait AgentEvent extends JsonSerializable
    final case class RegisterAgent(replyTo: Set[ActorRef[Dispatcher.InitializeMessageMap]]) extends AgentEvent with NoSerializationVerificationNeeded
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int) extends AgentEvent
    final case class Stop() extends AgentEvent
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
        ctx.system.receptionist ! Receptionist.Register(AgentServiceKey2, ctx.self)
        this.sim = sim

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case Dispatcher.dispatcherServiceKey.Listing(dispatchers) =>
                RegisterAgent(dispatchers)
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(Dispatcher.dispatcherServiceKey, subscriptionAdapter)
        simAgent()
    }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case RegisterAgent(dispatchers) => 
                    dispatchers.foreach(d => {
                        d ! Dispatcher.InitializeMessageMap(sim.id, ctx.self)
                    })
                    Behaviors.same
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
            ctx.log.warn(f"Total agents in the partition ${totalAgentsInPartition} total agents ${totalAgents}")

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
    var lastWords: List[Message] = List[Message]()

    def addStoppedAgent(agent: Actor): Unit = synchronized {
        stoppedAgents.append(agent)
    }

    def initialize(): Unit = {
        stoppedAgents.clear()
        lastWords=List()
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