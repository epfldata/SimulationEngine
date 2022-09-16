package simulation.base
package test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class MessageLatencyTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Messages with latency 5" should "arrive at time units 5 and later" in {
        val agents = generated.core.test.messageLatency.InitData()
        val r = new Base(agents, 10).run()
        val receivers = r.sims.filter(a => a.isInstanceOf[generated.core.test.messageLatency.ReceiverForBound])
        assert(receivers.size > 0)
        receivers.foreach(a => assert(a.asInstanceOf[generated.core.test.messageLatency.ReceiverForBound].counter == 15))
    }
}
