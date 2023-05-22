package example
package stockMarket

// Artificial economic life: a simple model of a stockmarket
class Stock(var priceAdjustmentFactor: Double) {
    var prices: ListBuffer[Double] = new ListBuffer()
    private val INCREASE: Int = 1
    private val DECREASE: Int = 2
    private val NO_CHANGE: Int = 0
    
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
            // if not enough information, compare previous average
            val calculated_avg: Option[Double] = if (period > window) {
                Some(prices.slice(period-window, period).sum/window)
            } else {
                None
            }

            (calculated_avg, lastAvg) match {
                case (None, _) => NO_CHANGE
                case (Some(x), None) => {
                    lastAvg = calculated_avg
                    INCREASE
                }
                case (Some(x), Some(y)) => {
                    lastAvg = calculated_avg
                    if (x > y){
                        INCREASE
                    } else if (x == y) {
                        NO_CHANGE
                    } else {
                        DECREASE
                    }
                }
            }
        }
    }

    def updateMarketInfo(p: Double, d: Double): List[Int] = {
        currentPrice = p
        period += 1
        prices.append(p)

        // Short-term info: whether dividend has increased
        val dividendIncrease: Int = {
            val ans = if (d == lastDividend) {
                NO_CHANGE
            } else if (d > lastDividend) {
                INCREASE
            } else {
                DECREASE
            }
            lastDividend = d
            ans
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