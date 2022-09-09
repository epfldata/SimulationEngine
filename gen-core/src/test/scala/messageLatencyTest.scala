package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class MessageLatencyTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Message with latency 5" should "arrive at time units after 5 in Base" in {
        val agents = generated.core.test.messageLatency.InitData()
        val c = new SimulationConfig(agents, 10)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        val receivers = r.sims.filter(a => a.isInstanceOf[generated.core.test.messageLatency.ReceiverForBound])
        assert(receivers.size > 0)
        receivers.foreach(a => assert(a.asInstanceOf[generated.core.test.messageLatency.ReceiverForBound].counter == 15))
    }

    "Message with latency 5" should "arrive at time units after 5 in Akka" in {
        val agents = generated.core.test.messageLatency.InitData()
        val c = new SimulationConfig(agents, 10)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        val receivers = r.sims.filter(a => a.isInstanceOf[generated.core.test.messageLatency.ReceiverForBound])
        assert(receivers.size > 0)
        receivers.foreach(a => assert(a.asInstanceOf[generated.core.test.messageLatency.ReceiverForBound].counter == 15))
    }

    "Message with latency 5" should "arrive at time units after 5 in DriverWorkerAkka" in {
        val agents = generated.core.test.messageLatency.InitData()
        val c = new SimulationConfig(agents, 10)
        val r = StartSimulation[AkkaDriverWorker.type](c)
        val receivers = r.sims.filter(a => a.isInstanceOf[generated.core.test.messageLatency.ReceiverForBound])
        assert(receivers.size > 0)
        receivers.foreach(a => assert(a.asInstanceOf[generated.core.test.messageLatency.ReceiverForBound].counter == 15))
    }
}
