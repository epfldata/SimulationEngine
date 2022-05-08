package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test names with modifier separators
 */

@lift 
class FooA() extends Actor {
    
    def get_Name__() : String = {
        println(id + " get_Name__ is called!")
        "Foo!"
    }

    // async message doesn't work without the ending bracket
    // def __get___Name__ : String = {
    def __get___Name__() : String = {
        println(id + " __get___Name__ is called!")
        "Hello"
    }

    def ___get_Name___() : String = {
        println(id + " ___get_Name___ is called!")
        "World"
    }

    def main(): Unit = {
        // println(get_Name__())
        // println(__get___Name__)
        // println(___get_Name___)
        
        while (true){
            waitAndReply(1)
        }
    }
}

@lift
class BarA() extends FooA {
    def override_get_Name__() : String = {
        println(id + " get_Name__ is called!")
        "Bar!"
    }
    override def main(): Unit = {
        // println(get_Name__())
        // println(__get___Name__)
        // println(___get_Name___)
        
        while (true){
            waitAndReply(1)
        }
    }
}

@lift
class FooBar() extends FooA {
    val bar: BarA = new BarA()
    val foo: FooA = new FooA()

    override def main(): Unit = {
        while (true){
            println("FooBar messages Foo!")
            foo.get_Name__()
            foo.__get___Name__
            foo.___get_Name___
            println("FooBar messages Bar!")
            bar.get_Name__()
            bar.__get___Name__
            bar.___get_Name___
            println("FooBar messages Foo asynchronously!")
            asyncMessage(() => foo.get_Name__())
            asyncMessage(() => foo.__get___Name__())
            asyncMessage(() => foo.___get_Name___())
            println("FooBar messages Bar asynchronously!")
            asyncMessage(() => bar.get_Name__())
            asyncMessage(() => bar.__get___Name__())
            asyncMessage(() => bar.___get_Name___())
        }
    }
}


class methodNameTest extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Calling methods with underscores locally" should "compile and resolve correctly" in {    
        val foo: ClassWithObject[FooA] = FooA.reflect(IR)
        val bar: ClassWithObject[BarA] = BarA.reflect(IR)
        val foobar: ClassWithObject[FooBar] = FooBar.reflect(IR)

        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val a = new FooA()
                val b = new BarA()
                val c = new FooBar()
                List(a, b, c)
            }
        }

        compileSims(List(foo, bar, foobar), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.methodNames"),
            destFolder = "gen-core/src/main/scala/methodNames/")
    }
}