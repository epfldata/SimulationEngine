package meta.compile 

import scala.collection.mutable.Map 
import meta.runtime.Actor 

object Optimization {
    // this flag indicates whether we copy stateless methods to the sender agents
    var sso: Boolean = false
}

object Development {
    val debug: Boolean = false 
}