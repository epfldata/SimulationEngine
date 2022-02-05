package meta.API

import meta.runtime.simulation._

sealed trait MessagingLayer
// Centralized
final case object BaseMessagingLayer extends MessagingLayer
// Distributed
final case object AkkaMessagingLayer extends MessagingLayer
final case object SparkMessagingLayer extends MessagingLayer

trait SimsRunner[MessagingLayer] {
    def run(c: SimulationConfig): SimulationSnapshot
}

object SimsRunner {
    implicit val baseSimulation = {
        new SimsRunner[BaseMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                new Base(c.actors, c.totalTurn, c.messages).run()
            }
        }
    }

    implicit val akkaSimulation = {
        new SimsRunner[AkkaMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                AkkaRun(c.actors, c.totalTurn, false, c.messages, c.role, c.port)
            }
        }
    }

    implicit val sparkSimulation = {
        new SimsRunner[SparkMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                new SparkRun(c.actors, c.totalTurn, c.messages).run()
            }
        }
    }
}