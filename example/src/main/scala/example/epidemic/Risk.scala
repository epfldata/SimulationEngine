package example.epidemic

object Risk {
    def eval(vulnerability: String, currentHealth: String, projectedHealth: String): Double = {
        vulnerability match {
            case "low" =>
                (currentHealth, projectedHealth) match {
                    case ("Infectious", "Hospitalized") => 0.1
                    case ("Hospitalized", "Deceased") => 0.1
                    case _ => 0.01
                }
            case "high" =>
                (currentHealth, projectedHealth) match {
                    case ("Infectious", "Hospitalized") => 0.4
                    case ("Hospitalized", "Deceased") => 0.5
                    case _ => 0.05
                }
        }
    }
}
