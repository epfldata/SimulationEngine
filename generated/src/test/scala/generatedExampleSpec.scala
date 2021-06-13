package generated.example.test

import meta.API._

class generatedExamples extends org.scalatest.FlatSpec {
    "Compiled epidemic example with base messaging layer" should "run" in {
        val agents = generated.example.epidemic.InitData(500)

        val c = new SimulationConfig(agents, totalTurn = 100)

        // 10 containers
        val containerConfig = c.staticPartition(10)
        // StartSimulation[AkkaMessagingLayer.type](containerConfig)
        StartSimulation[BaseMessagingLayer.type](containerConfig)
    }

    "Compiled game of life example with Akka messaging layer" should "run" in {
        val agents = generated.example.gameOfLife.InitData(100, 10)

        val c = new SimulationConfig(agents, totalTurn = 10)

        StartSimulation[AkkaMessagingLayer.type](c)
    }

    "Compiled wator example with 10 containers over Akka" should "run" in {
        val agents = generated.example.cellularAutomata.wator.InitData(100, 10)

        val c = new SimulationConfig(agents, totalTurn = 10)
        c.staticPartition(10)

        StartSimulation[AkkaMessagingLayer.type](c)
    }

    "Compiled segregation example with 10 containers over base" should "run" in {
        val agents = generated.example.segregation.InitData(100, 10, 700)

        val c = new SimulationConfig(agents, totalTurn = 10)
        c.staticPartition(10)

        StartSimulation[BaseMessagingLayer.type](c)
    }

    "Compiled wealth distribution example over Akka" should "run" in {
        val agents = generated.example.sugarscape.wealthDistribution.InitData(100, 10)

        val c = new SimulationConfig(agents, totalTurn = 10)
        c.staticPartition(10)

        StartSimulation[AkkaMessagingLayer.type](c)
    }
}
