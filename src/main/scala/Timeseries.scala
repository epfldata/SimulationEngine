package Timeseries {


class Timeseries[T](_from: Int, _to: Int, _fn: Int => T) {
  def from = _from
  def to = _to
  def fn = _fn

  def this(v: Vector[T]) = this(0, v.length - 1, t => v(t))

  def toSeq() : IndexedSeq[T] = for(i <- _from to _to) yield _fn(i)

  override def toString() = (_from, _to, toSeq).toString

  def map[U](f: T => U) = new Timeseries[U](_from, _to, (t: Int) => f(_fn(t)))

  def apply(t: Int) = _fn(t)

  def window(window_size: Int) : Timeseries[IndexedSeq[T]] = {
    assert(window_size >= 1);
    assert(_to - _from + 1 >= window_size);

    def w(t: Int) = for(i <- 1 to window_size) yield _fn(t - window_size + i)

    new Timeseries(_from + window_size - 1, _to, w)
  }

  def start_from(start: Int) = {
    assert(start >= _from);
    new Timeseries(start, _to, _fn)
  }

  def end_at(end: Int) = {
    assert(end <= _to);
    new Timeseries(_from, end, _fn)
  }

  def tail(ticks: Int) = {
    val new_start = _to - ticks + 1;
    start_from(new_start)
  }
}


abstract class MyDistribution {
  def expectation: Double
}
case class TrivialDistribution(v: Double) extends MyDistribution {
  def expectation = v
}
class ListDistribution(l: List[Double]) extends MyDistribution {
  def expectation = l.sum / l.length
}


} // package Timeseries


package object Timeseries {

def argmin[T <% Ordered[T]](_from: Int, _to: Int, f: Int => T) : Int = {
  assert(_to >= _from);

  var best_i = _from;
  var best_v = f(_from);
  for(i <- (_from + 1) to _to) {
    val v = f(i);
    if(v < best_v) { best_v = v; best_i = i; }
  };
  best_i
}


def sum_grp[T](ts: Timeseries[List[T]], f: T => Int) = ts.map(_.map(f).sum)

def ts_sum[T : Numeric](ts: Timeseries[T]) =
  (for(x <- ts.from to ts.to) yield ts.fn(x)).sum

def smoothe[T : Numeric](ts: Timeseries[T], window_size: Int) =
  ts.window(window_size).map((x: Seq[T]) =>
    implicitly[Numeric[T]].toDouble(x.sum) / window_size)


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



def forecast[T: Numeric](ts: Timeseries[T]) : Timeseries[Double] = {
  val sts  = smoothe(ts, 3);
  val avg : Double = sts(ts.to - 1);

  new Timeseries(ts.to, (1.0/0).toInt, _ => avg) 
}

def rms_volatility(ts: Timeseries[Double]) : Double = {
  var sm = 0.0;
  for(t <- ts.from to (ts.to - 1)) {
    val d = ts(t) - ts(t + 1);
    sm += d*d
  };
  sm
}



def main_periodicity[T: Numeric](ts: Timeseries[T], max_period: Int) : Int = {
  val p = argmin(1, math.min(max_period, ts.to - ts.from + 1), (i: Int) => {
    val sts = smoothe(ts, i);
    val start = math.max(sts.from, sts.to - 30);
    rms_volatility(sts.start_from(start));
  });

  if(! GLOBAL.silent)
    println("main_periodicity("+ts+" = "+p);

  p
}

def super_forecast[T: Numeric](ts: Timeseries[T]) =
  smoothe(ts, main_periodicity(ts, 10))


type UncertainTimeseries = Int => MyDistribution


} // package object Timeseries


