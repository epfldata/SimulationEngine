package meta.API

import meta.runtime.{Actor, Container, Message}

trait ContainerFactory[SimContainerOptimization, SimRunnerMode] {
  def createContainer(agents: List[Actor]): Container
}

sealed trait SimContainerOptimization
final case object BoundedLatency extends SimContainerOptimization
final case object DirectMethodCall extends SimContainerOptimization

sealed trait SimRunnerMode
final case object CompiledSims extends SimRunnerMode

object ContainerFactory {
    implicit val boundedLatencyCompiled = new ContainerFactory[BoundedLatency.type, CompiledSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
                new Container {
                    containedAgents ++= agents.map(x => (x.id, x)).toMap
                    addProxyIds(agents.flatMap(x => x.proxyIds))
                    override def run(msg: List[Message]): (List[Message], Int) = {
                        var localTurns: Int = 0
                        mx = (internalMessages ++ msg).toList.groupBy(_.receiverId)
                        sendMessages.clear()
                        do {
                            internalMessages.clear()
                            val sentMessages: List[Message] = containedAgents.flatMap(a => {
                                a._2.run(
                                    a._2.proxyIds.flatMap(
                                        id => mx.getOrElse(id, List())
                                    ))._1
                            }).toList
                            sendMessages.appendAll(sentMessages)
                            internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))
                            sendMessages --= internalMessages

                            localTurns += 1
                            mx = internalMessages.toList.groupBy(_.receiverId)
                        } while (!internalMessages.isEmpty && localTurns<kbound)
                        (sendMessages.toList, localTurns)
                    }
                }
        }
    }

    implicit val directMethodCallCompiled = new ContainerFactory[DirectMethodCall.type, CompiledSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
            new Container {
                containedAgents ++= agents.map(x => (x.id, x)).toMap
                addProxyIds(agents.flatMap(x => {
                    x._container = this
                    x.proxyIds
                }))
            }
        }
    }
}

object newContainer {
    import ContainerFactory._

    def apply(agents: List[Actor])(isCompiled: Boolean, containerOpt: SimContainerOptimization): Container = {

        (containerOpt, isCompiled) match {
            case (BoundedLatency, true) => 
                boundedLatencyCompiled.createContainer(agents)
            case (DirectMethodCall, true) => 
                directMethodCallCompiled.createContainer(agents)
            }
    }
}