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

import com.typesafe.config.ConfigFactory
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}

object Dispatcher {
    sealed trait DispatcherEvent
    final case object InitializeSims extends DispatcherEvent
    final case object RoundStart extends DispatcherEvent
    final case class RoundEnd(messages: List[Message], elapsedTime: Int) extends DispatcherEvent
    
    private var totalAgents: Int = 0
    private var totalTurn: Int = 0
    private var currentTurn: Int = 0
    private var proxyMap: Map[AgentId, AgentId] = Map()

    private var agentsAddMessages: Set[ActorRef[SimAgent.AddMessages]] = Set()

    val msgBuffer: ListBuffer[Message] = ListBuffer[Message]()

    def apply(maxAgents: Int, maxTurn: Int, pMap: Map[AgentId, AgentId], messages: List[Message]): Behavior[DispatcherEvent] = Behaviors.setup {ctx =>
        totalAgents = maxAgents
        totalTurn = maxTurn
        proxyMap = pMap
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
                    ctx.log.debug(f"Round ${currentTurn} starts")
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
                    // ctx.log.debug(f"Total agents in the system ${totalAgents}")

                    // val newProxyMap = SimRuntime.newActors
                    //     .map(a => (a.proxyIds, a.id))
                    //     .flatMap(p => p._1.map(i => (i, p._2))).toMap
                    // proxyMap = proxyMap ++ newProxyMap
                    SimRuntime.newActors.clear()

                    if (currentTurn + elapsedTime >= totalTurn){
                        Behaviors.stopped {() => {
                            ctx.log.debug(f"Simulation completes! Stop the dispatcher")
                            AkkaRun.lastWords = messages
                        }}
                    } else {
                        currentTurn += elapsedTime
                        msgBuffer.clear()
                        msgBuffer.appendAll(messages)
                        ctx.self ! RoundStart
                        dispatcher()
                    }
            }
        }

//   private def dispatcher(): Behavior[DispatcherEvent] =
//     Behaviors.receive { (ctx, message) =>
//         message match {
//             case Round => 
//                 implicit val timeout: Timeout = 100000.seconds
//                 val sentMessages = msgBuffer.toList
//                 msgBuffer.clear()
//                 agentsAddMessages.foreach(a => {
//                     ctx.ask(a, SimAgent.AddMessages(sentMessages, _)) {
//                         case Success(x) => CollectMessages(x.messages, x.elapsedTime)
//                         case Failure(e) => throw new Exception("Timed out when adding messages!")
//                     }
//                 })

//                 val nextTurn: Int = currentTurn + proposedTime
//                 ctx.log.debug(f"Next turn value is ${nextTurn} Message len ${msgBuffer.size}")

//                 if (nextTurn >= totalTurn) {
//                     agentsStop.foreach(a => {
//                         ctx.ask(a, SimAgent.Stop(_)) {
//                             case Success(x) => FinalState(x.agent)
//                             case Failure(e) => throw new Exception("Timed out while stopping agents")
//                         }
//                     })
//                     Behaviors.stopped
//                 } else {
//                     currentTurn = nextTurn
//                     ctx.log.info("Turn {}", currentTurn)
//                     ctx.self ! Round
//                     dispatcher()
//                 }

//             case CollectMessages(messages: List[Message], elapsedTime: Int) =>
//                 ctx.log.info(f"Collect messages! ${messages.size}")
//                 msgBuffer.appendAll(messages)
//                 ctx.log.info(f"Total collected messages ${msgBuffer.size}")
//                 // ProposedTime advances the timer of the system by the max of the containers
//                 if (elapsedTime > proposedTime) {
//                     proposedTime = elapsedTime
//                 }
//                 dispatcher()

//             case InitializeSims => 
//                 dispatcher()

//             // case CollectMessages(messages: List[Message], elapsedTime: Int, replyTo: ActorRef[SimAgent.AgentEvent]) => 
//             //     val aggAgents = agentCounter+1
//             //     if (elapsedTime > proposedTime){
//             //         proposedTime = elapsedTime
//             //     }
//             //     msgBuffer.appendAll(messages)

//             //     if (aggAgents == totalAgents) {
//             //         val nextTurn = currentTurn + proposedTime
//             //         proposedTime = 1
//             //         val t = System.currentTimeMillis()
//             //         ctx.log.info("Turn {} Total time: {} ms", currentTurn, t - currentTime)

//             //         if (nextTurn >= totalTurn) {
//             //             agentsStop.foreach(a => {
//             //                 a ! SimAgent.Stop(ctx.self)
//             //             })
//             //         } else {
//             //             agentsAddMessages.foreach(a => {
//             //                 a ! SimAgent.AddMessages(msgBuffer.toList, ctx.self)
//             //             })
//             //             msgBuffer.clear()

//             //             val newAgents = SimRuntime.newActors.map(a => {
//             //                     ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}")})
//             //             totalAgents += newAgents.size
//             //             val newProxyMap = SimRuntime.newActors
//             //                 .map(a => (a.proxyIds, a.id))
//             //                 .flatMap(p => p._1.map(i => (i, p._2))).toMap
//             //             proxyMap = proxyMap ++ newProxyMap
//             //             SimRuntime.newActors.clear()
                        
//             //             newAgents.foreach(a => a ! SimAgent.AddMessages(List(), ctx.self))
//             //         } 
//             //         dispatcher(0, nextTurn, t)
//             //     } else {
//             //         dispatcher(aggAgents, 
//             //                 currentTurn, 
//             //                 currentTime)
//             //     }

//             case FinalState(actor: Actor) =>
//                 finalStates.append(actor)
//                 Behaviors.same
//         }
//     }
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
            ctx.log.info("Register agent with receptionist")
            ctx.system.receptionist ! Receptionist.Register(AgentServiceKey1, ctx.self)
            this.sim = sim
            simAgent()
        }

    private def simAgent(): Behavior[AgentEvent] =
        Behaviors.receive[AgentEvent] { (ctx, message) =>
            message match {
                case AddMessages(messages, replyTo) => 
                    val agentAPI = sim.run(messages.filter(_.receiverId == sim.id))
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
            val proxyMap = actors
            .map(a => (a.proxyIds, a.id))
            .flatMap(p => p._1.map(i => (i, p._2))).toMap
            val dispatcher = ctx.spawn(Dispatcher(actors.size, totalTurn, proxyMap, messages), "dispatcher")
            val simAgents = actors.map(a => ctx.spawn((new SimAgent).apply(a), f"simAgent${a.id}"))
            ctx.watch(dispatcher)

            Behaviors.receiveSignal {
                case(_, Terminated(_)) => 
                    simAgents.map(s => ctx.stop(s))
                    Behaviors.stopped 
            }
        }
}

object AkkaRun {

    val stoppedAgents: ListBuffer[Actor] = ListBuffer[Actor]()
    var lastWords: List[Message] = List()

    def initialize(): Unit = {
        stoppedAgents.clear()
        lastWords = List()
    }

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
        initialize()    
        println("Simulation starts!")
        startup("Frontend", 2551)
        println("Simulation ends!")
        Actor.reset 
        SimulationSnapshot(stoppedAgents.toList, lastWords)
    }
}