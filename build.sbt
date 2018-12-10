lazy val root = (project in file(".")).settings(
  name := "economic_simulations",
  organization := "ch.epfl.data",
  version := "1.0",
  scalaVersion := "2.11.8"
)

// libraryDependencies += "com.quantifind" %% "wisp" % "0.0.4"

libraryDependencies  ++= Seq(
//  "com.github.fommil.netlib" % "all" % "1.1.2",
  "org.scalanlp" %% "breeze" % "0.12",
//  "org.scalanlp" %% "breeze-natives" % "0.12",
  "org.scalanlp" %% "breeze-viz" % "0.12",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

