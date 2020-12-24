ThisBuild / organization := "ch.epfl.data"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "1.0"

val paradiseVersion = "2.1.0"
val breezeVersion = "0.13.2"
val scalaTestVersion = "3.0.0"
val squidVersion = "0.4.1-SNAPSHOT"
val sparkVersion = "3.0.1"
val graphVizVersion = "0.10.0"

lazy val commonSettings = Seq(
  libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  libraryDependencies += "org.scalanlp" %% "breeze" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-viz" % breezeVersion
)

lazy val logSetting = Seq(
  excludeDependencies += "org.slf4j" % "slf4j-log4j12",
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
  .settings(
    name := "root",
    commonSettings, squidSettings, graphSettings, sparkSettings, logSetting
  )

lazy val library = (project in file("lib"))
  .settings(
    name := "library",
    commonSettings
  )
  .dependsOn(root)

lazy val runAll = taskKey[Unit]("run-all, for compiling all meta examples")

def runAllIn(config: Configuration) = Def.task {
    val s = streams.value
    val cp = (fullClasspath in config).value
    val r = (runner in run).value
    (discoveredMainClasses in config).value.foreach(c =>
      r.run(c, cp.files, Seq(), s.log))
}

lazy val example = (project in file("example"))
  .settings(
    name := "example",
    commonSettings, squidSettings, logSetting,
    runAll := runAllIn(Compile).value
  )
  .dependsOn(root, library)

lazy val genExample = (project in file("generated"))
  .settings(
    name := "genExample",
    commonSettings, logSetting, sparkSettings
  )
  .dependsOn(root, library, example)


