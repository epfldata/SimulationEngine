package generated.example.test

import meta.API._

object benchmarkTest {
    def main(args: Array[String]): Unit = {

        val example: String = args(0)
        val totalTurn: Int = args(1).toInt
        val mode: String = args(2)
        val containers: Int = args(3).toInt
        val containerOpt: String = args(4)

        val width: Int = 100
        val margs: Array[String] = args.drop(5)

        val agents = example match {
            case "wator" => generated.example.cellularAutomata.wator.InitData(width, margs(0).toInt, 1)
            // case "epidemic" => generated.example.epidemic.InitData(margs(0).toInt)  // first argument
            case "gameOfLife" => generated.example.gameOfLife.InitData(width, margs(0).toInt, 1)
            case "segregation" => generated.example.segregation.InitData(width, margs(0).toInt, margs(1).toInt)
        }
        
        val c = new SimulationConfig(agents, totalTurn)

        mode match {
            case "Akka" => 
                StartSimulation[AkkaMessagingLayer.type](c)
            case "Base" => 
                StartSimulation[BaseMessagingLayer.type](c)

            case x => 
                val containerConf = containerOpt match {
                    case "MC" => 
                        c.staticPartition(containers)(BoundedLatency)
                    case "DM" =>
                        c.staticPartition(containers)(DirectMethodCall)
                    case "VN" =>
                        c.staticPartition(containers)(VanillaContainer)
                    case _ =>
                        throw new Exception("Invalid container optimization!")
                }

                x match {
                    case "AkkaContainer" =>
                        StartSimulation[AkkaMessagingLayer.type](containerConf)
                    case "BaseContainer" =>
                        StartSimulation[BaseMessagingLayer.type](containerConf)
                    case _ =>
                        throw new Exception("Invalid messaging layer!")
                }
        }
    }
}