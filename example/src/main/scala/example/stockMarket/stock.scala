package example
package stockMarket

// Artificial economic life: a simple model of a stockmarket
class Stock(var priceAdjustmentFactor: Double) {
    private var prices: ListBuffer[Double] = new ListBuffer()
    private var period: Int = 0
    private var currentPrice: Double = -1

    // Update 10-day-average and return whether the updated val has increased
    private val updateLastAvg10: trackLastAvg = new trackLastAvg(10)
    // Update 100-day-average and return whether the updated val has increased
    private val updateLastAvg100: trackLastAvg = new trackLastAvg(100)


    class trackLastAvg(val window: Int) {
        private var lastAvg: Option[Double] = None

        // return whether the new avg has increased comparing with the prev avg
        def update: Option[Boolean] = {
            // moving window
            val calculated_avg: Option[Double] = if (period > window) {
                Some(prices.slice(period-window, period).sum/window)
            } else {
                None
            }

            var ans: Boolean = false
            (calculated_avg, lastAvg) match {
                case (None, _) => None
                case (Some(x), None) => {
                    lastAvg = calculated_avg
                    None
                }
                case (Some(x), Some(y)) => {
                    if (x > y){
                        ans = true
                    }
                    lastAvg = calculated_avg
                    Some(ans)
                }
            }
        }
    }

    def addPrice(p: Double): List[Option[Boolean]] = {
        currentPrice = p
        period += 1
        prices.append(p)
        val dividendIncrease: Option[Boolean] = Some(scala.util.Random.nextBoolean)
        val recent10AvgInc: Option[Boolean] = updateLastAvg10.update
        val recent100AvgInc: Option[Boolean] = updateLastAvg100.update
        // println("Market state is " + List(dividendIncrease, recent10AvgInc, recent100AvgInc))
        // Market state encodes both short-term and long-term information
        List(dividendIncrease, recent10AvgInc, recent100AvgInc)
    }

    // p(t+1) = p(t) * (1 + z*(buy - sell))
    // z*(buy - sell) << 1
    def priceAdjustment(buy: Int, sell: Int): Double = {
        assert(currentPrice>0)
        assert(priceAdjustmentFactor*(buy - sell) > -1)
        currentPrice * (1 + priceAdjustmentFactor*(buy - sell))
    }
}