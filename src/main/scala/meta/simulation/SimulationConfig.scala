package generated.simulation
import meta.deep.runtime.Actor

case class SimulationConfig(var actors: List[Actor],
                            var startTurn: Int = 0,
                            val totalTurn: Int = 100,
                            var startTime: Double = 0,
                            val totalTime: Double = 10,
                            val monitorEnabled: Boolean = false)

case class SimulationSnapshot(var actors: List[Actor] = List(),
                              var currentTurn: Int = 0,
                              var currentTime: Double = 0,
                              val monitorEnabled: Boolean = false)