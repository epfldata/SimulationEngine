package test.custMacros

import scala.collection.mutable.ListBuffer
import custMacros.liftMethod

object liftMethodExample extends App {

    val liftedMethod = liftMethod {
        def getMessages(f: Int): ListBuffer[Int] = {
            val l = ListBuffer[Int]()
            l.append(f)
            l
        }
    }

    println(liftedMethod)

    // Foo won't be quantified with the full path
    val liftedMethod2 = liftMethod {
        def d(): Unit = {
            val foo = new Foo()
            foo.run()
        }
    }

    println(liftedMethod2)

    // incorrect. Not a method
    // Will still be lifted, but the result is incorrect
    val liftedMethod3 = liftMethod {
        val foo = new Foo()
        foo.run()
    }

    println(liftedMethod3)
}
