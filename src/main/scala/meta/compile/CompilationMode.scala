package meta.compile 

import meta.runtime.Actor 

sealed abstract class CompilationMode {
    def setPackage(name: Map[String, String]): Unit  
    def fullNameMap: Map[String, String]    
    def pkgName: String 
}

// no optimization
case object Vanilla extends CompilationMode {
    private var fullName: Map[String, String] = Map[String, String]()
    private var pkg: String = "" 

    def fullNameMap: Map[String, String] = fullName 

    def pkgName: String = pkg 
    
    def setPackage(name: Map[String, String]): Unit = {
        fullName = name 
        pkg = "generated." + name.get("Main").get
    }
}

// merged Sims
case class SimsMerge(namePairs: List[(String, String)]) extends CompilationMode {
    private var fullName: Map[String, String] = Map[String, String]()
    private var pkg: String = "" 

    def fullNameMap: Map[String, String] = fullName 

    def pkgName: String = pkg 
    
    def setPackage(name: Map[String, String]): Unit = {
        fullName = name 
        pkg = "generated." + name.get("Main").get + "_merged"
    }
}