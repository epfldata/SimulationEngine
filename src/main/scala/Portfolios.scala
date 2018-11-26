
import breeze.stats.distributions._


/*
import GLOBAL._
import breeze.linalg._
import breeze.plot._

def tst() {
  for(k <- 1 to 3) {
    var l = List[Int]();
    for(it <- 1 to 1000000) {
      var x = 0;
      for(it2 <- 1 to k) x += math.log(rnd.nextInt(100000)).toInt;
      l = x :: l;
    }

    val m = l.groupBy((x: Int) => x).mapValues((x: List[Int]) => x.length);
    val x = DenseVector((k*4 to k*20).toArray).map(_.toDouble);
    val y = DenseVector((k*4 to k*20).toArray).map(
      (x: Int) => m.getOrElse(x, 0).toDouble);

    val f = Figure("Foo");
    val p = f.subplot(0);

    p += plot(x, y);
    f.refresh;
  }
}


  val runs = 10000;
  var l = List[Double]();
  for(k <- 1 to runs) { // simulation runs
    var x = 100.0;
    for(i <- 1 to 2) {
      if(rnd.nextInt(2) == 0) x *= 1.5
      else x *= 0.5
    }
    l = x :: l;
  };
  val m = l.groupBy((x: Double) => x).mapValues(
    (x: List[Double]) => x.length.toDouble/runs);

  val e = m.toList.map(t => t._1*t._2).sum




  val g = new Gaussian(.1, .15);

  val runs = 100;
  var l = List[Double]();
  for(k <- 1 to runs) { // simulation runs
    var x = 1.0;
    for(i <- 1 to 10) {
      val y: Double = g.sample(1)(0);

      x *= (1+y)
    }
    l = x :: l;
  };

  val annual_interest = l.map(math.log(_)/10);

  annual_interest.map((x: Double) => math.pow(math.exp(x), 10))

  annual_interest.sum / runs;


  def Z(T: Double, B: Double, Rg: Double, n: Int, S: Double) =
    (math.log(T / B) - math.log(1 + Rg) * n) / (S * math.sqrt(n))
*/


