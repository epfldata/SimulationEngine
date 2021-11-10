package meta.API

import meta.runtime.{Container, Actor}

object newContainer {
    import ContainerFactory._

    def apply(agents: List[Actor])(isCompiled: Boolean, containerOpt: SimContainerOptimization): Container = {

        (containerOpt, isCompiled) match {
            case (VanillaContainer, true) => 
                vanillaCompiled.createContainer(agents)
            case (VanillaContainer, false) => 
                vanillaStaged.createContainer(agents)
            case (BoundedLatency, true) => 
                boundedLatencyCompiled.createContainer(agents)
            // case (BoundedLatency, false) => 
            //     boundedLatencyStaged.createContainer(agents)
            case (DirectMethodCall, true) => 
                directMethodCallCompiled.createContainer(agents)
            // case (DirectMethodCall, false) => 
            //     directMethodCallStaged.createContainer(agents)
            }
    }
}