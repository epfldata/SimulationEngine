package generated.example

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.chart.{NumberAxis, ScatterChart, XYChart}

object DrawTimeseries {

  def apply(_title: String,
            canvas_w: Double,
            canvas_h: Double,
            series: List[(Double, Double)]): Unit = {
    val draw = new JFXApp {
      stage = new PrimaryStage {
        title = _title
        scene = new Scene(canvas_w, canvas_h) {
          val xAxis = NumberAxis()
          val yAxis = NumberAxis()
          val pData = XYChart.Series[Number, Number]("Turn",
            ObservableBuffer(series.map(td => XYChart.Data[Number, Number](td._1, td._2))))
          val plot = new ScatterChart(xAxis, yAxis, ObservableBuffer(pData))
          root = plot
        }
      }
    }

    draw.main(Array())
  }
}
