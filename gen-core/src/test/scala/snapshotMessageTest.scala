package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import scala.util.Random

class SnapshotMessageTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "The snapshot" should "capture messages not processed in base messaging layer" in {
        val agents = generated.core.test.snapshot.InitData()
        val c = new SimulationConfig(agents, 1)
        val snapshot1 = StartSimulation[BaseMessagingLayer.type](c)
        // After 1 epoch, we have 7 messages, sent by sender
        assert(snapshot1.messages.length == 7)
        println(snapshot1.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))
        // After 2 epochs, we have 14 messages. 7 replies from receiver and 7 from the sender. 
        val snapshot2 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(snapshot1.sims, 1, messages = snapshot1.messages))
        assert(snapshot2.messages.length == 14)
        println(snapshot2.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))
        // In the later epochs, the message load of the system remains at 14 messages, as previously
        val snapshot3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(snapshot2.sims, 10, messages = snapshot2.messages))
        assert(snapshot3.messages.length == 14) 
        println(snapshot3.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))
    }

    "The snapshot" should "capture messages not processed in Akka messaging layer" in {
        val agents = generated.core.test.snapshot.InitData()
        val c = new SimulationConfig(agents, 1)
        val snapshot1 = StartSimulation[AkkaMessagingLayer.type](c)
        // After 1 epoch, we have 7 messages, sent by sender
        assert(snapshot1.messages.length == 7)
        assert(snapshot1.sims.length == 8)

        // After 2 epochs, we have 14 messages. 7 replies from receiver and 7 from the sender.         
        val snapshot2 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(snapshot1.sims, 1, messages = snapshot1.messages))
        assert(snapshot2.messages.length == 14)
        assert(snapshot2.sims.length == 8)

        // In the later epochs, the message load of the system remains at 14 messages, as previously
        val snapshot3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(snapshot2.sims, 10, messages = snapshot2.messages))
        assert(snapshot3.messages.length == 14)
        assert(snapshot3.sims.length == 8)
    }

    "The snapshot" should "capture messages not processed in Spark messaging layer" in {
        val agents = generated.core.test.snapshot.InitData()
        val c = new SimulationConfig(agents, 1)
        val snapshot1 = StartSimulation[SparkMessagingLayer.type](c)
        // After 1 epoch, we have 7 messages, sent by sender
        assert(snapshot1.messages.length == 7)
        assert(snapshot1.sims.length == 8)

        // // After 2 epochs, we have 14 messages. 7 replies from receiver and 7 from the sender.         
        val snapshot2 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(snapshot1.sims, 1, messages = snapshot1.messages))
        assert(snapshot2.messages.length == 14)
        assert(snapshot2.sims.length == 8)

        // In the later epochs, the message load of the system remains at 14 messages, as previously
        val snapshot3 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(snapshot2.sims, 10, messages = snapshot2.messages))
        assert(snapshot3.messages.length == 14)
        assert(snapshot3.sims.length == 8)
    }

    "The snapshot" should "capture messages not processed in Spark Range messaging layer" in {
        val agents = generated.core.test.snapshot.InitData()
        val sim = new meta.runtime.simulation.SparkRun2(agents, 1, List())
        val snapshot1 = sim.run()
        assert(snapshot1.messages.length == 7)
        assert(snapshot1.sims.length == 8)
        println(snapshot1.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))

        // // After 2 epochs, we have 14 messages. 7 replies from receiver and 7 from the sender.   
        val sim2 = new meta.runtime.simulation.SparkRun2(snapshot1.sims, 1, messages = snapshot1.messages)      
        val snapshot2 = sim2.run()
        assert(snapshot2.messages.length == 14)
        assert(snapshot2.sims.length == 8)
        println(snapshot2.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))

        // In the later epochs, the message load of the system remains at 14 messages, as previously
        val sim3 = new meta.runtime.simulation.SparkRun2(snapshot2.sims, 10, messages = snapshot2.messages)      
        val snapshot3 = sim3.run()
        assert(snapshot3.messages.length == 14)
        assert(snapshot3.sims.length == 8)
        println(snapshot3.sims.filter(s => s.isInstanceOf[generated.core.test.snapshot.Receiver]).map(i => i.asInstanceOf[generated.core.test.snapshot.Receiver].counter))
    }
}