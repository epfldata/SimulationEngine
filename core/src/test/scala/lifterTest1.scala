package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import scala.util.Random
import meta.runtime.Future
import scala.collection.mutable.ListBuffer
import squid.lib.transparencyPropagating

@lift
class AgentWithSpecialInst(val n: AgentWithSpecialInst) extends Actor {

    @transparencyPropagating
    def blockingMtd(): Boolean = {
        println(id + " processes blocking mtd!")
        waitRounds(1)
        println(id + " finishes processing!")
        true
    }

    def testForallOperation(): Boolean = {
        List(1, 2, 3, 4).forall(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        ListBuffer(1, 2, 3, 4).forall(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        Set(1, 2, 3, 4).forall(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        Vector(1, 2, 3, 4).forall(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
    }

    def testExistsOperation(): Boolean = {
        List(1, 2, 3, 4).exists(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        ListBuffer(1, 2, 3, 4).exists(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        Set(1, 2, 3, 4).exists(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
        Vector(1, 2, 3, 4).exists(_ => asyncCall(n.blockingMtd(), 1).isCompleted)
    }

    // .map and .flatMap only work for List
    def testMapOperation(): Boolean = {
        List(1, 2, 3, 4).map(_ => asyncCall(n.blockingMtd(), 1))
        List(1, 2, 3, 4).flatMap(_ => List(asyncCall(n.blockingMtd(), 1)))
        
        // Fail
        // ListBuffer(1, 2, 3, 4).map[Future[Boolean], ListBuffer[Future[Boolean]]](_ => asyncCall(n.blockingMtd(), 1))
        // Set(1, 2, 3, 4).map(_ => asyncCall(n.blockingMtd(), 1))
        // (1 to 10).map(_ => asyncCall(n.blockingMtd(), 1))
        true
    }

    def testForeachOperation(): Boolean = {
        List(1, 2, 3, 4).foreach(_ => asyncCall(n.blockingMtd(), 1))
        ListBuffer(1, 2, 3, 4).foreach(_ => asyncCall(n.blockingMtd(), 1))
        Set(1, 2, 3, 4).foreach(_ => asyncCall(n.blockingMtd(), 1))
        Vector(1, 2, 3, 4).foreach(_ => asyncCall(n.blockingMtd(), 1))
        1.to(10).foreach(_ => asyncCall(n.blockingMtd(), 1))
        // Array does not work
        // Array(1, 2, 3, 4).foreach(_ => asyncCall(n.blockingMtd(), 1))
        List(n).foreach(i => asyncCall(i.blockingMtd(), 1))
        Set(n).foreach(i => asyncCall(i.blockingMtd(), 1))
        Map(1 -> n).map(i => i._2).foreach(i => asyncCall(i.blockingMtd(), 1))
        true
    }

    def main(): Unit = {
        while (true){
            println("Inside agent "+id)
            if (n != null){
                n.blockingMtd()
            }
            waitAndReply(1)
        }
    }
}


class lifterTest1 extends FlatSpec with org.scalatest.Matchers {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Lift a blocking method" should "compile" in {
        val liftMyClass2: ClassWithObject[AgentWithSpecialInst] = AgentWithSpecialInst.reflect(IR)
        val liftedRes = new Lifter().apply(List(liftMyClass2)) 
        liftedRes._1.head.methods.foreach(x => {
            assert(!x.body.toString.contains("SpecialInstructions"))
        })

        val liftedMainClass = liftedRes._1.head.main
        assert(!liftedMainClass.toString.contains("SpecialInstructions"))
    }
}