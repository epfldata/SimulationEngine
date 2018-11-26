package code

package object twospecies {
import multithreading._
import multithreading.mutable._


var glob_simu : Option[Simulation[Int]] = None;


def animals() : List[Species] =
  glob_simu.get.get_sims.map(_.precursor.asInstanceOf[Species])


abstract class Species(
  val name: String,
  spawn_intv: Int
) extends ParallelExecutorPrecursor[Int, Thread[Int]] {

  var x = 0
  var y = 0
  var alive = true

  protected def copy_state_to(to: Species) {
    to.x = x;
    to.y = y;
  }

  // Euclidean distance
  def distance(other: Species) = {
    val xd : Double = x - other.x;
    val yd : Double = y - other.y;
    math.sqrt(xd * xd + yd * yd)
  }

  def mycopy() : Species

  val s = this

  class MovementTP extends ThreadPrecursor[Int] {

    val prog = compile(__forever(__wait(1), __do{
      x += GLOBAL.rnd.nextInt(3) - 1;
      x = math.max(x, -5);
      x = math.min(x,  5);
      y += GLOBAL.rnd.nextInt(3) - 1;
      y = math.max(y, -5);
      y = math.min(y,  5);
    }))
  }

  class SpawningTP extends ThreadPrecursor[Int] {

    val prog = compile(__forever(__wait(spawn_intv), __do{
      if(animals().length < 2000) {
        println(s + " spawns a child.");
        glob_simu.get.add(s.mycopy());
      }
    }))
  }

  val pc = List(new MovementTP, new SpawningTP)
  val precursors = pc
}


class Prey(n: String, i: Int) extends Species(n, i) {
  def mycopy() = {
    val p = new Prey("f(" + name + ")", i);
    copy_state_to(p);
    p
  }
}


class Predator(
  n: String,
  i: Int,
  eat_intv: Int
) extends Species(n, i) {

  def mycopy() = {
    val p = new Predator("f(" + name + ")", i, eat_intv);
    copy_state_to(p);
    p
  }

  class EatingTP extends ThreadPrecursor[Int] {
    type Individual = ParallelExecutor[Int, Thread[Int]]

    val prog = compile(__forever(__wait(eat_intv), __do{

      val close_prey = animals().filter((s2: Species) =>
        (s2.isInstanceOf[Prey] && (s.distance(s2) < 4)));

      if(close_prey.isEmpty) {
        println(s + " dies of starvation.");

        s.alive = false;
      }
      else {
        val prey = close_prey.head;

        println("OMNOM: " + s + " eats " + prey);

        prey.alive = false;
      }
    }))
  }

  override val precursors = new EatingTP :: pc
}


/** Takes away the corpses. */
trait Undertaker[A] extends Effects[A] {
  def apply(a: A) : Option[A] =
    if(a.asInstanceOf[ParallelExecutor[Int, Thread[Int]]].precursor.asInstanceOf[Species].alive) Some(a) else None
}


def experiment(
  prey_spawn_intv: Int,
  predator_spawn_intv: Int,
  predator_eat_intv: Int,
  until: Int
) = {
  val prey_names = List("Nemo", "Dory", "Arielle", "Shrimp");

  val simu = new Simulation[Int](
    prey_names.map(n => new Prey(n, prey_spawn_intv)) ++
    List(new Predator("Sharko", predator_spawn_intv,
                      predator_eat_intv))
  ) with Undertaker[ParallelExecutor[Int, Thread[Int]]];

  glob_simu = Some(simu);

  var t = 0;
  while(t <= until) {
   simu.run_until(t + 5) match {
      case Some((_, t0)) => t = t0
      case None          => t = until + 1 // exit the while loop
    }

    val num_prey = animals().filter(_.isInstanceOf[Prey]    ).length;
    val num_pred = animals().filter(_.isInstanceOf[Predator]).length;
    println("t = " + t + "  #prey = " + num_prey +
            "   #predators = " + num_pred);
  }
}


/*
  code.twospecies.experiment(7, 11, 6, 10);
*/


} // end package object twospecies


