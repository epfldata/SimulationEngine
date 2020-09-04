package ecosim.timeseries

class Timeseries[T](_from: Int, _to: Int, _fn: Int => T) {
  def from: Int = _from
  def to: Int = _to
  def fn: Int => T = _fn

  def this(v: Vector[T]) = this(0, v.length - 1, t => v(t))

  override def toString: String = (_from, _to, toSeq).toString

  def toSeq: IndexedSeq[T] = for (i <- _from to _to) yield _fn(i)

  def map[U](f: T => U) = new Timeseries[U](_from, _to, (t: Int) => f(_fn(t)))

  def apply(t: Int): T = _fn(t)

  def window(window_size: Int): Timeseries[IndexedSeq[T]] = {
    assert(window_size >= 1)
    assert(_to - _from + 1 >= window_size)

    def w(t: Int) = for (i <- 1 to window_size) yield _fn(t - window_size + i)

    new Timeseries(_from + window_size - 1, _to, w)
  }

  def end_at(end: Int): Timeseries[T] = {
    assert(end <= _to)
    new Timeseries(_from, end, _fn)
  }

  def tail(ticks: Int): Timeseries[T] = {
    val new_start = _to - ticks + 1
    start_from(new_start)
  }

  def start_from(start: Int): Timeseries[T] = {
    assert(start >= _from)
    new Timeseries(start, _to, _fn)
  }
}
abstract class MyDistribution {
  def expectation: Double
}
case class TrivialDistribution(v: Double) extends MyDistribution {
  def expectation: Double = v
}

class ListDistribution(l: List[Double]) extends MyDistribution {
  def expectation: Double = l.sum / l.length
}
