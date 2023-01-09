package example
package epidemic.evalNPI

import breeze.stats.distributions.Gamma

object DiseaseParameter {
    val R0 = 2.6

    // units of day
    private val day = 1

    val incubationPeriod = 5.1 * day
    val symptomaticLatentPeriod = 4.6 * day
    val asymptomaticLatentPeriod = 7 * day

    val infectiousAlpha = 0.25
    val infectiousBeta = 1

    val hospitalizationDelayMean = 5 * day

    val infectiousSelfIsolateRatio = 2/3

    val symptomaticInfectiousnessScale = 2
    val hospitalizationCritical = 0.3
    val criticalDeath = 0.5

    // People older than 60 are 2x likely get infected
    val ageSkew = 2
    val ageThreshold = 60

    // People symptomatic are 2x likely to infect others
    val symptomaticSkew = 2

    val hospitalDays = 8 * day
    val asymptomaticRecovery = 21 * day
    val mildRecover = 14 * day

    // unit: days
    def stateDuration(health: String): Int = {
        health match {
            case "Infectious" => List(Random.nextGaussian() + 10, 2, 15).sorted.tail.head.toInt
            case "Hospitalized" => List(Random.nextGaussian() + 7, 2, 20).sorted.tail.head.toInt
            case "Exposed" => List(Random.nextGaussian() + 5, 3, 10).sorted.tail.head.toInt
        }
    }

    def infectiousness(health: String, symptomatic: Boolean): Double = {
        if (health == "Infectious") {
            var gd = Gamma(infectiousAlpha, infectiousBeta).draw()
            if (symptomatic){
                gd = gd * symptomaticSkew
            }
            gd
        } else {
            0
        }
    }

    def vulnerabilityLevel(age: Int): String = {
        if (age >= ageThreshold){
            "high"
        } else {
            "low"
        }
    }
}