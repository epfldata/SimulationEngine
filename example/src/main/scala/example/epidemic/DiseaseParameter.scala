package example.epidemic

object DiseaseParameter {
    val R0 = 2.6

    // units of day
    val day = 1

    val incubationPeriod = 5.1 * day
    val symptomaticLatentPeriod = 4.6 * day
    val asymptomaticLatentPeriod = 7 * day

    val infectiousAlpha = 0.25
    val infectiousBeta = 4

    val hospitalizationDelayMean = 5 * day

    val infectiousSelfIsolateRatio = 2/3

    val symptomaticInfectiousnessScale = 2
    val hospitalizationCritical = 0.3
    val criticalDeath = 0.5

    // People older than 60 are 2x likely get infected
    val ageSkew = 2
    val ageThreshold = 60

    // People sympotamatic are 2x likely to infect others
    val sympotamaticSkew = 2

    val hospitalDays = 8 * day
    val criticalDays = 16 * day
    val ICUDays = 10 * day
    val asymptomaticRecovery = 21 * day
    val mildRecover = 14 * day
}