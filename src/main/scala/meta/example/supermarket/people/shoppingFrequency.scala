package meta.example.supermarket.people

trait Weekly {
  val frequency: Int = 7
}

trait BiWeekly {
  val frequency: Int = 3
}

trait Daily {
  val frequency: Int = 1
}

trait SemiWeekly {
  val frequency: Int = 10
}

object frequencySummary {
  val names: Vector[String] = Vector(
    "Weekly",
    "BiWeekly",
    "Daily",
    "SemiWeekly"
  )
}