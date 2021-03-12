package meta.test 
package SSO 

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.Actor
import meta.runtime.simulation.{Default, SimulationConfig}
import org.scalatest.FlatSpec
import scala.util.Random 

import scala.collection.mutable.ListBuffer 

@lift 
class RandomServer extends Actor {
    val foo: Double = 30 

    def mtd(): Int = {
        Random.nextInt(20)
    }

    def mtd2(): Double = {
        foo 
    }

    def main(): Unit = {
        while(true) {
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}

@lift 
class Server(val randServer: RandomServer) extends Actor {
    val var1: Int = 10 
    val l: ListBuffer[Int] = new ListBuffer[Int]() 

    var var2: Double = 20 

    def statefulMtd: Int = {
        println("Stateful mtd called!")
        println("Mutable variable " + var2) 
        println("Mutable list buffer with immutable decl " + l)
        println("Immutable variable " + var1) 
        var1 
    }

    def statefulMtd2(): Int = {
        statefulMtd 
    }

    def redirectMtd1(): Int = {
        println("RedirectMtd 1 called!")
        randServer.mtd()
    }

    def redirectMtd2(): Double = {
        randServer.mtd2()
    }

    def statelessMtd(foo: Int): Int = {
        println("Stateless mtd invoked! Args: " + foo)
        42 + foo 
    }

    def main(): Unit = {
        while(true) {
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}

@lift 
class Client(val s: Server) extends Actor {
    def main(): Unit = {
        while(true) {
            // println("Call redirect mtd")
            // s.redirectMtd1() 
            // println("Call stateless mtd")
            val res: Int = s.statelessMtd(3) 
            println("Answer of the stateless method is " + res)
            waitLabel(Turn, 1)
        }
    }
}

class ssoLifter extends FlatSpec {

  import meta.classLifting.Lifter 

  val c1: ClassWithObject[Server] = Server.reflect(IR)
  val c2: ClassWithObject[RandomServer] = RandomServer.reflect(IR)
  val c3: ClassWithObject[Client] = Client.reflect(IR)    

  "Init" should "populate the sso method during lifting" in {
    val res = Lifter(List(c1, c2, c3), true)
    
    assert(Lifter.ssoMtds.diff(List("Server.statelessMtd", "Server.redirectMtd1", "RandomServer.mtd")).isEmpty)
  }
}

class ssoCompile extends FlatSpec {
  import meta.compile._
  import meta.deep.IR.Predef._ 

  "The sso example" should "compile" in {
    val init = code"""
      val rServer: RandomServer = new RandomServer
      val server: Server = new Server(rServer)
      val client: Client = new Client(server)
    """
  
    val c1: ClassWithObject[Server] = Server.reflect(IR)
    val c2: ClassWithObject[Client] = Client.reflect(IR)
    val c3: ClassWithObject[RandomServer] = RandomServer.reflect(IR)  

    compileSims(List(c1, c2, c3), ssoEnabled = true, 
      mainInit = Some(init),  
      initPkgName = this.getClass().getPackage().getName(), 
      destFolder = "src/test/scala/generated/sso")  
  }
}

class ssoRun extends FlatSpec {
  "The generated code" should "run" in {
    
    generated.meta.test.SSO.InitData.initActors()
    new Default(SimulationConfig(actors = List(), totalTurn = 10)).run()
  }
}