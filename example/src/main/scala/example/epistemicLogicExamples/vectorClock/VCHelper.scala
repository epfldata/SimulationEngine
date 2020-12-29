package example
package epistemicLogicExamples

package object vectorClock {
  import meta.runtime.Actor.AgentId
  case class ProcessTime(id: AgentId, time: Int)
}
