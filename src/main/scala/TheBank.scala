
package Owner {


object TheBank { // Could be a Factory[Debit => Credit]
  var capital = 0;
  var accounts = List[Owner]();
  var assets_interest_rate = 0.01;  // 1 percent
  private val profit_rate  = 0.01;

  def credit_rate(o: Owner) : Double = {
    /*
      This is the solution for
      E[interest earned] = E[money lost on business failure = debt]
      if(fail) -debt else debt*risk_reward = 0
      o.probfail * -debt + (1 - o.probfail) * debt * risk_reward = 0
    */
    val risk_reward = o.probfail / (1 - o.probfail);

    assets_interest_rate + risk_reward + profit_rate
  }

  def step() {
    var savings = 0.0;
    var loans   = 0.0;

    for(o <- accounts) {
      val c: Int = o.capital;

      val interest =
        (if(c >= 0) { savings += c; c * assets_interest_rate }
         else       { loans   += c; c * credit_rate(o) }).toInt;

      o.capital += interest;  // sign of interest is just right
      capital   -= interest;

      if(o.probfail >= 0.5) println("Bankrupt " + o);
    }

    // set interest levels:
    val cost = savings * assets_interest_rate;
    val income = loans * (assets_interest_rate + profit_rate);

    // savings * assets_interest_rate =
    //   loans * assets_interest_rate + loans * profit_rate);

    assets_interest_rate = loans * profit_rate / (savings - loans);
  }
}


} // end package Owner
