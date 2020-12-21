lazy val no_messaging_example = (project in file("ecosim"))
  .settings(
    // Add the sources
    unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "main" / "scala",
    libraryDependencies += "org.scalanlp" %% "breeze" % "0.13.2",
    libraryDependencies += "org.scalanlp" %% "breeze-viz" % "0.13.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
