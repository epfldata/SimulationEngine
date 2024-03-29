package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test override
 */
trait Person extends Actor {
    def work(): String = {
        // println("This is the parent class!")
        "Self-Employed"
    }
}

@lift
class Worker() extends Person {
    var workplace: String = ""

    override def work(): String = {
        "Factory"
    }

    def main(): Unit = {
        markOverride("work")
        while (true) {
            workplace = work()
            waitAndReply(1)
        }
    }
}

@lift
class Teacher() extends Person {
    var workplace: String = ""
    override def work(): String = {
        // With the special instruction wait, workplace is set to Self-employed 
        // invoking the inherited method instead
        // waitRounds(1)
        "School"
    }

    def main(): Unit = {
        markOverride("work")
        while (true) {
            workplace = work()
            waitAndReply(1)
        }
    }
}

@lift
class Student(var neighbor: Teacher) extends Person {
    var neighborWorkplace: String = ""
    private var f: Future[String] = null

    def main(): Unit = {
        while (true) {
            // Ask what does the neighbor do
            f = asyncCall(() => neighbor.work(), 1)
            while (!f.isCompleted){
                waitAndReply(1)
            }
            neighborWorkplace = f.popValue.get
        }
    }
}

class InheritanceTestRootNonlifted extends FlatSpec {
    import meta.classLifting.Lifter

    "Lifting agents with overriding keywords" should "compile" in {
        val teacherClass: ClassWithObject[Teacher] = Teacher.reflect(IR)
        val workerClass: ClassWithObject[Worker] = Worker.reflect(IR)
        val studentClass: ClassWithObject[Student] = Student.reflect(IR)

        val liftedMain = meta.classLifting.liteLift {
            def apply(): IndexedSeq[Actor] = {
                val teacher = new Teacher()
                val worker = new Worker()
                val student = new Student(teacher)
                Vector(teacher, worker, student)
            }
        }

        // Add root agent to the lifter
        Lifter.rootAgents = "Person" :: Lifter.rootAgents
        compileSims(List(workerClass, teacherClass, studentClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.inheritance"),
            destFolder = "gen-core/src/main/scala/inheritance/")
    }
}