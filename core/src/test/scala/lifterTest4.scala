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
 * Test messaging for override methods
 */

@lift
class Student(var teacher: Teacher) extends Person {

    def override_work(): Unit = {
        println("Study at school")
    }

    def main(): Unit = {
        while (true) {
            work()
            // Ask what does the teacher do
            // teacher.work() would invoke Person.work() instead
            asyncMessage[Unit](() => teacher.override_work())
            waitAndReply(1)
        }
    }
}

class lifterTest4 extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    // "Calling an override method from another agent" should "invoke the right function" in {
        
    //     val teacherClass: ClassWithObject[Teacher] = Teacher.reflect(IR)
    //     val studentClass: ClassWithObject[Student] = Student.reflect(IR)

    //     val liftedMain = meta.classLifting.liteLift {
    //         def apply(): List[Actor] = {
    //             val teacher = new Teacher()
    //             val student = new Student(teacher)
    //             List(teacher, student)
    //         }
    //     }

    //     compileSims(List(teacherClass, studentClass), 
    //         mainInit = Some(liftedMain), 
    //         initPkgName = Some(this.getClass().getPackage().getName()+".inheritance2"),
    //         destFolder = "core/src/test/scala/generated/inheritance2/")
    // }

    "Calling a remote overriden method" should "invoke the child method" in {
        val agents = generated.meta.test.inheritance2.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
    }
}