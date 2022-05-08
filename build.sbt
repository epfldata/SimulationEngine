ThisBuild / organization := "ch.epfl.data"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "1.3-SNAPSHOT"

import com.trueaccord.scalapb.compiler.Version.scalapbVersion
// import com.typesafe.sbt.SbtMultiJvm.multiJvmSettings
// import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

val project_name = "TickTalk"
name := project_name

val paradiseVersion = "2.1.0"
val breezeVersion = "0.13.2"
val scalaTestVersion = "3.1.2"
val squidVersion = "0.4.1-SNAPSHOT"
val sparkVersion = "3.0.1"
val graphVizVersion = "0.10.0"
val akkaVersion = "2.6.14"
val scalapbVersion = "1.0.6"

run / fork := true

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
  libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,
)

lazy val sparkSettings = Seq(
  libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion,
  libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion,
)

// Enable graph drawing when debugging
lazy val graphSettings = Seq(
  libraryDependencies += "guru.nidi" % "graphviz-java" % graphVizVersion,
)

lazy val protobufSettings = Seq(
  libraryDependencies ++= Seq(
    "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.6.7" % "protobuf"
  ),
  Compile / PB.targets := Seq(
    scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
  )
)

lazy val noMessaging = (project in file("ecosim"))
  .settings(
    name := f"${project_name}-noMessaging",
    commonSettings)

lazy val core = (project in file("core"))
  .settings(
    name := f"${project_name}-core",
    commonSettings, squidSettings, graphSettings, akkaSettings, sparkSettings, protobufSettings,
    libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.20",
    Test / parallelExecution := false,
  )

lazy val genCore = (project in file("gen-core"))
  .settings(
    name := f"${project_name}-genCore",
    Test / parallelExecution := false,
    commonSettings, akkaSettings, sparkSettings,
  )
  .dependsOn(core % "compile->compile;compile->test", library)

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
    commonSettings, akkaSettings, sparkSettings,
  )
  // .settings(multiJvmSettings: _*)
  // .configs(MultiJvm)
  .dependsOn(core, library, example, gui)