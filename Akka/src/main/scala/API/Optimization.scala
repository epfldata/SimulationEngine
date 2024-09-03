package simulation.akka.API

sealed trait Optimization
case object DirectMethodCall extends Optimization
case object MergedWorker extends Optimization

object OptimizationConfig {
    var conf: Optimization = MergedWorker

    // todo: tmp, fix with proper availability input
    var availability: Int = 1

    def directMethodCall(): Unit = {
      conf = DirectMethodCall
    }

    def mergedWorker(): Unit = {
      conf = MergedWorker
    }
}