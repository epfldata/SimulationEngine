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

lazy val logSetting = Seq(
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3", 
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
  .settings(commonSettings, squidSettings, graphSettings, sparkSettings)
  .settings(
    excludeDependencies += "org.slf4j" % "slf4j-log4j12"
  )
  .settings(logSetting)



lazy val no_messaging_example = (project in file("ecosim"))
  .settings(name := "no_messaging_example")
  .settings(commonSettings)

lazy val runAll = taskKey[Unit]("run-all, for compiling all meta examples")

def runAllIn(config: Configuration) = Def.task {
    val s = streams.value
    val cp = (fullClasspath in config).value
    val r = (runner in run).value
    (discoveredMainClasses in config).value.foreach(c =>
      r.run(c, cp.files, Seq(), s.log))
}

lazy val example = (project in file("example"))
  .settings(name := "example")
  .settings(commonSettings, squidSettings)
  .dependsOn(root)
  .settings(runAll := runAllIn(Compile).value)
  .settings(
    excludeDependencies += "org.slf4j" % "slf4j-log4j12"
  )
  .settings(logSetting)
