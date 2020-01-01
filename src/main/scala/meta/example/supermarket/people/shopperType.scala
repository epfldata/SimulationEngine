package meta.example.supermarket.people

// Impulse shopper and target shopper are expensive shoppers 50% of time
// Random shopping list of random amount
trait ImpulseShopper {
  val priceConscious: Double = 0.5
  val needBased: Double = 0
}

// Shopping list based only on bargains
trait BargainShopper {
  val priceConscious: Double = 1
  val needBased: Double = 0.5
}

// Fixed shopping list based on needs
trait TargetShopper {
  val priceConscious: Double = 0.5
  val needBased: Double = 1
}