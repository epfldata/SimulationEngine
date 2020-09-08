package generated.simulation
import meta.deep.runtime.Actor

case class SimulationConfig(var actors: List[Actor],
                              var startTurn: Int = 0,
                              val totalTurn: Int = 100,
                              var startTime: Double = 0,
                              val totalTime: Double = 10,
                              val monitorEnabled: Boolean = false)
