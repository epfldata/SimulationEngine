package meta.API

import meta.runtime.simulation._

sealed trait MessagingLayer
// Centralized, star network
final case object BaseMessagingLayer extends MessagingLayer
// Centralized dispatcher, distributed agents
final case object AkkaMessagingLayer extends MessagingLayer
// Same as above, but staged (no compilation)
final case object BaseStaged extends MessagingLayer 
final case object AkkaStaged extends MessagingLayer

trait SimsRunner[MessagingLayer] {
    def run(c: SimulationConfig): SimulationSnapshot
}

object SimsRunner {
    implicit val baseSimulation = {
        new SimsRunner[BaseMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                val finalAgents = new Base(c.actors, c.totalTurn).run()
                SimulationSnapshot(finalAgents)
            }
        }
    }

    implicit val baseStagedSimulation = {
        new SimsRunner[BaseStaged.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                val finalAgents = new StagedBase(c.actors, c.totalTurn).run()
                SimulationSnapshot(finalAgents)
            }
        }
    }

    implicit val akkaSimulation = {
        new SimsRunner[AkkaMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                val finalAgents = AkkaRun(c.actors, c.totalTurn, false)
                SimulationSnapshot(finalAgents)
            }
        }
    }

    implicit val akkaStagedSimulation = {
        new SimsRunner[AkkaStaged.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                val finalAgents = AkkaRun(c.actors, c.totalTurn, true)
                SimulationSnapshot(finalAgents)
            }
        }
    }
}