package meta.example.supermarket

case class categoryAmount(var Vegetable: Double=0.0,
                          var Meat: Double=0.0,
                          var Snack: Double=0.0,
                          var Grain: Double=0.0,
                          var Dairy: Double=0.0) {
  def +(bar: categoryAmount): categoryAmount = {
    categoryAmount(bar.Vegetable + Vegetable, bar.Meat+Meat,
      bar.Snack + Snack, bar.Grain + Grain, bar.Dairy + Dairy)
  }
}
