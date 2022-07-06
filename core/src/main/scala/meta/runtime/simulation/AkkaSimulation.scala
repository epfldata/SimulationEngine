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

import java.util.concurrent.ConcurrentHashMap

import java.util.Collections
import java.util.concurrent.atomic.AtomicInteger
import scala.collection.JavaConversions._

import akka.NotUsed
import akka.actor.typed.{ActorRef, ActorSystem, Terminated, PostStop, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout

import akka.cluster.typed.Cluster
import com.typesafe.config.ConfigFactory
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

object Dispatcher {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeMessageMap], name = "initializeMessageMap")))
    sealed trait DispatcherEvent
    final case class InitializeMessageMap(proxyIds: List[java.lang.Long], replyTo: ActorRef[SimAgent.AddMessages]) extends DispatcherEvent with JsonSerializable
    final case object InitializeSims extends DispatcherEvent with NoSerializationVerificationNeeded
    final case object RoundStart extends DispatcherEvent with NoSerializationVerificationNeeded
    final case class RoundEnd(elapsedTime: Int) extends DispatcherEvent with NoSerializationVerificationNeeded

    val dispatcherServiceKey = ServiceKey[InitializeMessageMap]("Dispatcher")
}

object MessageMap {
    private val messageMap: ConcurrentHashMap[scala.Long, scala.collection.mutable.ListBuffer[Message]] = new ConcurrentHashMap()

    def add(ms: List[Message]): Unit = synchronized {
        ms.groupBy(_.receiverId).foreach(i => {
            messageMap.getOrElseUpdate(i._1, ListBuffer()).appendAll(i._2)
        })
    }

    def pop(agentId: Long): List[Message] = synchronized {
        messageMap.remove(agentId) match {
            case null => List()
            case a: ListBuffer[Message] => a.toList
        }
    }

    def clear(): Unit = synchronized {
        messageMap.clear()
    }

    def lastWords(): List[Message] = synchronized {
        messageMap.values().flatten.toList
    }
}

class Dispatcher {
    import Dispatcher._
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0
    private var registeredAgents: AtomicInteger = new AtomicInteger(0)

    private var agentsStop: Set[ActorRef[SimAgent.Stop]] = Set()
    val agentLookup: ConcurrentHashMap[scala.Long , ActorRef[SimAgent.AddMessages]] = new ConcurrentHashMap()

    def apply(scheduledAgents: Int, maxTurn: Int, messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        ctx.system.receptionist ! Receptionist.Register(dispatcherServiceKey, ctx.self)

        totalAgents = scheduledAgents
        totalTurn = maxTurn
        currentTurn = 0
        MessageMap.clear()
        agentLookup.clear()
        MessageMap.add(messages)

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgent.AgentServiceKey.Listing(agents) =>
                ctx.log.warn(f"Subscription adapter called! Total agents in the service ${agents.size}  Registered agents ${registeredAgents} Total agents ${totalAgents}")

                if (agents.size == totalAgents) {
                    ctx.log.warn(f"Recorded all agents that need to be stopped! Agent Lookup table size ${agentLookup.size}")
                    agentsStop = agents
                } 
                InitializeSims
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey, subscriptionAdapter)
        dispatcher()
    }

    def dispatcher(): Behavior[DispatcherEvent] = 
        Behaviors.receive[DispatcherEvent] { (ctx, message) => 
            message match { 
                case InitializeSims =>
                    dispatcher()

                case InitializeMessageMap(ids, reply) =>
                    ctx.log.warn(f"Add to initialize map, size ${agentLookup.size}, total ${totalAgents}")
                    ids.foreach(id => {
                        agentLookup.putIfAbsent(id, reply)
                    })
                    val currentRegistered = registeredAgents.addAndGet(1)
                    if (currentRegistered == totalAgents) {
                        ctx.self ! RoundStart
                    }
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
                            timeout=1000.seconds))
                    dispatcher()
                }

                case RoundEnd(elapsedTime: Int) =>
                    // todo: Handle adding agents properly 
                    val syncNewActors = Collections.synchronizedList[Actor](SimRuntime.newActors)
                    syncNewActors.map(a => {ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")})
                    totalAgents += syncNewActors.size
                    SimExperiment.totalAgentsInPartition += syncNewActors.size
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
                        if (syncNewActors.size == 0) {
                            ctx.self ! RoundStart
                        }
                        dispatcher()
                    }
            }
        }
}

