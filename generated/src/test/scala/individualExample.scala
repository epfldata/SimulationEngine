package generated.example.test

import meta.API._

object gameOfLifeTest {

  def main(args: Array[String]): Unit = {
    val agents = generated.example.gameOfLife.InitData(100, 10)

    val c = new SimulationConfig(agents, totalTurn = 10)

    StartSimulation[AkkaMessagingLayer.type](c)
    // StartSimulation[BaseMessagingLayer.type](c)
  }
}


object epidemicTest extends App {

  val agents = generated.example.epidemic.InitData(500)

  val c = new SimulationConfig(agents, totalTurn = 100)

  // 10 containers
  val containerConfig = c.staticPartition(10)
  // StartSimulation[AkkaMessagingLayer.type](containerConfig)
  StartSimulation[BaseMessagingLayer.type](containerConfig)
}

object watorTest {

  def main(args: Array[String]): Unit = {
    val width: Int = 100
    val height: Int = args(0).toInt
    val mode: String = args(1)

    val agents = generated.example.cellularAutomata.wator.InitData(width, height)

    val c = new SimulationConfig(agents, totalTurn = args(2).toInt)

    mode match {
      case "Akka" => 
        StartSimulation[AkkaMessagingLayer.type](c)
      case "AkkaContainer" => 
        val containerConf = c.staticPartition(args(3).toInt)
        StartSimulation[AkkaMessagingLayer.type](containerConf)
      case "Base" => 
        StartSimulation[BaseMessagingLayer.type](c)
      case "BaseContainer" => 
        val containerConf = c.staticPartition(args(3).toInt)
        StartSimulation[BaseMessagingLayer.type](containerConf)
    }
  }
}

object wealthDistTest extends App {

  val agents = generated.example.sugarscape.wealthDistribution.InitData(50,50)

  val c = new SimulationConfig(agents, totalTurn = 100)

  // 10 containers
//   StartSimulation[AkkaMessagingLayer.type](c)
  StartSimulation[BaseMessagingLayer.type](c)
}

object segregationTest extends App {

  val agents = generated.example.segregation.InitData(5,5, 10)

  val c = new SimulationConfig(agents, totalTurn = 10)

  // 10 containers
//   StartSimulation[AkkaMessagingLayer.type](c)
  StartSimulation[BaseMessagingLayer.type](c)
}