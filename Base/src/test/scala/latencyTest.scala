package simulation.base
package test

import API._
import org.scalatest.FlatSpec

class MessageLatencyTest extends FlatSpec {

    "Messages with different latency" should "arrive in their respective time" in {
        // One sender and one receiver 
        val agents = generated.core.test.messageLatency.InitData()
        val totalRound = 20
        val r = Simulate(agents, totalRound)
        val receiver: generated.core.test.messageLatency.Receiver = r.sims.filter(a => 
            a.isInstanceOf[generated.core.test.messageLatency.Receiver]
            ).head.asInstanceOf[generated.core.test.messageLatency.Receiver]
        assert(receiver.counter1 == totalRound-1)
        assert(receiver.counter2 == totalRound-2)
        assert(receiver.counter3 == totalRound-3)
    }
}
