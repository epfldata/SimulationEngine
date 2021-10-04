ThisBuild / organization := "ch.epfl.data"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "1.0-SNAPSHOT"
name := "Simulation"

val paradiseVersion = "2.1.0"
val breezeVersion = "0.13.2"
val scalaTestVersion = "3.0.0"
val squidVersion = "0.4.1-SNAPSHOT"
val sparkVersion = "3.0.1"
val graphVizVersion = "0.10.0"
val akkaVersion = "2.6.14"

run / fork := true

lazy val commonSettings = Seq(
  libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  libraryDependencies += "org.scalanlp" %% "breeze" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-natives" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-viz" % breezeVersion,
)

lazy val squidSettings = Seq(
  // libraryDependencies += "ch.epfl.data" %% "squid" % squidVersion, 
  // resolvers += Resolver.sonatypeRepo("snapshots"),
  autoCompilerPlugins := true,
  addCompilerPlugin(
    "org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full
  )
)

lazy val akkaSettings = Seq(
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
)

// Enable graph drawing when debugging
lazy val graphSettings = Seq(
  libraryDependencies += "guru.nidi" % "graphviz-java" % graphVizVersion,
)

lazy val stagedSims = (project in file("stagedSims"))
  .settings(
    name := "stagedSims",
    autoCompilerPlugins := true,
    addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full),
    libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.20",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    libraryDependencies += "de.sciss" %% "coroutines" % "0.1.0",
  )

lazy val noMessaging = (project in file("ecosim"))
  .settings(
    name := "noMessaging",
    commonSettings)

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    commonSettings, squidSettings, graphSettings, akkaSettings,
    libraryDependencies += "de.sciss" %% "coroutines" % "0.1.0",
  ).dependsOn(stagedSims)

lazy val library = (project in file("library"))
  .settings(
    name := "library",
    commonSettings,
  )

lazy val runAll = taskKey[Unit]("run-all, for compiling all meta examples")

def runAllIn(config: Configuration) = Def.task {
    val s = streams.value
    val cp = (config / fullClasspath).value
    val r = (run / runner).value
    (config / discoveredMainClasses).value.foreach(c =>
      r.run(c, cp.files, Seq(), s.log))
}

lazy val example = (project in file("example"))
  .settings(
    name := "example",
    commonSettings, squidSettings,
    runAll := runAllIn(Compile).value,
    Test / parallelExecution := false
  )
  .dependsOn(core, library, stagedSims)

lazy val genExample = (project in file("generated"))
  .settings(
    name := "genExample",
    Test / parallelExecution := false,
    commonSettings, akkaSettings,
  )
  .dependsOn(core, library, example)


