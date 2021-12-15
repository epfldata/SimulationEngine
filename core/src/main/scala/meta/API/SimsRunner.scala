package meta.API

import meta.runtime.simulation._

sealed trait MessagingLayer
// Centralized, star network
final case object BaseMessagingLayer extends MessagingLayer
// Centralized dispatcher, distributed agents
final case object AkkaMessagingLayer extends MessagingLayer


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
                AkkaRun(c.actors, c.totalTurn, false, c.messages)
            }
        }
    }
}