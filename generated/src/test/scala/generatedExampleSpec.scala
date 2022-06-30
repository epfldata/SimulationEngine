package generated.example.test

import meta.API._

class generatedExamples extends org.scalatest.FlatSpec {
    "Compiled epidemic example with Akka containers" should "run" in {
        val agents = generated.example.epidemic.evalNPI.InitData(List(100,100,100), 0.1, false)
        val c = new SimulationConfig(agents, totalTurn = 50)

        // 10 containers
        // val containerConfig = c.staticPartition(3)(BoundedLatency)
        val results = StartSimulation[AkkaMessagingLayer.type](c)
    }

    "Compiled game of life example with base messaging layer" should "run" in {
        val agents = generated.example.gameOfLife.InitData(10, 10, 1)

        val c = new SimulationConfig(agents, 10)
        val containerConfig = c.staticPartition(10)(DirectMethodCall)

        val results = StartSimulation[BaseMessagingLayer.type](containerConfig)
    }

    "Compiled wator example with 10 containers over base" should "run" in {
        val agents = generated.example.cellularAutomata.wator.InitData(10, 10, 1)

        val c = new SimulationConfig(agents, 10)
        val containerConfig = c.staticPartition(10)(VanillaContainer)

        val results = StartSimulation[BaseMessagingLayer.type](containerConfig)
        // val results = StartSimulation[AkkaMessagingLayer.type](containerConfig)
    }
}
