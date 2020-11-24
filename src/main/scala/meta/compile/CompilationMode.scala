package meta.compile 

sealed abstract class CompilationMode {
    def pkgName(name: String): String 
}

// no optimization
case object Vanilla extends CompilationMode {
    def pkgName(name: String): String = {
        "generated." + name 
    }
}

// merged Sims
case class SimsMerge(namePairs: List[(String, String)]) extends CompilationMode {
    def pkgName(name: String): String = {
        "generated." + name + "_merged"
    }
}

// stateless-server optimization
case class SimsStateless(statelessServers: List[String]) extends CompilationMode {
    def pkgName(name: String): String = {
        "generated." + name + "_sso"
    }
}



