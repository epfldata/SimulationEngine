package meta.example

sealed trait CompilationMode
// no optimization
case object Vanilla extends CompilationMode
// merged Sims.
case class SimsMerge(namePairs: List[(String, String)]) extends CompilationMode
// stateless-server optimization
case class SimsStateless(statelessServers: List[String]) extends CompilationMode
