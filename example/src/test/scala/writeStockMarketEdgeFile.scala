package example
import java.io._

object writeStockMarketEdgeFile {
  def main(args: Array[String]): Unit = {
   val markets: Int = args(0).toInt
   val tradersPerMarket: Int = args(1).toInt

   val pw = new PrintWriter(new FileOutputStream(new File(f"stockMarket_${markets}_${tradersPerMarket}.txt"),false))
   var market_id: Int = 0
   Range(0, markets).foreach(_ => {
      val traders = Range(market_id+1, market_id+1+tradersPerMarket)
      // market 
      pw.write(f"[$market_id,0,[${traders.map(i => f"[$i,0]").mkString(",")}]]\n")
      pw.flush()
      // traders. Each trader is connected to its market
      traders.foreach(t => {
         pw.write(f"[$t,0,[$market_id,0]]\n")
         pw.flush()
      })
      market_id = market_id+tradersPerMarket+1
   })
    
   pw.close()
 }  
}