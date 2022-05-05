package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import java.io.File

/**
 * Test override
 */
trait Person extends Actor {
    def work(): Unit = println("This is the parent class!")
}

@lift
class Worker() extends Person {
    // If both override_work and @override work are defined, then
    // the compiler uses the definition in override_ as final
    def override_work(): Unit = {
        println("Work in a factory.")
    }

    override def work(): Unit = {
        override_work()
    }

    def main(): Unit = {
        while (true) {
            work()
            waitAndReply(1)
        }
    }
}

@lift
class Teacher() extends Person {
    def override_work(): Unit = {
        println("Teach in a classroom. ")
        waitLabel(Turn, 1)
    }

    override def work(): Unit = {
        override_work()
    }

    def main(): Unit = {
        while (true) {
            work()
            waitAndReply(1)
        }
    }
}

class lifterTest3 extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Lifting agents with overriding keywords" should "compile" in {
        val workerClass: ClassWithObject[Worker] = Worker.reflect(IR)
        val teacherClass: ClassWithObject[Teacher] = Teacher.reflect(IR)

        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val teacher = new Teacher()
                val worker = new Worker()
                List(teacher, worker)
            }
        }

        // Add root agent to the lifter
        Lifter.rootAgents = "Person" :: Lifter.rootAgents
        compileSims(List(workerClass, teacherClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()+".inheritance"),
            destFolder = "core/src/test/scala/generated/inheritance/")
    }

    // "Calling overriden methods" should "invoke local methods" in {
    //     val agents = generated.meta.test.inheritance.InitData()
    //     val c = new SimulationConfig(agents, 5)
    //     val r = StartSimulation[BaseMessagingLayer.type](c)
    // }
}