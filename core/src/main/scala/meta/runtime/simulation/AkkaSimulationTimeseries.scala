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

class DispatcherWithReducer[K, T] {
    import Dispatcher._
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0

    private var agentsAddMessages: Set[ActorRef[SimAgentWithMapper.AddMessages]] = Set()

    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    def apply(maxAgents: Int, maxTurn: Int, messages: List[Message], mapper: Actor=>K, reducer: List[K]=> T): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        totalAgents = maxAgents
        totalTurn = maxTurn
        currentTurn = 0
        msgBuffer.clear()
        msgBuffer.appendAll(messages)

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgentWithMapper.AgentServiceKey1.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Sims are initialized!")
                    agentsAddMessages = agents
                    RoundStart
                } else{ 
                    InitializeSims
                } 
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(SimAgentWithMapper.AgentServiceKey1, subscriptionAdapter)
        dispatcher(mapper, reducer)
    }

    def dispatcher(mapper: Actor=>K, reducer: List[K]=>T): Behavior[DispatcherEvent] = 
        Behaviors.receive[DispatcherEvent] { (ctx, message) => 
            message match { 
                case InitializeSims =>
                    dispatcher(mapper, reducer)

                case RoundStart => {
                    ctx.log.info(f"Round ${currentTurn}")
                    ctx.spawnAnonymous(
                        Aggregator[SimAgentWithMapper.MessagesAdded, RoundEnd](
                            sendRequests = { replyTo =>
                                agentsAddMessages.map(a => {
                                    a ! SimAgentWithMapper.AddMessages(msgBuffer.toList, replyTo)
                                })
                            },
                            expectedReplies = totalAgents,
                            ctx.self,
                            aggregateReplies = replies => {
                                var passedRounds: Int = 1
                                val messages: ListBuffer[Message] = new ListBuffer[Message]()
                                val mapperResults: ListBuffer[K] = new ListBuffer[K]()
                                msgBuffer.clear()
                                for (r <- replies) {
                                    msgBuffer.appendAll(r.messages)
                                    mapperResults.append(r.mapperResult.asInstanceOf[K])
                                    if (r.elapsedTime > passedRounds){
                                        passedRounds = r.elapsedTime
                                    }
                                }
                                SimExperimentWithTimeseries.recording.append(reducer(mapperResults.toList))
                                RoundEnd(passedRounds)
                            },
                            timeout=10.seconds))
                    dispatcher(mapper, reducer)
                }

                case RoundEnd(elapsedTime: Int) =>
                    // Add new agents to the system 
                    val newAgents = SimRuntime.newActors.map(a => 
                        ctx.spawn((new SimAgentWithMapper).apply(a, mapper), f"simAgent${a.id}"))
                    totalAgents += newAgents.size
                    ctx.log.debug(f"Total agents in the system ${totalAgents}")

                    SimRuntime.newActors.clear()

                    if (currentTurn + elapsedTime >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.debug(f"Simulation completes! Stop the dispatcher")
                            AkkaRun.lastWords = msgBuffer.toList
                            ctx.system.terminate()
                        }
                    } else {
                        currentTurn += elapsedTime
                        if (newAgents.size == 0) {
                            ctx.self ! RoundStart
                        }
                        dispatcher(mapper, reducer)
                    }
            }
        }
}

object SimAgentWithMapper {
    val AgentServiceKey1 = ServiceKey[AddMessages]("SimAgent")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "addMessages"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "messagesAdded")))
    sealed trait AgentEvent extends JsonSerializable
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int, mapperResult: Any) extends AgentEvent
}

class SimAgentWithMapper[K] {
    import SimAgentWithMapper._

    private var sim: Actor = null

    def apply(sim: Actor, mapper: Actor=>K): Behavior[AgentEvent] = 
        Behaviors.setup { ctx =>
            ctx.log.debug("Register agent with receptionist")
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey1, ctx.self)
            this.sim = sim
            simAgent(mapper)
        }

    private def simAgent(mapper: Actor=>K): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case AddMessages(messages, replyTo) => 
                    val agentAPI = sim.runAndEval[K](messages.filter(m => sim.proxyIds.contains(m.receiverId)), mapper)
                    val sentMessages = agentAPI._1._1
                    val elapsedTime = agentAPI._1._2
                    replyTo ! MessagesAdded(sentMessages, elapsedTime, agentAPI._2)
                    Behaviors.same
            }
        }.receiveSignal {
            case (ctx, PostStop) => 
                ctx.log.debug(f"Stop agent ${sim.id}")
                AkkaTimeseriesRun.addStoppedAgent(sim)
                Behaviors.stopped
        }
}

object SimExperimentWithTimeseries {
    val recording: ListBuffer[Any] = new ListBuffer[Any]()

    def apply[K, T](totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message], mapper: Actor=>K, reducer: List[K]=>T): Behavior[NotUsed] = 
        Behaviors.setup { ctx => 
            val cluster = Cluster(ctx.system)

            if (cluster.selfMember.hasRole("Sims")) {
                actors.foreach(a => {
                    ctx.spawn((new SimAgentWithMapper[K]).apply(a, mapper), f"simAgent${a.id}")
                })
            }

            if (cluster.selfMember.hasRole("Dispatcher")) {
                ctx.spawn((new DispatcherWithReducer).apply(actors.size, totalTurn, messages, mapper, reducer), "dispatcher")
            }

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.spawn((new DispatcherWithReducer).apply(actors.size, totalTurn, messages, mapper, reducer), "dispatcher")
                actors.foreach(a => ctx.spawn((new SimAgentWithMapper[K]).apply(a, mapper), f"simAgent${a.id}"))
            }

            Behaviors.empty
        }
}

object AkkaTimeseriesRun {
    private val stoppedAgents: ListBuffer[Actor] = ListBuffer[Actor]()
    var lastWords: List[Message] = List()

    def addStoppedAgent(agent: Actor): Unit = synchronized {
        stoppedAgents.append(agent)
    }
}

class AkkaTimeseriesRun[K, T] {
    import AkkaTimeseriesRun._

    def initialize(): Unit = {
        stoppedAgents.clear()
        lastWords = List()
    }

    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message], role: String= "Standalone", port: Int = 25251, mapper: Actor=>K, reducer: List[K]=>T): List[T] = {
        def startup(role: String, port: Int): Unit ={
            val config = ConfigFactory.parseString(s"""
                akka.remote.artery.canonical.port=$port
                akka.cluster.roles = [$role]
                """).withFallback(ConfigFactory.load("application"))
            val actorSystem = ActorSystem(SimExperimentWithTimeseries(totalTurn, actors, staged, messages, mapper, reducer), "SimsCluster", config)
            Await.ready(actorSystem.whenTerminated, 10.days)
        }
        initialize()
        println("Simulation starts!")
        startup(role, port)
        println("Simulation ends!")
        // Actor.reset 
        SimExperimentWithTimeseries.recording.toList.asInstanceOf[List[T]]
    }
}