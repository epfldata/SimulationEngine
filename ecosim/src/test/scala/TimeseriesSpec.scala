import ecosim.timeseries.{Timeseries, _}
import org.scalatest._

class TimeseriesSpec extends FlatSpec {

  "argmin" should "work" in {
    assert(argmin(-3, 4, (i: Int) => (i - 2) * (i - 2)) == 2)
  }

  "smoothe" should "work" in {
    assert({
      val ts = smoothe(new Timeseries(Vector(3, 5, 1, 10)), 2)
      (ts.from == 1) && (ts.to == 3) && (ts.toSeq == Vector(4.0, 3.0, 5.5))
    })
  }

  "main_periodicity" should "work" in {
    val ts = new Timeseries(Vector(8, 0, 0, 0, 8, 0, 0, 0, 8, 0))
    assert(rms_volatility(smoothe(ts, 2)) == 64.0)
    assert(rms_volatility(smoothe(ts, 4)) == 0)
    assert(main_periodicity(ts, 10) == 4)
  }
}
