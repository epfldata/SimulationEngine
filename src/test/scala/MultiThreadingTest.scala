/*import org.scalatest._
import code._
import code.multithreading._
import code.multithreading.mutable._

class MultiThreadingSpec extends FlatSpec {

  def tp(j: Int): ThreadPrecursor[Nothing] = new ThreadPrecursor {
    var i = 0
    val prog: Vector[SimpleInstruction] = compile(__forever(__wait(j), __do {
      i += 1
    }))
  }

  def term_tp(i: Int, j: Int): ThreadPrecursor[Nothing] = new ThreadPrecursor {
    var o = 0
    val prog: Vector[SimpleInstruction] = compile(__repeat(i, __wait(j), __do {
      o += j
    }))
  }

  "test1" should "succeed" in {
    val tps = List(tp(1), tp(2))
    val simu = new ConcreteParallelExecutorFromPrecursors[Int](tps, 0)

    simu.run_until(10)

    assert(tps(0).i == 10)
    assert(tps(1).i == 5)

    simu.run_until(12)

    assert(tps(0).i == 12)
    assert(tps(1).i == 6)

    simu.run_until(10)

    assert(tps(0).i == 12)
    assert(tps(1).i == 6)
  }

  "test2" should "succeed" in {
    val tps = List(term_tp(1, 3), term_tp(2, 2))
    val simu = new ConcreteParallelExecutorFromPrecursors[Int](tps, 0)

    assert(simu.run_until(2) == Some(3))
    assert(tps(0).o == 0)
    assert(tps(1).o == 2)

    assert(simu.run_until(3) == Some(4))
    assert(tps(0).o == 3)
    assert(tps(1).o == 2)

    assert(simu.run_until(4) == None)
    assert(tps(0).o == 3)
    assert(tps(1).o == 4)

    simu.run_until(30)
  }
}
 */
