package ecosim

package object timeseries {
  type UncertainTimeseries = Int => MyDistribution

  def sum_grp[T](ts: Timeseries[List[T]], f: T => Int): Timeseries[Int] =
    ts.map(_.map(f).sum)

  def ts_sum[T: Numeric](ts: Timeseries[T]): T =
    (for (x <- ts.from to ts.to) yield ts.fn(x)).sum

  def forecast[T: Numeric](ts: Timeseries[T]): Timeseries[Double] = {
    val sts = smoothe(ts, 3)
    val avg: Double = sts(ts.to - 1)

    new Timeseries(ts.to, (1.0 / 0).toInt, _ => avg)
  }

  /*
def foo[U <: Seq[T], T](x: U, y: T) = x :+ y
def foo[B >: Seq[T], U <: Seq[T], T](x: U, y: T) : B = x :+ y
def bar[T <% Ordered[T]](x: T, y: T) = x < y
def bar[T : Ordering](x: T, y: T) = implicitly[Ordering[T]].lt(x, y)

def g[T : Fractional](a: T) = implicitly[Fractional[T]].div(a, implicitly[Fractional[T]].fromInt(4))
def g[T : Fractional](a: T, b: T)(implicit o: Fractional[T]) = o.div(a, b)
def g[T : Integral](a: T, b: T)(implicit o: Integral[T]) = o.quot(a, b)
def g[T : Numeric](l : List[T]) = l.sum
def f[T : Numeric](a: T, b: T) = implicitly[Numeric[T]].plus(a, b)
   */

  def smoothe[T: Numeric](ts: Timeseries[T],
                          window_size: Int): Timeseries[Double] =
    ts.window(window_size)
      .map((x: Seq[T]) => implicitly[Numeric[T]].toDouble(x.sum) / window_size)

  def super_forecast[T: Numeric](ts: Timeseries[T]): Timeseries[Double] =
    smoothe(ts, main_periodicity(ts, 10))

  def main_periodicity[T: Numeric](ts: Timeseries[T], max_period: Int): Int = {
    val p = argmin(1, math.min(max_period, ts.to - ts.from + 1), (i: Int) => {
      val sts = smoothe(ts, i)
      val start = math.max(sts.from, sts.to - 30)
      rms_volatility(sts.start_from(start))
    })

    if (!global.silent)
      println("main_periodicity(" + ts + " = " + p)

    p
  }

  def argmin[T](_from: Int, _to: Int, f: Int => T)(
      implicit ev$1: T => Ordered[T]): Int = {
    assert(_to >= _from)

    var best_i = _from
    var best_v = f(_from)
    for (i <- (_from + 1) to _to) {
      val v = f(i)
      if (v < best_v) { best_v = v; best_i = i; }
    }
    best_i
  }

  def rms_volatility(ts: Timeseries[Double]): Double = {
    var sm = 0.0
    for (t <- ts.from until ts.to) {
      val d = ts(t) - ts(t + 1)
      sm += d * d
    }
    sm
  }
}
