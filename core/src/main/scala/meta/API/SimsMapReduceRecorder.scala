package meta.API

import meta.runtime.simulation._
import meta.runtime.{Actor, Message}

trait SimsMapReduceRecorder[MessagingLayer] {
    def runAndEval[K, T](c: SimulationConfig)(mapper: Actor=>K, reducer: List[K]=>T): List[T]
}

object SimsMapReduceRecorder {
    implicit val baseSimulation = {
        new SimsMapReduceRecorder[BaseMessagingLayer.type] {
            def runAndEval[K, T](c: SimulationConfig)(mapper: Actor=>K, reducer: List[K]=>T): List[T] = {
                new BaseWithReducer(c).run[K, T](mapper, reducer)
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