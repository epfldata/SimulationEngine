package meta.compile 

sealed abstract class CompilationMode {
    def setPackage(name: String): Unit  
    def canonicalName: String    
    def pkgName: String 
}

// no optimization
case object Vanilla extends CompilationMode {
    private var canonical: String = ""
    private var pkg: String = "" 

    def canonicalName: String = canonical 

    def pkgName: String = pkg 
    
    def setPackage(name: String): Unit = {
        canonical = name 
        pkg = "generated." + name 
    }
}

// merged Sims
case class SimsMerge(namePairs: List[(String, String)]) extends CompilationMode {
    private var canonical: String = ""
    private var pkg: String = "" 

    def canonicalName: String = canonical 

    def pkgName: String = pkg 
    
    def setPackage(name: String): Unit = {
        canonical = name 
        pkg = "generated." + name + "_merged"
    }
}

// stateless-server optimization
case class SimsStateless(statelessServers: List[String]) extends CompilationMode {
    private var canonical: String = ""
    private var pkg: String = "" 

    def canonicalName: String = canonical 

    def pkgName: String = pkg 
    
    def setPackage(name: String): Unit = {
        canonical = name 
        pkg = "generated." + name + "_sso"
    }
}



