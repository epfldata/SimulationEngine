package meta.example.supermarket

case class categoryAmount(var Vegetable: Int = 0,
                          var Meat: Int = 0,
                          var Snack: Int =0,
                          var Grain: Int =0,
                          var Dairy: Int =0) {
  def +(bar: categoryAmount): categoryAmount = {
    categoryAmount(bar.Vegetable + Vegetable, bar.Meat+Meat,
      bar.Snack + Snack, bar.Grain + Grain, bar.Dairy + Dairy)
  }
}
