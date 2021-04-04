package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time
import meta.runtime.Actor.AgentId
import scala.collection.immutable.ListMap

class Default(val config: SimulationConfig) extends Simulation {

  private var actors: MutMap[AgentId, Actor] = MutMap() ++ config.actors.map(x => (x.id, x)).toMap
  private var currentTurn: Int = config.startTurn
  private var currentTime: Double = config.startTime
  private val mergeFrequency: Int = 5
  private val chattyAgents = new ChattyAgents(10)

  private var retiredActors: MutMap[AgentId, Actor] = MutMap()

  // test only, should get it from Spark (in the Spark implementation)
  private val migrateThreshold: Int = 3

  // Track the agent and the container agent it merges to
  private val runtimeContainer: MutMap[AgentId, AgentId] = MutMap[AgentId, AgentId]()

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def collect(): Unit = {
    newActors.map(x => {actors += (x.id -> x)})
    newActors.clear()
    meta.Util.warning(s"Total agents ${actors.size}, ${actors.keySet}")
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  private def inlineAgents(candidates: List[List[AgentId]]): Unit = {
    // Check if any candidate is frequently paired with others, comparing with the rest of candidates
    val candidateFrequencyMap = candidates.flatten.groupBy(identity).mapValues(_.size)

    // Sort the candidate frequency map from high to low
    val sortedCandidacyFrequency = ListMap(candidateFrequencyMap.toSeq.sortWith(_._2 > _._2):_*)

    meta.Util.debug(s"Communication frequency ${sortedCandidacyFrequency}")

    val hottestCandidateMeta = sortedCandidacyFrequency.head
    val hottestCandidateAgent = 
      actors.getOrElse(hottestCandidateMeta._1, retiredActors.getOrElse(hottestCandidateMeta._1, throw new Exception("Agent not found!")))
      
    // Heuristic: if the highest hit agent is more than migrate threshold times comparint to the next high frequency agent and it is a minimal agent with relaxed consistency and no connections, then for each agent that communicates with it, we assign them a copy 
    if (hottestCandidateMeta._2 >= migrateThreshold*(sortedCandidacyFrequency.tail.head._2) && hottestCandidateAgent.relaxConsistency && hottestCandidateAgent.connectedAgents.isEmpty) {
      val connectionOfHottestCandidate = candidates.filter(x => x.contains(hottestCandidateMeta._1)).flatten.filterNot(_==hottestCandidateMeta._1).toSet
      meta.Util.debug(s"Found a hot candidate! ${hottestCandidateMeta._1}")
      meta.Util.debug(s"Connections of a hot candidate: ${connectionOfHottestCandidate}")

      connectionOfHottestCandidate.foreach(c => {
        var recipientActor = actors(c)
        val cloner = actors(hottestCandidateMeta._1).deepClone()

        recipientActor.connectedAgents = cloner :: recipientActor.connectedAgents.filterNot(x => x.id == hottestCandidateMeta._1)
        
        meta.Util.debug(s"Cloner created for agent ${hottestCandidateMeta._1} with id ${cloner.id}!")
        meta.Util.debug(s"Recipient agent's connection has been updated: ${recipientActor.connectedAgents.map(x => x.id)}")
        actors += (cloner.id -> cloner)
        fuseAgents(List(List(c, cloner.id)))
      })
      fuseAgents(candidates.filterNot(x => x.contains(hottestCandidateMeta._1)))
    } else {
      fuseAgents(candidates)
    }
  }

  private def fuseAgents(candidates: List[List[AgentId]]): Unit = {
    meta.Util.debug(s"Fuse agents: ${candidates}")
    meta.Util.debug(s"Current actors: ${actors}")
    candidates.foreach(x => {
      val c1 = new Container()
      if (!x.exists(a => runtimeContainer.keySet.contains(a))) {
        c1.addAgents(x.map(aId => {
          assert(actors.get(aId).isDefined)
          val sim = actors.remove(aId).get
          if (!sim.isInstanceOf[Container]) {
            retiredActors += (aId -> sim)
          }
          sim
        }))
      } else {
        // the ids of containers who hold at least one candidate agent
        val existingContainerIds = x.toList
          .map(a => runtimeContainer.get(a))
          .filter(n => n.isDefined)
          .map(n => n.get)
        // the ids of the candidate agents who are not in any container 
        val independentAgentIds = x.filter(a => runtimeContainer.get(a).isEmpty)

        // materialize the containers given their ids
        val existingContainers = existingContainerIds
          .map(x => actors(x).asInstanceOf[Container])

        val independentAgents = independentAgentIds.map(x => actors(x)).toList

        // meta.Util.debug(s"x contains at least one merged agent! ${x}\nexisting containers are ${existingContainerIds}\nindependent agents are ${independentAgentIds}")

        // Remove the references of existing containers and independent agents from actors 
        existingContainerIds.foreach(actors -= _)

        independentAgentIds.foreach(c => {
          retiredActors += (c -> actors(c))
          actors -= c
        })

        // list of agents reside in the containers
        val mergedAgents = existingContainers.flatMap(_.getAgents).toList
        
        // flatten out the agents and add them to the new container 
        c1.addAgents(mergedAgents ::: independentAgents)
        // add any messages contained in the previous containers
        c1.sendMessages ++= (existingContainers.toList).flatMap(x => x.getSendMessages)
      }
    
      c1.getProxyIds.foreach(i => {
        runtimeContainer += (i -> c1.id)
      })

      // meta.Util.debug("Sent messages in merged container agent " + c1.sendMessages)
      assert(c1.getAgents.flatMap(x => x.receivedMessages).isEmpty)
      actors += (c1.id -> c1)
    })
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
      val msgs: List[Message] = actors.flatMap(_._2.getSendMessages).toList
      val mx = msgs.groupBy(_.receiverId)
      // meta.Util.debug(s"Messages at root ${mx}")
      // Record the communication pattern
      if (meta.compile.Optimization.runtimeMerging){
        chattyAgents.recordMessage(msgs)
      }
      
      actors = actors.filterNot(x => x._2.deleted).map(a =>
      {(a._1, {
        val targetMessages: List[Message] = a._2.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
        a._2.cleanSendMessage
          .addReceiveMessages(Random.shuffle(targetMessages))
          .run()})
      })
    })
    events.append(() => proceed())
    if (meta.compile.Optimization.runtimeMerging) {
      events.append(() => {
        if (currentTurn % mergeFrequency == 0) {
          val pairCandidates = chattyAgents.getPairedMergeCandidates
          if (!pairCandidates.isEmpty) {
            inlineAgents(pairCandidates)
            chattyAgents.clearMergeCandidates
          }
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