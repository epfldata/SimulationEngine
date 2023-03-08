package example
package stockMarket

// Artificial economic life: a simple model of a stockmarket
class Stock(var priceAdjustmentFactor: Double) {
    var prices: ListBuffer[Double] = new ListBuffer()
    private var period: Int = 0
    private var currentPrice: Double = -1

    private var lastDividend: Double = 0
    // Update 10-day-average and return whether the updated val has increased
    private val updateLastAvg10: trackLastAvg = new trackLastAvg(10)
    // Update 50-day-average and return whether the updated val has increased
    private val updateLastAvg50: trackLastAvg = new trackLastAvg(50)


    class trackLastAvg(val window: Int) {
        private var lastAvg: Option[Double] = None

        // return whether the new avg has increased comparing with the prev avg
        def update: Int = {
            // moving window
            val calculated_avg: Option[Double] = if (period > window) {
                Some(prices.slice(period-window, period).sum/window)
            } else {
                None
            }

            var ans: Int = 2
            (calculated_avg, lastAvg) match {
                case (None, _) => 0
                case (Some(x), None) => {
                    lastAvg = calculated_avg
                    0
                }
                case (Some(x), Some(y)) => {
                    if (x > y){
                        ans = 1
                    }
                    lastAvg = calculated_avg
                    ans
                }
            }
        }
    }

    def updateMarketInfo(p: Double, d: Double): List[Int] = {
        currentPrice = p
        period += 1
        prices.append(p)

        // Short-term info: whether dividend has increased. 0 means no dividend
        val dividendIncrease: Int = {
            if (d == 0){
                0
            } else {
                val lastDividend_copy = lastDividend
                lastDividend = d
                if(lastDividend_copy < d){
                    1
                } else {
                    2
                }
            }
        }

        // Mid-term info: whether 10-day avg has increased
        val recent10AvgInc = updateLastAvg10.update
        // Long-term info: whether 50-day avg has increased
        val recent50AvgInc = updateLastAvg50.update
        // println("Market state is " + List(dividendIncrease, recent10AvgInc, recent50AvgInc))
        // Market state encodes both short-term and long-term information
        List(dividendIncrease, recent10AvgInc, recent50AvgInc)
    }

    // p(t+1) = p(t) * (1 + z*(buy - sell))
    // z*(buy - sell) << 1
    def priceAdjustment(buy: Int, sell: Int): Double = {
        if(currentPrice<=0){
            100
        } else {
            currentPrice * (1 + priceAdjustmentFactor*(buy - sell))
        }
    }

    // Model the dividend stream (pay in cash) using a stochastic process
    def getDividend(): Double = {
        val x = 0.1* Random.nextGaussian()
        if (x < 0) 0 else x
    }
}