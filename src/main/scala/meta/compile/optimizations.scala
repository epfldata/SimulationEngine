package meta.compile 

import scala.collection.mutable.Map 
import meta.runtime.Actor 

object Optimization {
    // this flag indicates whether we copy stateless methods to the sender agents
    var sso: Boolean = false
    
    // this flag indicates whether we create container agents automatically at runtime to reduce messaging
    val runtimeMerging: Boolean = true
}

object Development {
    val debug: Boolean = true 
}