package meta.runtime

import Actor.AgentId
import meta.classLifting.SpecialInstructions._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import collection.JavaConverters._

/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 

class Container extends Actor {
    protected var position: Int = 0

    // Bound latency, default to 1
    var kbound: Int = 1
    // Use a list buffer to remove agents
    val containedAgents: scala.collection.mutable.Map[AgentId, Actor] = scala.collection.mutable.Map[AgentId, Actor]()
    
    protected val internalMessages: MutMap[AgentId, ListBuffer[Message]] = MutMap[AgentId, ListBuffer[Message]]()

    // Dynamically add agents to a container at run time
//   def addAgents(sims: Seq[Actor]): Unit = {
//     val simsMessages = sims.flatMap(_.getSendMessages)
//     sendMessages.appendAll(simsMessages)
//     containedAgents ++= sims.map(_.cleanSendMessage).map(x => (x.id, x)).toMap
//     addProxyIds(sims.flatMap(x => x.proxyIds))
//     sims.foreach(s => {
//       s._container = this
//     })
//   }

    def setKBound(bound: Int): Unit = {
        kbound = bound
    }

    // Before invoking run, the system has already delivered messages
    override def run(): Int = {
        messageListener()
        var localTurns: Int = 0
        sendMessages.clear()

        while (localTurns<kbound) {
            var passed: Int = 1
            val sentMessages = containedAgents.map(a => {
                var local_passed: Int = a._2.run() 
                if (local_passed > passed){
                    passed = local_passed
                }                
                a._2.sendMessages
            })

            sentMessages.foreach(cmap => {
                cmap.foreach(r => {
                    if (containedAgents.get(r._1).isDefined){
                        containedAgents.get(r._1).get.receivedMessages.addAll(r._2.asJava)
                    } else {
                        sendMessages.getOrElseUpdate(r._1, new ListBuffer[Message]()).appendAll(r._2)
                    }
                })
            })
            localTurns += passed
            // containedAgents.foreach(i => i._2.merge())
        } 
        time += localTurns        
        time
    }
}