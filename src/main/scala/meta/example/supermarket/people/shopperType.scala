package meta.example.supermarket.people

// Impulse shopper and target shopper are expensive shoppers 50% of time
// Random shopping list of random amount
trait ImpulseShopper {
  val priceConscious: Double = 0.5
  val needBased: Boolean = false
}

// Shopping list based only on bargains
trait BargainShopper {
  val priceConscious: Double = 1
  val needBased: Boolean = false
}

// Fixed shopping list based on needs
trait TargetShopper {
  val priceConscious: Double = 0.5
  val needBased: Boolean = true
}

object shopperTypeSummary {
  val names: Vector[String] = Vector(
    "ImpulseShopper",
    "BargainShopper",
    "TargetShopper"
  )
}