package meta.example.supermarket

// Don't lift this class. Use var so totalSupply can be modified
/* The number of unit of food of different categories */
case class Category(var vegie: Int=0,
                      var meat: Int=0,
                      var snacks: Int=0,
                      var grain: Int=0)

/* Relative ratio of each type of customers */
case class Mix(var vegielover: Int,
               var meatlover: Int,
               var partylover: Int,
               var regularshopper: Int) {
  /* ratio adds each term with the preceding one,
  used for generating a random customer */
  def ratio(): Mix = {
    List(vegielover, meatlover, partylover, regularshopper)
    .scanLeft(0)(_+_).tail match {
      case List(a, b, c, d) => Mix(a, b, c, d)
    }
  }
}

// CustomerProfile won't work, can't include another word
object Profile {
  // Shopping lists of different customers
  def veganShopper = Category(vegie = 8, snacks = 1, grain = 1)
  def meatShopper = Category(vegie = 1, meat = 7, snacks = 1, grain = 1)
  def partyShopper = Category(vegie = 2, meat = 2, snacks = 5, grain = 1)
  def regularShopper = Category(vegie = 3, meat = 3, snacks = 1, grain = 3)

  // Mix of different customers
  def EPFL = Mix(vegielover = 1, meatlover = 3, partylover = 3, regularshopper = 3)
    .ratio()// Mix(1, 4, 7, 10)
  def Ecublens = Mix(2, 6, 2, 7).ratio()
}






