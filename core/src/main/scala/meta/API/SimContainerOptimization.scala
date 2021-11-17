package meta.API

import meta.runtime.{Actor, Container, Message}

sealed trait SimContainerOptimization
final case object VanillaContainer extends SimContainerOptimization
final case object BoundedLatency extends SimContainerOptimization
final case object DirectMethodCall extends SimContainerOptimization

sealed trait SimRunnerMode
final case object CompiledSims extends SimRunnerMode

trait ContainerFactory[SimContainerOptimization, SimRunnerMode] {
  def createContainer(agents: List[Actor]): Container
}

object ContainerFactory {
    implicit val vanillaCompiled = {
        new ContainerFactory[VanillaContainer.type, CompiledSims.type] {
            override def createContainer(agents: List[Actor]): Container = {
                new Container {
                    containedAgents ++= agents.map(x => (x.id, x)).toMap
                    addProxyIds(agents.flatMap(x => x.getProxyIds))
                }
            }
        }
    }

    implicit val boundedLatencyCompiled = new ContainerFactory[BoundedLatency.type, CompiledSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
                new Container {
                    containedAgents ++= agents.map(x => (x.id, x)).toMap
                    addProxyIds(agents.flatMap(x => x.getProxyIds))
                    override def run(msg: List[Message]): (List[Message], Int) = {
                        var countDown: Int = 0
                        mx = (internalMessages ++ msg).toList.groupBy(_.receiverId)
                        sendMessages.clear()
                        do {
                            internalMessages.clear()
                            val sentMessages: List[Message] = containedAgents.flatMap(a => {
                                a._2.run(
                                    a._2.getProxyIds.toList.flatMap(
                                        id => mx.getOrElse(id, List())
                                    ))._1
                            }).toList
                            sendMessages.appendAll(sentMessages)
                            internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))
                            sendMessages --= internalMessages

                            countDown += 1
                            mx = internalMessages.toList.groupBy(_.receiverId)
                        } while (!internalMessages.isEmpty && countDown<kbound)
                        (sendMessages.toList, countDown)
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
                    x.getProxyIds
                }))
            }
        }
    }
}

