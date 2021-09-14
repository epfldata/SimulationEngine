lazy val root = (project in file(".")).settings(
  //name := "economic_simulations",
  name := "economics",
  organization := "ch.epfl.data",
  version := "1.0",
  scalaVersion := "2.13.1"
) 

//publishTo := Some(Resolver.file("file", new File("/Users/douglasbouchet/Code/SwissFarmSimulator/tmp")))

// libraryDependencies += "com.quantifind" %% "wisp" % "0.0.4"

libraryDependencies  ++= Seq(
//  "com.github.fommil.netlib" % "all" % "1.1.2",
  //"org.scalanlp" %% "breeze" % "0.12", // old
  "org.scalanlp" %% "breeze" % "1.2",
//  "org.scalanlp" %% "breeze-natives" % "0.12",
  // "org.scalanlp" %% "breeze-viz" % "0.12", OLD
  "org.scalanlp" %% "breeze-viz" % "1.2",
  //"org.scalatest" %% "scalatest" % "3.0.0" % "test" OLD
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test"
)

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

