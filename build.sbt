ThisBuild / organization := "ch.epfl.data"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "1.0"

val paradiseVersion = "2.1.0"
val breezeVersion = "0.13.2"
val scalaTestVersion = "3.0.0"
val squidVersion = "0.4.1-SNAPSHOT"
val sparkVersion = "2.4.3"
val graphVizVersion = "0.10.0"

lazy val commonSettings = Seq(
  libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  libraryDependencies += "org.scalanlp" %% "breeze" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-viz" % breezeVersion,
  resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)

lazy val squidSettings = Seq(
  libraryDependencies += "ch.epfl.data" %% "squid" % squidVersion,
  resolvers += Resolver.sonatypeRepo("snapshots"),
  autoCompilerPlugins := true,
  addCompilerPlugin(
    "org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full
  )
)

lazy val sparkSettings = Seq(
  libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
)

// Enable graph drawing when debugging
lazy val graphSettings = Seq(
  libraryDependencies += "guru.nidi" % "graphviz-java" % graphVizVersion
)

lazy val root = (project in file("."))
  .settings(name := "root")
  .settings(commonSettings: _*)
  .settings(squidSettings: _*)
  .settings(graphSettings: _*)

lazy val no_messaging_example = (project in file("ecosim"))
  .settings(name := "no_messaging_example")
  .settings(commonSettings: _*)

lazy val example = (project in file("example"))
  .settings(name := "example")
  .settings(commonSettings: _*)
  .settings(squidSettings: _*)
  .settings(sparkSettings: _*)
  .dependsOn(root)

//lazy val sims_generated = (project in file("generated"))
//  .settings(
//    name := "sims_generated",
//    scalaSource in Compile := baseDirectory.value / "main/scala")
//  .settings(sparkSettings: _*)
//  .dependsOn(example)
