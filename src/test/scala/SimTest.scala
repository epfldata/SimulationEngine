import ecosim.code._
import ecosim.simulation.Sim
import org.scalatest._

class XFactory extends Sim {
  init(0)
  val time_needed = 4
  // BEGIN state addition
  var capital = 0
  // END state addition
  var i = 0

  def mycopy: XFactory = {
    val s = new XFactory
    this.copy_state_to(s)
    s
  }

  protected def copy_state_to(to: XFactory): Unit = {
    super.copy_state_to(to)
    to.capital = capital
    to.i = i
  }

  def algo =
    __forever(
      __do { print("buying consumables. "); capital -= 1; i = 0 },
      __dowhile(
        __wait(1),
        __do { print("paying salaries. "); capital -= 1; i += 1; }
      )({ i < time_needed }),
      __do { print("production complete! "); capital += 5 }
    )
}

class SimSpec extends FlatSpec {
  "Booby" should "work" in {
    class XFactory0 extends Sim {
      init(0)
      var capital = 0
      val time_needed = 4

      def algo =
        __forever(
          __do { print("buying consumables. "); capital -= 1 },
          __repeat(time_needed, __wait(1), __do {
            print("paying salaries. "); capital -= 1
          }),
          __do { print("production complete! "); capital += 5 }
        )
    }

    val f1 = new XFactory0

    f1.run_until(9)
    assert(f1.capital == -2)
    f1.run_until(10)
    assert(f1.capital == -3)
    f1.run_until(8)
    assert(f1.capital == -3)
  }

  "Copying a Sim" should "work" in {
    val f1 = new XFactory
    val f2 = new XFactory

    f1.run_until(9)
    f1.run_until(10)
    f1.run_until(8)
    assert(f1.capital == -3)

    val f1b = f1.mycopy
    val f2b = f2.mycopy

    f1.run_until(12)
    assert(f1.capital == -1)
    f1b.run_until(12)
    assert(f1.capital == -1)
    assert(f1b.capital == -1)
  }
}
