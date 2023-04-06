ThisBuild / organization := "ch.epfl.data"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "2.0-SNAPSHOT"

val project_name = "CloudCity"
name := project_name

val paradiseVersion = "2.1.0"
val breezeVersion = "0.13.2"
val scalaTestVersion = "3.1.2"
val squidVersion = "0.4.1-SNAPSHOT"
val graphVizVersion = "0.10.0"
val akkaVersion = "2.6.14"
// val scalapbVersion = "1.0.6"

run / fork := true

val pubLocal = Option(System.getProperty("pubLocal")).getOrElse("false")

lazy val commonSettings = Seq(
  libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  libraryDependencies += "org.scalanlp" %% "breeze" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-natives" % breezeVersion,
  libraryDependencies += "org.scalanlp" %% "breeze-viz" % breezeVersion,
)

lazy val squidSettings = Seq(
  autoCompilerPlugins := true,
  addCompilerPlugin(
    "org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full
  ),
  unmanagedBase := (unmanagedBase in LocalRootProject).value
)

lazy val akkaSettings = Seq(
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
  libraryDependencies += "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  libraryDependencies += "com.typesafe.akka" %% "akka-cluster-typed"         % akkaVersion,
)

// Enable graph drawing when debugging
lazy val graphSettings = Seq(
  libraryDependencies += "guru.nidi" % "graphviz-java" % graphVizVersion,
)

lazy val noMessaging = (project in file("ecosim"))
  .settings(
    name := f"${project_name}-noMessaging",
    commonSettings)

lazy val core = (project in file("core"))
  .settings(
    name := f"${project_name}-core",
    commonSettings, squidSettings, graphSettings,
    scalacOptions ++= Seq("-Xlint"),
    libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.20",
    libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
    Test / parallelExecution := false,
  )

lazy val genCore = (project in file("gen-core"))
  .settings(
    name := f"${project_name}-genCore",
    Test / parallelExecution := false,
  ).dependsOn(core % "compile->compile;compile->test")

lazy val akka = if (pubLocal == "true") {
  (project in file("Akka"))
  .settings(
    name := f"${project_name}-akka",
    commonSettings, akkaSettings,
    Test / parallelExecution := false,
  ).dependsOn(core % "compile->compile;compile->test")
} else {
  (project in file("Akka"))
    .settings(
      name := f"${project_name}-akka",
      commonSettings, akkaSettings,
      Test / parallelExecution := false,
    ).dependsOn(core % "compile->compile;compile->test", genCore, genExample)
}

lazy val base = if (pubLocal == "true") {
  (project in file("Base"))
  .settings(
    name := f"${project_name}-base",
    commonSettings,
    Test / parallelExecution := false,
  ).dependsOn(core % "compile->compile;compile->test")
} else {
  (project in file("Base"))
    .settings(
      name := f"${project_name}-base",
      commonSettings,
      Test / parallelExecution := false,
    ).dependsOn(core % "compile->compile;compile->test", genCore, genExample)
}

lazy val library = (project in file("library"))
  .settings(
    name := f"${project_name}-library",
    commonSettings, 
  ).dependsOn(core)

lazy val gui = (project in file("GUI"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := f"${project_name}-GUI",
    commonSettings, 
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0",
  ).dependsOn(core)

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
    name := f"${project_name}-example",
    commonSettings, squidSettings,
    runAll := runAllIn(Compile).value,
    Test / parallelExecution := false
  )
  .dependsOn(core, library)

lazy val genExample = (project in file("generated"))
  .settings(
    name := f"${project_name}-genExample",
    Test / parallelExecution := false,
    // libraryDependencies += "org.plotly-scala" %%% "plotly-render" % "0.8.2",
    commonSettings
  ).dependsOn(core, library, example)
