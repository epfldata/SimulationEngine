package GLOBAL {

class Dummy;

}

package object GLOBAL {
  var silent = false
  val rnd = util.Random

  def mapopt[A,B](l: List[A], f: A => Option[B]) : List[B] =
    l.flatMap((a: A) => f(a) match {
      case Some(b) => List(b)
      case None    => List()
    })
}

package object CONSTANTS {

  val TICKS_TIMER_PER_MONTH: Int = 30 //Change this to change the timer rate

  val WHEAT_SEEDS_PER_HA: Double = 150 // in kg per ha
  val WHEAT_PRODUCED_PER_HA: Double = 6000 // in kg per ha
  val HA_PER_WORKER: Int = 20
  val CONVERSION_WHEAT_FLOUR: Double = 0.8
  val KG_CO2_PER_WHEAT_CROP_HA: Double = 1900
  
  var workercounter: Int = 0
  
  //If timer tick = 1 per month, last 2 month. If 2x faster TICKS_TIMER_PER_MONTH -> Still last 2 month (but requires 4 ticks)
  val WHEAT_EXPIRY_TIMER_IN_MONTH = 2 * TICKS_TIMER_PER_MONTH 
  val FERTILIZER_EXPIRY_TIMER_IN_MONTH = 3 * TICKS_TIMER_PER_MONTH 


  val WHEAT_PROD_DURATION = 12 * TICKS_TIMER_PER_MONTH
  val FERTILIZER_PROD_DURATION = 1 * TICKS_TIMER_PER_MONTH
}


