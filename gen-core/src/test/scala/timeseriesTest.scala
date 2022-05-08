package generated.core.timeseries

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Message}
import meta.API._
import org.scalatest.FlatSpec

class timeseriesTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Stepwise eval materialized from snapshots" should "contain correct value" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)
        // select only the state of counters
        val eval: (List[Actor], List[Message]) => List[Int] = (agents, messages) => agents.map(_.asInstanceOf[generated.core.test.timeseries.CounterSim].state)

        val timeseries = StartSimulation.runAndEval[BaseMessagingLayer.type, List[Int]](c)(eval)

        // println(timeseries)
        // Record each timestamp
        assert(timeseries.length == 5)
        // Initial states
        assert(timeseries(0).forall(_ == 1))
        // Final states 
        assert(timeseries.last.filter(_ == 5).length == 2)
    }

    "Batch run and eval without intermediate materialization" should "contain correct value" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)
        // select only the state of counters
        val eval: (List[Actor], List[Message]) => List[Int] = (agents, messages) => agents.map(_.asInstanceOf[generated.core.test.timeseries.CounterSim].state)

        val timeseries = StartSimulation.runAndEvalOpt[BaseMessagingLayer.type, List[Int]](c)(eval)

        // println(timeseries)
        // Record each time stamp
        assert(timeseries.length == 5)
        // Initial states
        assert(timeseries(0).forall(_ == 1))
        // Final states 
        assert(timeseries.last.filter(_ == 5).length == 2)
    }

    "Push down eval with mapper and reducer" should "contain correct value" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)
        // select only the state of counters
        val mapper: Actor => Int = (agent) => agent.asInstanceOf[generated.core.test.timeseries.CounterSim].state
        val reducer: List[Int] => List[Int] = (x) => x

        val timeseries = 
            StartSimulation.runAndReduce[BaseMessagingLayer.type, Int, List[Int]](c)(mapper, reducer)
        
        println(timeseries)
        // Record each time stamp
        assert(timeseries.length == 5)
        // Initial states
        assert(timeseries(0).forall(_ == 1))
        // Final states 
        assert(timeseries.last.filter(_ == 5).length == 2)
    }

    "Eval without intermediate materialization" should "run faster than stepwise materialization" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 500)
        // select only the state of counters
        val eval: (List[Actor], List[Message]) => List[Int] = (agents, messages) => agents.map(_.asInstanceOf[generated.core.test.timeseries.CounterSim].state)
        
        val time1 = meta.runtime.simulation.util.bench{
            StartSimulation.runAndEval[BaseMessagingLayer.type, List[Int]](c)(eval)
        }
        
        val time2 = meta.runtime.simulation.util.bench{
            StartSimulation.runAndEvalOpt[BaseMessagingLayer.type, List[Int]](c)(eval)
        }

        println("Time for step-wise run and eval is " + time1)
        println("Time for eval without materialization is " + time2)
        // assert(time1 > time2)
    }

    "Push down the mapper" should "run similar to central eval without materialization" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 500)
        // select only the state of counters
        val eval: (List[Actor], List[Message]) => List[Int] = (agents, messages) => agents.map(_.asInstanceOf[generated.core.test.timeseries.CounterSim].state)
        
        val time1 = meta.runtime.simulation.util.bench{
            StartSimulation.runAndEvalOpt[BaseMessagingLayer.type, List[Int]](c)(eval)
        }
        
        val mapper: Actor => Int = (agent) => agent.asInstanceOf[generated.core.test.timeseries.CounterSim].state

        val reducer: List[Int] => List[Int] = (x) => x

        val time2 = meta.runtime.simulation.util.bench{
            StartSimulation.runAndReduce[BaseMessagingLayer.type, Int, List[Int]](c)(mapper, reducer)
        }

        println("Time with a central eval is " + time1)
        println("Time using mapper and reducer is " + time2)
        // assert(time1 < time2)
    }

    "Push down eval with mapper and reducer" should "contain correct value for Akka" in {
        val agents = generated.core.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)
        // select only the state of counters
        val mapper: Actor => Int = (agent) => agent.asInstanceOf[generated.core.test.timeseries.CounterSim].state
        val reducer: List[Int] => List[Int] = (x) => x

        val timeseries =
            StartSimulation.runAndReduce[AkkaMessagingLayer.type, Int, List[Int]](c)(mapper, reducer)
        
        println(timeseries)
        // Record each time stamp
        assert(timeseries.length == 5)
        // Initial states
        assert(timeseries(0).forall(_ == 1))
        // Final states 
        assert(timeseries.last.filter(_ == 5).length == 2)
    }
}