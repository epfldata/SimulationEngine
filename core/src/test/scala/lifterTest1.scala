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
        waitLabel(Turn, 1)
        println(id + " finishes processing!")
        true
    }

    def testForallOperation(): Boolean = {
        List(1, 2, 3, 4).forall(_ => asyncSend(n.blockingMtd()).isCompleted)
        ListBuffer(1, 2, 3, 4).forall(_ => asyncSend(n.blockingMtd()).isCompleted)
        Set(1, 2, 3, 4).forall(_ => asyncSend(n.blockingMtd()).isCompleted)
        Vector(1, 2, 3, 4).forall(_ => asyncSend(n.blockingMtd()).isCompleted)
    }

    def testExistsOperation(): Boolean = {
        List(1, 2, 3, 4).exists(_ => asyncSend(n.blockingMtd()).isCompleted)
        ListBuffer(1, 2, 3, 4).exists(_ => asyncSend(n.blockingMtd()).isCompleted)
        Set(1, 2, 3, 4).exists(_ => asyncSend(n.blockingMtd()).isCompleted)
        Vector(1, 2, 3, 4).exists(_ => asyncSend(n.blockingMtd()).isCompleted)
    }

    // .map and .flatMap only work for List
    def testMapOperation(): Boolean = {
        List(1, 2, 3, 4).map(_ => asyncSend(n.blockingMtd()))
        List(1, 2, 3, 4).flatMap(_ => List(asyncSend(n.blockingMtd())))
        
        // Fail
        // ListBuffer(1, 2, 3, 4).map[Future[Boolean], ListBuffer[Future[Boolean]]](_ => asyncSend(n.blockingMtd()))
        // Set(1, 2, 3, 4).map(_ => asyncSend(n.blockingMtd()))
        // (1 to 10).map(_ => asyncSend(n.blockingMtd()))
        true
    }

    def testForeachOperation(): Boolean = {
        List(1, 2, 3, 4).foreach(_ => asyncSend(n.blockingMtd()))
        ListBuffer(1, 2, 3, 4).foreach(_ => asyncSend(n.blockingMtd()))
        Set(1, 2, 3, 4).foreach(_ => asyncSend(n.blockingMtd()))
        Vector(1, 2, 3, 4).foreach(_ => asyncSend(n.blockingMtd()))
        1.to(10).foreach(_ => asyncSend(n.blockingMtd()))
        // Array does not work
        // Array(1, 2, 3, 4).foreach(_ => asyncSend(n.blockingMtd()))
        List(n).foreach(i => asyncSend(i.blockingMtd()))
        Set(n).foreach(i => asyncSend(i.blockingMtd()))
        Map(1 -> n).map(i => i._2).foreach(i => asyncSend(i.blockingMtd()))
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