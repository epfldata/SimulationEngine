package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test override where all parents are lifted
 */

@lift
class Vehicle() extends Actor {
    var price: Int = 20
    var load: Int = 10

    def getLoad(): Int = {
        load
    }

    def getPrice(): Int = {
        price
    }

    def main(): Unit = {
        while (true) {
            waitAndReply(1)
        }
    }
}

@lift
class ShortDistanceTransport() extends Vehicle {

    price = 15
    def override_getPrice(): Int = {
        price
    }

    override def main(): Unit = {
        while (true) {
            val x = getPrice()
            println("Short distance transport price is " + x + " should be 15")
            val y = getLoad()
            println("Short distance transport load is " + y + " should be 10")
            waitAndReply(1)
        }
    }
}

@lift
class Bus() extends ShortDistanceTransport {
    var currentPassengers: Int = 30

    def override_getLoad(): Int = {
        currentPassengers
    }

    override def main(): Unit = {
        while (true) {
            val x = getPrice()
            println("Bus price is " + x + " should be 20")
            val y = getLoad()
            println("Bus load is " + y + " should be 30")
            waitAndReply(1)
        }
    }
}

@lift
class Van() extends ShortDistanceTransport {

    override def main(): Unit = {
        while (true) {
            val x = getPrice()
            println("Van price is " + x + " should be 20")
            val y = getLoad()
            println("Van load " + y + " should be 10")
            waitAndReply(1)
        }
    }
}

class lifterTest5 extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Inherit from a lifted agent" should "invoke the right function" in {
        
        val vehicleClass: ClassWithObject[Vehicle] = Vehicle.reflect(IR)
        val shortDistanceClass: ClassWithObject[ShortDistanceTransport] = ShortDistanceTransport.reflect(IR)
        val busClass: ClassWithObject[Bus] = Bus.reflect(IR)
        val vanClass: ClassWithObject[Van] = Van.reflect(IR)

        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val v = new Vehicle()
                val s = new ShortDistanceTransport()
                val bus = new Bus()
                val van = new Van()
                List(v, s, bus, van)
            }
        }

        compileSims(List(vehicleClass, shortDistanceClass, busClass, vanClass
        ), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()+".inheritance3"),
            destFolder = "core/src/test/scala/generated/inheritance3/")
    }

    // "Calling a remote overriden method" should "invoke the child method" in {
    //     val agents = generated.meta.test.inheritance3.InitData()
    //     val c = new SimulationConfig(agents, 5)
    //     val r = StartSimulation[BaseMessagingLayer.type](c)
    // }
}