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

object Dispatcher {
    sealed trait DispatcherEvent
    final case object InitializeSims extends DispatcherEvent with CborSerializable
    final case object RoundStart extends DispatcherEvent with CborSerializable
    final case class RoundEnd(messages: List[Message], elapsedTime: Int) extends DispatcherEvent with CborSerializable
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0

    private var agentsAddMessages: Set[ActorRef[SimAgent.AddMessages]] = Set()

    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    def apply(maxAgents: Int, maxTurn: Int, messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        totalAgents = maxAgents
        totalTurn = maxTurn
        currentTurn = 0
        msgBuffer.clear()
        msgBuffer.appendAll(messages)

        val subscriptionAdapter = ctx.messageAdapter[Receptionist.Listing] {
            case SimAgent.AgentServiceKey1.Listing(agents) =>
                if (agents.size == totalAgents) {
                    ctx.log.debug("Sims are initialized!")
                    agentsAddMessages = agents
                    RoundStart
                } else{ 
                    InitializeSims
                } 
        } 

        ctx.system.receptionist ! Receptionist.Subscribe(SimAgent.AgentServiceKey1, subscriptionAdapter)
        dispatcher()
    }

    def dispatcher(): Behavior[DispatcherEvent] = 
        Behaviors.receive[DispatcherEvent] { (ctx, message) => 
            message match { 
                case InitializeSims =>
                    dispatcher()

                case RoundStart => {
                    ctx.log.info(f"Round ${currentTurn} starts ${totalAgents}")
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
                    ctx.log.debug(f"Total agents in the system ${totalAgents}")

                    SimRuntime.newActors.clear()

                    if (currentTurn + elapsedTime >= totalTurn){
                        Behaviors.stopped {() => 
                            ctx.log.info(f"Simulation completes! Stop the dispatcher")
                            AkkaRun.lastWords = messages
                            ctx.system.terminate()
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

    sealed trait AgentEvent
    final case class AddMessages(messages: List[Message], replyTo: ActorRef[MessagesAdded]) extends AgentEvent with CborSerializable
    final case class MessagesAdded(messages: List[Message], elapsedTime: Int) extends CborSerializable
}

class SimAgent {
    import SimAgent._

    private var sim: Actor = null

    def apply(sim: Actor): Behavior[AgentEvent] = 
        Behaviors.setup { ctx =>
            ctx.log.debug("Register agent with receptionist")
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey1, ctx.self)
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
                    
            }
        }.receiveSignal {
            case (ctx, PostStop) => 
                ctx.log.debug(f"Stop agent ${sim.id}")
                AkkaRun.stoppedAgents.append(sim)
                Behaviors.stopped
        }
}

object SimExperiment {
    def apply(totalTurn: Int, actors: List[Actor], staged: Boolean=false, messages: List[Message]): Behavior[NotUsed] = 
        Behaviors.setup { ctx => 
            val cluster = Cluster(ctx.system)

            if (cluster.selfMember.hasRole("Sims")) {
                actors.foreach(a => {
                    ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")
                })
            }

            if (cluster.selfMember.hasRole("Dispatcher")) {
                ctx.spawn(Dispatcher(actors.size, totalTurn, messages), "dispatcher")
            }

            if (cluster.selfMember.hasRole("Standalone")) {
                ctx.spawn(Dispatcher(actors.size, totalTurn, messages), "dispatcher")
                actors.foreach(a => ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))
            }

            Behaviors.empty
        }
}

object AkkaRun {

    val stoppedAgents: ListBuffer[Actor] = ListBuffer[Actor]()
    var lastWords: List[Message] = List()

    def initialize(): Unit = {
        stoppedAgents.clear()
        lastWords = List()
    }

    def apply(actors: List[Actor], totalTurn: Int, staged: Boolean=false, messages: List[Message], 
            role: String= "Standalone", port: Int = 0): SimulationSnapshot = {
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