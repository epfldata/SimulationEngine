package test.custMacros

import org.coroutines._
import scala.collection.mutable.ListBuffer

class Foo() {
    val messages: ListBuffer[Int] = new ListBuffer[Int]()

    def addNewMessage(f: Int): Unit = {
        messages.append(f)
    }

    def getMessages(): ListBuffer[Int] = {
        messages
    }

    def run() =  coroutine ((() => {
        while(true){
            println("Hi!")
            var cnt = 0

            while (cnt < 4) {
                if (scala.util.Random.nextBoolean()){
                    println("Inside the condition!")
                    val foo = (1 to 4).toList
                    addNewMessage(foo(2).asInstanceOf[Int])
                }
                yieldval(getMessages())
                cnt += 1
            }
        }
    }))
}

class CoroutineExample extends org.scalatest.FlatSpec {

    "Coroutine example" should "run" in {
        val ss =  new Foo()
        val x = call (ss.run()())

        var currentTurns = 0
        while (currentTurns < 10) {
            x.resume
            println(x.value)
            currentTurns += 1
        }
    }
}

