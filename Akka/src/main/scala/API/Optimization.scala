package simulation.akka.API

import com.typesafe.config.ConfigFactory
import meta.API.SimulationSnapshot
import meta.runtime.{Actor, Message}
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.actor.typed.ActorSystem

sealed trait Optimization
case object DirectMethodCall extends Optimization
case object MergedWorker extends Optimization
case object ConcurrentWorker extends Optimization

object OptimizationConfig {
    var conf: Optimization = ConcurrentWorker

    // todo: tmp, fix with proper availability input
    var availability: Int = 1

    def directMethodCall(): Unit = {
      conf = DirectMethodCall
    }

    def mergedWorker(): Unit = {
      conf = MergedWorker
    }

    def concurrentWorker(): Unit = {
      conf = ConcurrentWorker
    }
}