package meta.API

import meta.runtime.{Actor, Container, Message}
import org.coroutines.call

sealed trait SimContainerOptimization
final case object VanillaContainer extends SimContainerOptimization
final case object MessageCaching extends SimContainerOptimization
final case object DirectMethodCall extends SimContainerOptimization
// final case object DirectDelivery extends SimContainerOptimization

sealed trait SimRunnerMode
final case object CompiledSims extends SimRunnerMode
final case object StagedSims extends SimRunnerMode

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

    implicit val vanillaStaged = new ContainerFactory[VanillaContainer.type, StagedSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
            new Container {
                containedAgents ++= agents.map(x => (x.id, x)).toMap
                addProxyIds(agents.flatMap(x => x.getProxyIds))
            }
        }
    }

    implicit val messageCachingCompiled = new ContainerFactory[MessageCaching.type, CompiledSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
                new Container {
                    containedAgents ++= agents.map(x => (x.id, x)).toMap
                    addProxyIds(agents.flatMap(x => x.getProxyIds))

                    override def run(msg: List[Message]): List[Message] = {
                        mx = (internalMessages ++ msg).toList.groupBy(_.receiverId)

                        sendMessages.clear()
                        internalMessages.clear()

                        val sentMessages = containedAgents.flatMap(a => {
                                a._2.run(
                                    a._2.getProxyIds.toList.flatMap(
                                        id => mx.getOrElse(id, List())
                                    ))
                            })

                        sendMessages.appendAll(sentMessages)

                        internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))

                        sendMessages --= internalMessages
                        sendMessages.toList
                    }
                }
        }
    }

    implicit val messageCachingStaged = new ContainerFactory[MessageCaching.type, StagedSims.type] {
        override  def createContainer(agents: List[Actor]): Container = {
                new Container {
                    containedAgents ++= agents.map(x => (x.id, x)).toMap
                    addProxyIds(agents.flatMap(x => x.getProxyIds))

                    containedAgentInstances.appendAll(agents.map(x => call (x.run()())))

                    override def run() = org.coroutines.coroutine((() => while (true) 
                    {
                        mx = (internalMessages ++ receivedMessages).toList.groupBy(_.receiverId)

                        receivedMessages.clear()
                        internalMessages.clear()

                        containedAgents.foreach(a => {
                            a._2.addReceiveMessages(
                                a._2.getProxyIds.toList.flatMap(
                                    id => mx.getOrElse(id, List())
                                ))
                        })

                        containedAgentInstances.map(x =>x.resume)

                        sendMessages.appendAll(containedAgentInstances.flatMap(a => a.value))

                        internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))

                        sendMessages --= internalMessages

                        org.coroutines.yieldval(sendMessages.toList);
                        sendMessages.clear()
                    }))
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

    implicit val directMethodCallStaged = new ContainerFactory[DirectMethodCall.type, StagedSims.type] {
        override def createContainer(agents: List[Actor]): Container = {
            new Container {
                containedAgents ++= agents.map(x => (x.id, x)).toMap
                addProxyIds(agents.flatMap(x => {
                    x._container = this
                    x.getProxyIds
                }))
                containedAgentInstances.appendAll(agents.map(x => call (x.run()())))
            }
        }
    }
}

