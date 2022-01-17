package example.epidemic

// SEIR model
sealed trait HealthStatus {
    def change(): HealthStatus
}

final case object Susceptible extends HealthStatus {
    def change(): HealthStatus = {
        println("Affected!")
        Infectious
    }
}

// Exposed but not infectious
// final case class Exposed() extends HealthStatus {
//     def change(): HealthStatus = Infectious()
// }

final case object Infectious extends HealthStatus {
    def change(): HealthStatus = {
        println("Recovered!")
        Recover
    }
}

// final case class Hospitalized() extends HealthStatus

// final case class Critical() extends HealthStatus

// Assume people who recover become immune
final case object Recover extends HealthStatus {
    def change(): HealthStatus = Recover
}


