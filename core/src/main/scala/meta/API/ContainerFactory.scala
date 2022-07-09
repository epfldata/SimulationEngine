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