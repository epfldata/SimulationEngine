import ecosim.code
import ecosim.code._
import org.scalatest._

case class ExecSim(prog: Vector[SimpleInstruction]) {
  var pos = 0
  var time = 0

  def exec(until: Int): Option[Int] = {
    val (p, t, n) = code.exec(prog, pos, time, until)
    pos = p
    time = t
    n
  }
}

case class ExecSimDouble(prog: Vector[SimpleInstruction]) {
  var pos = 0
  var time = 0.0

  def exec(until: Double): Option[Double] = {
    val (p, t, n) = code.exec[Double](prog, pos, time, until)
    pos = p
    time = t
    n
  }
}

class CodeSpec extends FlatSpec {
  def execf(s: ExecSim, u: Int): Option[Int] = s.exec(u)
  def execfd(s: ExecSimDouble, u: Double): Option[Double] = s.exec(u)
  def wprog[T](w: T): Vector[SimpleInstruction] = compile(__forever(__wait(w)))

  "exec" should "stop after the last instruction that can still be finished no later then the end time" in {
    assert(exec(wprog(11), 0, 0, 10) == (0, 0, Some(11)))
    assert(exec(wprog(5), 0, 0, 11) == (0, 10, Some(15)))
  }

  "execp" should "stop after the last instruction that can still be finished no later then the end time" in {
    {
      val sim = ExecSim(wprog(11))
      val n = execp[ExecSim, Int](Vector(sim), execf, 0, 10)

      assert((sim.pos, sim.time, n) == (0, 0, Some(11)))
    }
    {
      val sim = ExecSim(wprog(5))
      val n = execp[ExecSim, Int](Vector(sim), execf, 0, 11)

      assert((sim.pos, sim.time, n) == (0, 10, Some(15)))
    }
  }

  "execp" should "allow composition even is durations are mismatched" in {
    {
      var s = ""

      def wprog[T](w: T, n: String) =
        compile(__forever(__wait(w), __do({ s += n })))

      val s1 = ExecSimDouble(wprog(1.0 / 3, "a"))
      val s2 = ExecSimDouble(wprog(1.0 / 2, "b"))

      val n = execp[ExecSimDouble, Double](Vector(s1, s2), execfd, 0, 5.6).get
      assert(
        (s1.pos, s1.time, s2.pos, s2.time) ==
          (0, 5.333333333333332, 0, 5.5))
      assert(n == 5.666666666666665)

      val n2 = execp[ExecSimDouble, Double](Vector(s1, s2), execfd, n, 9.6).get
      assert(n2 == 9.666666666666666)
      assert(
        (s1.pos, s1.time, s2.pos, s2.time) ==
          (0, 9.333333333333332, 0, 9.5))
      assert(s == "abaababaababaababaababaababaababaababaababaabab")
    }
    {
      var s = ""

      def wprog[T](w: T, n: String) =
        compile(__forever(__wait(w), __do({ s += n })))

      val s1 = ExecSimDouble(wprog(1.0 / 3, "a"))
      val s2 = ExecSimDouble(wprog(1.0 / 2, "b"))

      val n2 = execp[ExecSimDouble, Double](Vector(s1, s2), execfd, 0, 9.6).get
      assert(n2 == 9.666666666666666)
      assert(
        (s1.pos, s1.time, s2.pos, s2.time) ==
          (0, 9.333333333333332, 0, 9.5))
      assert(s == "abaababaababaababaababaababaababaababaababaabab")
    }
  }

  "Terminating programs" should "work" in {
    assert(exec(Vector(__wait(1)), 0, 0, 10) == (1, 1, None))
    assert(exec(Vector(__wait(1), __wait(7)), 0, 0, 5) == (1, 1, Some(8)))
  }

  "Nonterminating programs" should "be halted in time" in {
    assert(exec(compile(__forever(__wait(1))), 0, 0, 5) == (0, 5, Some(6)))
    assert(exec(compile(__forever(__wait(2))), 0, 0, 5) == (0, 4, Some(6)))
  }

  "Side effects" should "work" in {
    var a = 0
    assert(exec(Vector(__do { a += 1 }), 0, 0, 0) == (1, 0, None))
    assert(a == 1)
  }

  "My code" should "work with non-integer waits" in {
    var s = ""
    val prog = {
      var i = 0
      Vector(
        __do {
          s = s + "H;"
          i = 0
        },
        __do {
          s = s + i + ";"
          i += 1
        },
        __goto(i < 3, 1),
        __wait(2.3),
        __goto(cond = true, 0)
      )
    }

    assert(exec(prog, 0, 0.0, 5.0) == (3, 4.6, Some(6.8999999999999995)))
    assert(s == "H;0;1;2;H;0;1;2;H;0;1;2;")
    assert(exec(prog, 3, 4.6, 7.0) == (3, 6.8999999999999995, Some(9.2)))
    assert(s == "H;0;1;2;H;0;1;2;H;0;1;2;H;0;1;2;")
  }

  it should "pass test 2" in {
    var a = 0
    val prog2 = {
      var i = 0
      compile(
        __repeat(1,
                 __do { i = 0 },
                 __dowhile(
                   __do { a += 1 },
                   __wait(1)
                 )({ i += 1; i < 15 }))
      )
    }

    assert(exec(prog2, 0, 0, 10) == (2, 10, Some(11)))
    assert(exec(prog2, 2, 10, 20) == (5, 15, None))

    assert(a == 15)
  }

  "The program that adds to a string in all kind of weird places" should "work" in {
    var s = ""
    val prog3 = compile(
      __dowhile(
        __do { s += "a" },
        __repeat(3, __do { s += "d" }, __repeat({ s += "k"; 2 }, __do {
          s += "b"
        }, __wait(1)), __do { s += "e" }),
        __do { s += "c" },
        __repeat(3, __do { s += "f" }, __wait(1))
      )(_cond = true)
    )
    assert(exec(prog3, 0, 0, 20) == (3, 20, Some(21)))
    assert(s == "adbkbkedbkbkedbkbkecfffadbkbkedbkbkedbkbkecfffadbkbkedb")
  }

  "The parallel ordering test" should "succeed" in {
    var s = ""
    val p1 = compile(__forever(__do { s += "1" }, __wait(5)))
    val p2 = compile(__forever(__do { s += "2" }, __wait(3)))

    val v = List(ExecSim(p1), ExecSim(p2))
    val v2 = execp(v, execf, 0, 20)

    assert(s == "122122121221")
  }
}
