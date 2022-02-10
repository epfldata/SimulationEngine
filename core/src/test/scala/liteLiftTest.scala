package meta.test.liteLift

import scala.collection.mutable.ListBuffer
import org.scalatest.{Matchers, FlatSpec}
import meta.classLifting.liteLift
import squid.quasi.lift


class Foo() {
    def run(): Unit = {
        println("Test classpath")
    }
}

class liteLiftExample extends org.scalatest.FlatSpec with Matchers {

    "lift method" should "return the full path for imported library and capture arguments" in {
        val liftedMethod = liteLift {
            def getMessages(f: Int): ListBuffer[Int] = {
                val l = ListBuffer[Int]()
                l.append(f)
                l
            }
        }

        assert(liftedMethod.contains("scala.collection.mutable.ListBuffer"))
        assert(liftedMethod.contains("getMessages(f: scala.Int)"))
    }
    
    "lift method with mulitple arguments" should "capture all arguments in the wrapper" in {
        val liftedMethod = liteLift {
            def getMessages(f: Int, g: String): ListBuffer[Int] = {
                val l = ListBuffer[Int]()
                l.append(f)
                l
            }
        }

        assert(liftedMethod.contains("getMessages(args(0).asInstanceOf[Int],args(1).asInstanceOf[String])"))
    }

    "lift method without argument parenthesis" should "contain only the method name" in {
        val liftedMtd = liteLift{
            def getMessages: ListBuffer[Int] = {
                val l = ListBuffer[Int]()
                l.append(100)
                l
            }}
        val expectedWrapper: String = """        
        def wrapper(args: List[Any]): scala.collection.mutable.ListBuffer[Int] = {
          getMessages
        }"""
        liftedMtd.contains(expectedWrapper)
    }


    "lift method" should "not return the full path for objects defined in the same package" in {
        val liftedMethod2 = liteLift {
            def d(): Unit = {
                val foo = new Foo()
                foo.run()
            }
        }

        assert(liftedMethod2.contains("Foo"))
    }

    "A method including non-exist type" should "not type check" in {
        """liteLift {
            def bar(): Unit = {
                val foo = new NonExistentFoo()
            }
        }""" shouldNot typeCheck
    }

    "A method with wrong argument application" should "not type check" in {
        """liteLift {
            def bar(): Unit = {
                val foo = new Foo(10, 100)
            }
        }""" shouldNot typeCheck
    }

    "A block definition which is not a method" should "throw exception by liteLift" in {
        """liteLift {
            val foo = new Foo()
            foo.run()
        }""" shouldNot compile
    }
}
