package lib.Grid

import meta.runtime.Actor
import meta.runtime.Actor.AgentId

trait AgentWithNeighbors extends Actor {
    var connectedAgentIds: List[AgentId] = Nil

    // lazy var, doesn't need to sync with connectedAgentIds. We donot need references to 
    // connections who are not within the same container 
    var connectedAgents: Map[AgentId, Actor] = Map()
}