object SimAgent {
    val AgentServiceKey = ServiceKey[Stop]("StopAgent")

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "addToMessageMap"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "messagesAdded"),
        new JsonSubTypes.Type(value = classOf[Stop], name = "stop")))
    trait AgentEvent 
    final case class RegisterAgent(replyTo: Set[ActorRef[Dispatcher.InitializeMessageMap]]) extends AgentEvent with NoSerializationVerificationNeeded
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent with JsonSerializable
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int) extends AgentEvent with JsonSerializable
    final case class Stop() extends AgentEvent with JsonSerializable
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = Behaviors.setup { ctx =>
        // ctx.log.debug("Register agent with receptionist")
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
                        d ! Dispatcher.InitializeMessageMap(sim.proxyIds.map(_.asInstanceOf[java.lang.Long]).toList, ctx.self)
                    })
                    ctx.system.receptionist ! Receptionist.Register(AgentServiceKey, ctx.self)
                    Behaviors.same
                case AddMessages(messages, replyTo) => 
                    // val agentAPI = sim.run(messages.filter(m => sim.proxyIds.contains(m.receiverId)))
                    val agentAPI = sim.run(messages)
                    val sentMessages = agentAPI._1
                    val elapsedTime = agentAPI._2
                    replyTo ! MessagesAdded(sentMessages, elapsedTime)
                    Behaviors.same
                case Stop() =>
                    ctx.log.debug(f"Stop agent ${sim.id}")
                    Behaviors.stopped
            }
        }
}

object SimExperiment {
    sealed trait Command
    final case class SpawnDispatcher(totalAgents: Int, totalTurn: Int, messages: List[Message]) extends Command
    final case class SpawnSim(actor: Actor) extends Command
    final case class DispatcherStopped() extends Command
    final case class ActorStopped(actor: Actor) extends Command

    var cluster: Cluster = null
    var totalAgents: Int = 0
    var totalAgentsInPartition: Int = 0
    var terminatedAgents: Int = 0

    def apply(totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[Command] = 
        Behaviors.setup { ctx => 
            cluster = Cluster(ctx.system)
            totalAgentsInPartition = actors.size

            val syncActors = Collections.synchronizedList[Actor](actors)

            ctx.log.warn(f"Total agents in the partition ${totalAgentsInPartition}")

            if (cluster.selfMember.hasRole("Sims")) {
                syncActors.foreach(a => ctx.self ! SpawnSim(a))
            } 
            
            if (cluster.selfMember.hasRole("Dispatcher")) {
                syncActors.foreach(a => ctx.self ! SpawnSim(a))
                ctx.self ! SpawnDispatcher(totalAgents, totalTurn, messages)
            } 

            if (cluster.selfMember.hasRole("Standalone")) {
                totalAgents = actors.size
                ctx.log.warn(f"Standalone mode. Total agents ${totalAgents}")
                syncActors.foreach(a => ctx.self ! SpawnSim(a))
                ctx.self ! SpawnDispatcher(totalAgents, totalTurn, messages)
            }
            waitTillFinish(Vector.empty)
        }

    def waitTillFinish(finalAgents: IndexedSeq[Actor]): Behavior[Command] = {
        Behaviors.receive { (ctx, message) => 
            message match {
                case SpawnDispatcher(totalAgents, totalTurn, messages) => 
                    val dispatcher = ctx.spawn((new Dispatcher).apply(totalAgents, totalTurn, messages), "dispatcher")
                    ctx.watchWith(dispatcher, DispatcherStopped())
                    Behaviors.same

                case SpawnSim(a) =>
                    val sim = ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")
                    ctx.watchWith(sim, ActorStopped(a))
                    Behaviors.same
                
                case DispatcherStopped() =>
                    Behaviors.same                    

                case ActorStopped(a) =>
                    val terminatedAgents = finalAgents :+ a
                    ctx.log.debug(f"Add stopped agent! Total terminated agents ${terminatedAgents}")
                    if (terminatedAgents.size == totalAgentsInPartition){
                        AkkaRun.addStoppedAgents(terminatedAgents)
                        Behaviors.stopped {() =>
                            ctx.system.terminate()
                        }
                    } else {
                        waitTillFinish(terminatedAgents)
                    }
            }

        }
    }
}

object AkkaRun {

    private var stoppedAgents = List[Actor]()

    var lastWords: List[Message] = List[Message]()

    def addStoppedAgents(agents: IndexedSeq[Actor]): Unit = {
        stoppedAgents = agents.toList 
    }

    def initialize(): Unit = {
        stoppedAgents=List()
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
        println(f"Simulation starts in ${role} mode!")
        startup(role, port)
        println("Simulation ends!")
        // Actor.reset 
        SimulationSnapshot(stoppedAgents, lastWords)
    }
}