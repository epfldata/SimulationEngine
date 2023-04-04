package simulation.base
package test

import API._
import org.scalatest.Suites
import org.scalatest.{DoNotDiscover, FunSuite}

class InheritanceTest extends Suites (new InheritanceRootLifted, new InheritanceRootNonlifted)

class InheritanceRootNonlifted extends FunSuite{
  // Messages are processed randomly, so both lists are possible.
  class Fixture {
    val agents = generated.core.test.inheritance.InitData()
    val totalRounds: Int = 5
    val finalAgents = Simulate(agents, totalRounds).sims.toList
  }

  def fixture = new Fixture

  test("Parent values and method definitions should be shadowed"){
    val f = fixture
    val teacher = f.finalAgents(0).asInstanceOf[generated.core.test.inheritance.Teacher]
    assert(teacher.workplace=="School")
    assert(teacher.work=="School")

    val worker = f.finalAgents(1).asInstanceOf[generated.core.test.inheritance.Worker]
    assert(worker.workplace=="Factory")
    assert(worker.work=="Factory")
    
    val student = f.finalAgents(2).asInstanceOf[generated.core.test.inheritance.Student]
    assert(student.neighborWorkplace=="School")
  }
}

class InheritanceRootLifted extends FunSuite{
  // Messages are processed randomly, so both lists are possible.
  class Fixture {
    val agents = generated.core.test.inheritance2.InitData()
    val totalRounds: Int = 5
    val finalAgents = Simulate(agents, totalRounds).sims.map(_.asInstanceOf[generated.core.test.inheritance2.Vehicle]).toList
  }

  def fixture = new Fixture

  test("Parent values and definitions are shadowed"){
    val f = fixture
    // vehicle
    assert(f.finalAgents(0).price==20)
    assert(f.finalAgents(0).load==10)
    // shortDistanceTransport
    assert(f.finalAgents(1).price==25)
    assert(f.finalAgents(1).load==10)
    // bus
    assert(f.finalAgents(2).price==20)
    assert(f.finalAgents(2).load==30)
    // van
    assert(f.finalAgents(3).price==20)
    assert(f.finalAgents(3).load==10)
    // message takes 1 round to arrive, hence price for shortDistanceTransport has the old value 23
    assert(f.finalAgents(4).asInstanceOf[generated.core.test.inheritance2.CommunicatingVehicle].neighborPrices==List(20, 23, 20, 20))
  }

  test("Private attributes in parent classes are preserved in the generated code") {
      val f = fixture
      assertDoesNotCompile("f.finalAgents(0).asInstanceOf[generated.core.test.inheritance2.Vehicle].private_donot_copy==512")
    
      // Only accessible inside the agent
      assertDoesNotCompile("f.finalAgents(0).asInstanceOf[generated.core.test.inheritance2.Vehicle].donot_copy==512")
      
      assertDoesNotCompile("""f.finalAgents(0).asInstanceOf[generated.core.test.inheritance2.Vehicle].local_mtd=="Invisible!"""")
  }

  test("A child class should not inherit private variables or methods of the parent") {
      val f = fixture
      assertDoesNotCompile(
          "f.finalAgents(1).asInstanceOf[generated.core.test.inheritance2.ShortDistanceTransport].donot_copy"
      )

      assertDoesNotCompile(
          "f.finalAgents(1).asInstanceOf[generated.core.test.inheritance2.ShortDistanceTransport].donot_copy"
      )
      assertDoesNotCompile(
          "f.finalAgents(1).asInstanceOf[generated.core.test.inheritance2.ShortDistanceTransport].local_mtd"
      )
      assertDoesNotCompile(
          "f.finalAgents(2).asInstanceOf[generated.core.test.inheritance2.Bus].donot_copy"
      )
      assertDoesNotCompile(
          "f.finalAgents(3).asInstanceOf[generated.core.test.inheritance2.Van].donot_copy"
      )
  }
}

