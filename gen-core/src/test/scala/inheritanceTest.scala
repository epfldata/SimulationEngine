package generated.core

import meta.API._
import org.scalatest.Suites
import org.scalatest.{DoNotDiscover, FunSuite}

@DoNotDiscover
class RootNonlifted extends FunSuite{
  // Messages are processed randomly, so both lists are possible.
  class Fixture {
    val agents = generated.core.test.inheritance.InitData()
    val totalRounds: Int = 5
    val c = new SimulationConfig(agents, totalRounds)
    val finalAgents = StartSimulation[BaseMessagingLayer.type](c).sims
  }

  def fixture = new Fixture

  test("Overriding a method inherited from a non-lifted class with special instructions executes the definition in child"){
    val f = fixture
    val teacher = f.finalAgents(0).asInstanceOf[generated.core.test.inheritance.Teacher]
    assert(teacher.workplace=="School")
  }

  test("Overriding a method inherited from a non-lifted class with special instructions does not support reflection"){
    val f = fixture
    val teacher = f.finalAgents(0).asInstanceOf[generated.core.test.inheritance.Teacher]
    // Does not support reflection if the overriden mtd contains special inst.
    // assert(teacher.work=="Self-Employed")
    assert(teacher.work=="School")
  }

  test("Overriding a method inherited from a non-lifted class without special instructions executes the definition in child"){
    val f = fixture
    val worker = f.finalAgents(1).asInstanceOf[generated.core.test.inheritance.Worker]
    assert(worker.workplace=="Factory")
  }

  test("Overriding a method inherited from a non-lifted class without special instructions supports reflection"){
    val f = fixture
    val worker = f.finalAgents(1).asInstanceOf[generated.core.test.inheritance.Worker]
    assert(worker.work=="Factory")
  }

  test("Calling an overriden method should invoke the child definition, regardless of reflection support"){
    val f = fixture
    val student = f.finalAgents(2).asInstanceOf[generated.core.test.inheritance.Student]
    assert(student.neighborWorkplace=="School")
  }
}

@DoNotDiscover
class RootLifted extends FunSuite{
  // Messages are processed randomly, so both lists are possible.
  class Fixture {
    val agents = generated.core.test.inheritance2.InitData()
    val totalRounds: Int = 5
    val c = new SimulationConfig(agents, totalRounds)
    val finalAgents = StartSimulation[BaseMessagingLayer.type](c).sims.map(_.asInstanceOf[generated.core.test.inheritance2.Vehicle])
  }

  def fixture = new Fixture

  test("Overriding a method inherited from a non-lifted class should invoke the child definition"){
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
  }

  test("Calling an overriden method should invoke the child definition"){
    val f = fixture
    // vehicle
    assert(f.finalAgents(4).asInstanceOf[generated.core.test.inheritance2.CommunicatingVehicle].neighborPrices==List(20, 23, 20, 20))
  }

  test("Private attributes in parent classes no longer have modifier prefix") {
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


class InheritanceTest extends Suites (new RootNonlifted, new RootLifted)