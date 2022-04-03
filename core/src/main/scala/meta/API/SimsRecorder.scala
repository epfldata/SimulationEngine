package meta.API

import meta.runtime.simulation._
import meta.runtime.{Actor, Message}

/**
 * Record a timeseries according to eval function 
 */
trait SimsRecorder[MessagingLayer] {
    def run[T](c: SimulationConfig)(eval: (List[Actor], List[Message]) => T): List[T]
}

object SimsRecorder {
    implicit val baseSimulation = {
        new SimsRecorder[BaseMessagingLayer.type] {
            def run[T](c: SimulationConfig)(eval: (List[Actor], List[Message]) => T): List[T] = {
                new BaseWithEval(c).run[T](eval)
            }
        }
    }

    // implicit val akkaSimulation = {
    //     new SimsRunner[AkkaMessagingLayer.type] {
    //         def run(c: SimulationConfig): SimulationSnapshot = {
    //             AkkaRun(c.actors, c.totalTurn, false, c.messages, c.role, c.port)
    //         }
    //     }
    // }
}