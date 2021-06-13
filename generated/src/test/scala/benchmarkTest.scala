package generated.example.test

import meta.API._

object benchmarkTest {
  def main(args: Array[String]): Unit = {

    val example: String = args(0)
    val totalTurn: Int = args(1).toInt
    val mode: String = args(2)
    val containers: Int = args(3).toInt

    val width: Int = 100
    val margs: Array[String] = args.drop(4)

    val agents = example match {
      case "wator" => generated.example.cellularAutomata.wator.InitData(width, margs(0).toInt)
      case "epidemic" => generated.example.epidemic.InitData(margs(0).toInt)  // first argument
      case "gameOfLife" => generated.example.gameOfLife.InitData(width, margs(0).toInt)
      case "segregation" => generated.example.segregation.InitData(width, margs(0).toInt, margs(1).toInt)
      case "wealthDist" => generated.example.sugarscape.wealthDistribution.InitData(width, margs(0).toInt)
    }
    
    val c = new SimulationConfig(agents, totalTurn)

    mode match {
      case "Akka" => 
        StartSimulation[AkkaMessagingLayer.type](c)
      case "AkkaContainer" => 
        val containerConf = c.staticPartition(containers)
        StartSimulation[AkkaMessagingLayer.type](containerConf)
      case "Base" => 
        StartSimulation[BaseMessagingLayer.type](c)
      case "BaseContainer" => 
        val containerConf = c.staticPartition(containers)
        StartSimulation[BaseMessagingLayer.type](containerConf)
    }
  }  
}