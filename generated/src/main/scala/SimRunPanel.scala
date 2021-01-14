package generated.example

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.chart.{NumberAxis, ScatterChart, XYChart}
import scalafx.scene.control.{Button, ComboBox, ListView}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{GridPane, HBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent

object SimRunPanel extends JFXApp {
  stage = new PrimaryStage {
    title = "Schelling's segregation example"
    scene = new Scene(400, 300) {
      val button = new Button("Run")
      button.layoutX = 100
      button.layoutY = 50

      val comboBox = new ComboBox(List("Scala", "Java"))
      comboBox.layoutX = 200
      comboBox.layoutY = 50

      val listView = new ListView(List("Scala", "Java"))
      listView.layoutX = 100
      listView.layoutY = 100
      listView.prefHeight = 150

      button.onAction = (e: ActionEvent) => {
        println("Button clicked!")
      }

      content = List(button, comboBox, listView)
//      val xAxis = NumberAxis()
//      val yAxis = NumberAxis()
//      val pData = XYChart.Series[Number, Number]("Turn",
//        ObservableBuffer(data.map(td => XYChart.Data[Number, Number](td._1, td._2))))
//      val plot = new ScatterChart(xAxis, yAxis, ObservableBuffer(pData))
//      root = plot
//            fill = White
//            content = new GridPane()
//            content = new HBox {
//              padding = Insets(20)
//              children = Seq(
//                new Text {
//                  text = "Hello "
//                  style = "-fx-font-size: 48pt"
//                  fill = new LinearGradient(
//                    endX = 0,
//                    stops = Stops(PaleGreen, SeaGreen))
//                },
//                new Text {
//                  text = "World!!!"
//                  style = "-fx-font-size: 48pt"
//                  fill = new LinearGradient(
//                    endX = 0,
//                    stops = Stops(Cyan, DodgerBlue)
//                  )
//                  effect = new DropShadow {
//                    color = DodgerBlue
//                    radius = 25
//                    spread = 0.25
//                  }
//                }
//              )
//            }
    }
  }
}
