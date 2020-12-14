package generated.simulation

object util {
  private def truncDigit(num: Double): Double = {
      BigDecimal(num).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def displayTime(turn: Int, time: Double): String = {
    "(Time " + truncDigit(time) + " Turn " + turn + ")" 
  }
}
