package GUI.Graphics

import scala.scalajs.js
import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.window.alert

import scala.util.Random
import org.scalajs.dom.raw.{HTMLInputElement}


@JSExportAll
@JSExportTopLevel("Grid2DVisualization")
class Grid2DVisualization(
        var width: Int, 
        var height: Int,
        var grid_size: Int,
        val color_scheme: js.Map[Int, String],
        val canvas: Canvas) {

    def initGrid(width: Int = this.width, height: Int = this.height): Unit = {
        this.width = width
        this.height = height
        val world_height = height * grid_size
        val world_width = width * grid_size

        canvas.width = world_width
        canvas.height = world_height

        val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
        ctx.beginPath()
        ctx.lineWidth = 3
        ctx.strokeRect(0, 0, world_width, world_height)

        (0 to width).foreach(i => {
            ctx.moveTo(i*grid_size, 0)
            ctx.lineTo(i*grid_size, world_height)
        })

        (0 to height).foreach(i => {
            ctx.moveTo(0, i*grid_size)
            ctx.lineTo(world_width, i*grid_size)
        })
        ctx.stroke()
    }


    def visualizeInput(input: js.Array[Int]): Unit = {

        def drawPixel(x: Int, y: Int, color: String): Unit = {
            val ctx = canvas.getContext("2d")
            ctx.fillStyle = color
            ctx.fillRect(x, y, grid_size, grid_size)
        }

        initGrid()
        input.zipWithIndex.foreach(x => {
            for ((threshold, color) <- color_scheme) {
                threshold match {
                    case x._1 => drawPixel(grid_size * (x._2 % width), grid_size * (x._2 / width), color)
                    case _ =>
                }
            }
        })
    }

    def main(): Unit = {

        def getTextById(id: String): HTMLInputElement = {
            document.querySelector("input#" + id).asInstanceOf[HTMLInputElement]
        }

        def configSlider(): Unit = {
            val sliderNode = document.getElementById("stepRange")
            val gridSizeText = document.getElementById("grid_size")

            gridSizeText.textContent = f"Grid size: ${grid_size}"


            sliderNode.addEventListener("input", { (e: dom.Event) => {
                val newGridSize = getTextById("stepRange").value
                gridSizeText.textContent = f"Grid size: ${newGridSize}"
                grid_size = newGridSize.toInt
                initGrid()
            }})
        }

        document.addEventListener("DOMContentLoaded", { (e: dom.Event) =>
            initGrid()
            configSlider()
        })
    }
}