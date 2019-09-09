package ecosim.code

package object twospecies {
  import ecosim.global
  import multithreading._
  import multithreading.mutable._

  var glob_simu: Option[Simulation[Int]] = None

  def experiment(
      prey_spawn_intv: Int,
      predator_spawn_intv: Int,
      predator_eat_intv: Int,
      until: Int
  ): Unit = {
    val prey_names = List("Nemo", "Dory", "Arielle", "Shrimp")

    val simu = new Simulation[Int](
      prey_names.map(n => new Prey(n, prey_spawn_intv)) ++
        List(new Predator("Sharko", predator_spawn_intv, predator_eat_intv))
    ) with Undertaker[ParallelExecutor[Int, Thread[Int]]]

    glob_simu = Some(simu)

    var t = 0
    while (t <= until) {
      simu.run_until(t + 5) match {
        case Some((_, t0)) => t = t0
        case None          => t = until + 1 // exit the while loop
      }

      val num_prey = animals().count(_.isInstanceOf[Prey])
      val num_pred = animals().count(_.isInstanceOf[Predator])
      println(
        "t = " + t + "  #prey = " + num_prey +
          "   #predators = " + num_pred)
    }
  }

  def animals(): List[Species] =
    glob_simu.get.get_sims.map(_.precursor.asInstanceOf[Species])

  /** Takes away the corpses. */
  trait Undertaker[A] extends Effects[A] {
    def apply(a: A): Option[A] =
      if (a.asInstanceOf[ParallelExecutor[Int, Thread[Int]]]
            .precursor
            .asInstanceOf[Species]
            .alive) Some(a)
      else None
  }

  abstract class Species(
      val name: String,
      spawn_intv: Int
  ) extends ParallelExecutorPrecursor[Int, Thread[Int]] {

    val s: Species = this
    val pc = List(new MovementTP, new SpawningTP)
    val precursors: List[ThreadPrecursor[Int]] = pc
    var x = 0
    var y = 0
    var alive = true

    // Euclidean distance
    def distance(other: Species): Double = {
      val xd: Double = x - other.x
      val yd: Double = y - other.y
      math.sqrt(xd * xd + yd * yd)
    }

    def mycopy(): Species

    protected def copy_state_to(to: Species) {
      to.x = x
      to.y = y
    }

    class MovementTP extends ThreadPrecursor[Int] {

      val prog: Vector[SimpleInstruction] = compile(__forever(__wait(1), __do {
        x += global.rnd.nextInt(3) - 1
        x = math.max(x, -5)
        x = math.min(x, 5)
        y += global.rnd.nextInt(3) - 1
        y = math.max(y, -5)
        y = math.min(y, 5)
      }))
    }

    class SpawningTP extends ThreadPrecursor[Int] {

      val prog: Vector[SimpleInstruction] = compile(
        __forever(__wait(spawn_intv), __do {
          if (animals().length < 2000) {
            println(s + " spawns a child.")
            glob_simu.get.add(s.mycopy())
          }
        }))
    }
  }

  class Prey(n: String, i: Int) extends Species(n, i) {
    def mycopy(): Prey = {
      val p = new Prey("f(" + name + ")", i)
      copy_state_to(p)
      p
    }
  }

  class Predator(
      n: String,
      i: Int,
      eat_intv: Int
  ) extends Species(n, i) {

    override val precursors: List[ThreadPrecursor[Int]] = new EatingTP :: pc

    def mycopy(): Predator = {
      val p = new Predator("f(" + name + ")", i, eat_intv)
      copy_state_to(p)
      p
    }

    class EatingTP extends ThreadPrecursor[Int] {
      type Individual = ParallelExecutor[Int, Thread[Int]]

      val prog: Vector[SimpleInstruction] = compile(
        __forever(
          __wait(eat_intv),
          __do {

            val close_prey = animals().filter((s2: Species) =>
              s2.isInstanceOf[Prey] && (s.distance(s2) < 4))

            if (close_prey.isEmpty) {
              println(s + " dies of starvation.")

              s.alive = false
            } else {
              val prey = close_prey.head

              println("OMNOM: " + s + " eats " + prey)

              prey.alive = false
            }
          }
        ))
    }
  }
  /*
  code.twospecies.experiment(7, 11, 6, 10);
 */

}
