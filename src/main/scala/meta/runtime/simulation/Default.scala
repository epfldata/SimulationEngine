package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time

class Default(val config: SimulationConfig) extends Simulation {

  private var actors: MutMap[Actor.AgentId, Actor] = MutMap() ++ config.actors.map(x => (x.id, x)).toMap
  private var currentTurn: Int = config.startTurn
  private var currentTime: Double = config.startTime
  private val mergeFrequency: Int = 5

  private var mx: Map[Actor.AgentId, Iterable[Message]] = Map()

  // Track the agent and the container agent it merges to
  private val runtimeContainer: MutMap[Actor.AgentId, Actor.AgentId] = MutMap[Actor.AgentId, Actor.AgentId]()

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def collect(): Unit = {
    newActors.map(x => {actors += (x.id -> x)})
    // actors = actors ::: newActors.map(x => (x.id, x)).toList
    newActors.clear()
    meta.Util.warning(s"Total agents ${actors.size}, ${actors.keySet}")
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  def scheduleEvents(): List[()=> Unit] = {
    val events: ListBuffer[()=> Unit] = new ListBuffer()
    events.append(
      () => { println(util.displayTime(currentTurn, currentTime)) }
    )
    events.append(() => collect())
    // If new actors are added, time takes them into account as well
    events.append(() => registerLabel(Time, actors.size))
    events.append(() => {
      mx = actors.flatMap(_._2.getSendMessages).groupBy(_.receiverId)
      // meta.Util.debug(s"Messages at root ${mx}")
      // Record the communication pattern
      if (meta.compile.Optimization.runtimeMerging){
        mx.foreach(x => {
          x._2.foreach(m => MessagingStats.recordMessage(m.senderId, m.receiverId))
        })
      }
      
      actors = actors.filterNot(x => x._2.deleted).map(a =>
      {(a._1, {
        val targetMessages: List[Message] = a._2.proxyIds.flatMap(id => mx.getOrElse(id, List()))
        a._2.cleanSendMessage
          .addReceiveMessages(Random.shuffle(targetMessages))
          .run()})
      })
    })
    events.append(() => proceed())
    if (meta.compile.Optimization.runtimeMerging) {
      events.append(() => {
        if (currentTurn % mergeFrequency == 0) {
          val candidates = MessagingStats.getMergeCandidates
          // meta.Util.debug(s"Merge agents: ${candidates} ${MessagingStats.getSketch}")
          candidates.foreach(x => {
            val c1 = new Container()
            if (!x.exists(a => runtimeContainer.keySet.contains(a))) {
              c1.addAgents(x.toList.map(a => {
                assert(actors.get(a).isDefined)
                actors.remove(a).get
              }))
            } else {
              // the ids of containers who hold at least one candidate agent
              val existingContainerIds = x.toList
                .map(a => runtimeContainer.get(a))
                .filter(n => n.isDefined)
                .map(n => n.get).toSet
              // the ids of the candidate agents who are not in any container 
              val independentAgentIds = x.filter(a => runtimeContainer.get(a).isEmpty)

              // materialize the containers given their ids
              val existingContainers = existingContainerIds
                .map(x => actors(x).asInstanceOf[Container])

              val independentAgents = independentAgentIds.map(x => actors(x)).toList

              // meta.Util.debug(s"x contains at least one merged agent! ${x}\nexisting containers are ${existingContainerIds}\nindependent agents are ${independentAgentIds}")

              // Remove the references of existing containers and independent agents from actors 
              existingContainerIds.union(independentAgentIds).foreach(c => {
                actors -= c
              })

              // list of agents reside in the containers
              val mergedAgents = existingContainers.flatMap(_.getAgents).toList
              
              // flatten out the agents and add them to the new container 
              c1.addAgents(mergedAgents ::: independentAgents)
              // add any messages contained in the previous containers
              c1.sendMessages ++= (existingContainers.toList).flatMap(x => x.getSendMessages)
            }
            
            c1.proxyIds.foreach(i => {
              runtimeContainer += (i -> c1.id)
            })

            // meta.Util.debug("Sent messages in merged container agent " + c1.sendMessages)
            assert(c1.getAgents.flatMap(x => x.receivedMessages).isEmpty)
            newActors.append(c1)
          })
          MessagingStats.clearMergeCandidates
        }
      })
    }
    events.toList
  }

  def run(): SimulationSnapshot = {

    val events: List[()=> Unit] = init()

    util.bench {
      while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
        events.foreach(_())
      }
    }

    SimulationSnapshot(actors.map(x => x._2).toList, currentTurn, currentTime)
  }
}