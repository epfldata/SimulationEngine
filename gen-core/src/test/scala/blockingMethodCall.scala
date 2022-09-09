package generated.core

import meta.API._
import org.scalatest.Suites
import org.scalatest.{DoNotDiscover, FunSuite}

@DoNotDiscover
class RemoteBlockingMtdTest extends FunSuite{
  // Messages are processed randomly, so both lists are possible.
  val possibleReplyValues = Set(List(9, 7, 5, 3, 1), List(8, 6, 4, 2))

  test("Call a blocking method remotely should take time to process"){
    val agents = generated.core.test.blockingMethodCall.InitData()
    val totalRounds: Int = 20
    val c = new SimulationConfig(agents, totalRounds)
    val finalState = StartSimulation[BaseMessagingLayer.type](c)
    val finalAgents = finalState.sims.map(x => x.asInstanceOf[generated.core.test.blockingMethodCall.AgentWithBlockingCall])
    assert(finalAgents.map(_.totalBlockingMtdCalls)==List(10, 0, 0))
    assert(finalAgents(0).blockingReplyValue == List())
    assert(possibleReplyValues.contains(finalAgents(1).blockingReplyValue))
    assert(possibleReplyValues.contains(finalAgents(2).blockingReplyValue))
  }

  test("Call a blocking method remotely should take time to process in Akka"){
    val agents = generated.core.test.blockingMethodCall.InitData()
    val totalRounds: Int = 20
    val c = new SimulationConfig(agents, totalRounds)
    val finalState = StartSimulation[AkkaMessagingLayer.type](c)
    val finalAgents = finalState.sims.map(x => x.asInstanceOf[generated.core.test.blockingMethodCall.AgentWithBlockingCall]).sortBy(_.id)
    assert(finalAgents.map(_.totalBlockingMtdCalls)==List(10, 0, 0))
    assert(finalAgents(0).blockingReplyValue == List())
    assert(possibleReplyValues.contains(finalAgents(1).blockingReplyValue))
    assert(possibleReplyValues.contains(finalAgents(2).blockingReplyValue))
  }

  test("Call a blocking method remotely should take time to process in p2p Akka"){
    val agents = generated.core.test.blockingMethodCall.InitData()
    val totalRounds: Int = 20
    val c = new SimulationConfig(agents, totalRounds)
    val finalState = StartSimulation[AkkaDriverWorker.type](c)
    val finalAgents = finalState.sims.map(x => x.asInstanceOf[generated.core.test.blockingMethodCall.AgentWithBlockingCall]).sortBy(_.id)
    assert(finalAgents.map(_.totalBlockingMtdCalls)==List(10, 0, 0))
    assert(finalAgents(0).blockingReplyValue == List())
    assert(possibleReplyValues.contains(finalAgents(1).blockingReplyValue))
    assert(possibleReplyValues.contains(finalAgents(2).blockingReplyValue))
  }
}

@DoNotDiscover
class LocalBlockingMtdTest extends FunSuite{
  test("Call a blocking method locally should take time to process"){
    val agents = generated.core.test.blockingMethodCallLocal.InitData()
    val finalState = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(agents, 10))
    val finalSim = finalState.sims.head.asInstanceOf[generated.core.test.blockingMethodCallLocal.AgentWithBlockingCallLocal]
    assert(finalSim.totalBlockingMtdCalls==5)
    assert(finalSim.totalNonBlockingMtdCalls==5)
  }

  test("Call a blocking method locally should take time to process in p2p Akka"){
    val agents = generated.core.test.blockingMethodCallLocal.InitData()
    val finalState = StartSimulation[AkkaDriverWorker.type](new SimulationConfig(agents, 10))
    val finalSim = finalState.sims.head.asInstanceOf[generated.core.test.blockingMethodCallLocal.AgentWithBlockingCallLocal]
    assert(finalSim.totalBlockingMtdCalls==5)
    assert(finalSim.totalNonBlockingMtdCalls==5)
  }

  test("Call a blocking method locally should take time to process in Akka"){
    val agents = generated.core.test.blockingMethodCallLocal.InitData()
    val finalState = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(agents, 10))
    val finalSim = finalState.sims.head.asInstanceOf[generated.core.test.blockingMethodCallLocal.AgentWithBlockingCallLocal]
    assert(finalSim.totalBlockingMtdCalls==5)
    assert(finalSim.totalNonBlockingMtdCalls==5)
  }
}

// Set blocking method suite to do not discover, 
// since the new inst. with latency does not support blocking RPC
@DoNotDiscover
class BlockingMethodSuite extends Suites (new RemoteBlockingMtdTest, new LocalBlockingMtdTest)