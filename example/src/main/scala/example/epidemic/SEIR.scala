package example.epidemic

import scala.util.Random
// SEIR model
sealed trait HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus
}

final case object Susceptible extends HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus = {
        // println("Affected!")
        Infectious
    }
}

final case object Infectious extends HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus = {
        val worse_prob: Double = vulnerability.risk(Infectious).getOrElse(Hospitalized, 0)
        if (Random.nextDouble < worse_prob) {
            // println("Hospitalized!")
            Hospitalized
        } else {
            Recover
        }
    }
}

final case object Hospitalized extends HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus = {
        val worse_prob: Double = vulnerability.risk(Hospitalized).getOrElse(Deceased, 0)
        if (Random.nextDouble < worse_prob) {
            // println("Deceased!")
            Deceased
        } else {
            Recover
        }
    }
}

// Assume people who recover become immune
final case object Recover extends HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus = Recover
}

final case object Deceased extends HealthStatus {
    def change(vulnerability: VulnerabilityLevel): HealthStatus = Deceased
}

