package generated.example
package test

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.{Scene, Node}

import scalafx.scene.chart.{NumberAxis, ScatterChart, XYChart}
import scalafx.embed.swing.SwingFXUtils
import java.io.File
import javax.imageio.ImageIO

class segregationTest extends FlatSpec {

  import segregation._

  "The similarity level of the segregation model" should "converge to around 0.85" in {
    val totalTurns: Int = 100
    val config: Config = new Config(InitData.initActors, 0, totalTurns, 0, 0)
    val res = new Default(config).run()
//    val res = new SimulationSpark(config).run()

    val series: List[(Double, Double)] = res.actors
      .filter(a => a.isInstanceOf[LoggerBotTimeseries])
      .map(x => x.asInstanceOf[LoggerBotTimeseries].timeseries)
      .head

    DrawTimeseries("Schelling's segregation example", 500, 500, series)
  }
}
