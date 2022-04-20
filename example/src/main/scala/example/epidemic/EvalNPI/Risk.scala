package example
package epidemic.evalNPI


object Risk {
    def eval(vulnerability: String, currentHealth: String, projectedHealth: String): Double = {
        vulnerability match {
            case "low" =>
                (currentHealth, projectedHealth) match {
                    case ("Exposed", "Infectious") => 0.6
                    case ("Infectious", "Hospitalized") => 0.1
                    case ("Hospitalized", "Deceased") => 0.1
                    case _ => 0.01
                }
            case "high" =>
                (currentHealth, projectedHealth) match {
                    case ("Exposed", "Infectious") => 0.9
                    case ("Infectious", "Hospitalized") => 0.4
                    case ("Hospitalized", "Deceased") => 0.5
                    case _ => 0.05
                }
        }
    }
}
