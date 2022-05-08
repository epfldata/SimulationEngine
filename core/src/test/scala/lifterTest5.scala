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
    val licensePlate: Int = 0

    private val private_donot_copy: Double = 512

    def getLoad(): Int = {
        load
    }

    def getPrice(): Int = {
        price
    }

    // Should not get copied to children
    private def private_local_mtd(): Unit = {
        println("This is an invisible local method!")
    }

    def main(): Unit = {
        while (true) {
            waitAndReply(1)
        }
    }
}

@lift
class ShortDistanceTransport() extends Vehicle {
    val licensePlace: Int = 800

    private val private_donot_copy: Double = 521
    // price = 15
    def override_getPrice(): Int = {
        // Make sure this method is called
        price = price + 2
        price
    }

    override def main(): Unit = {
        price = 15
        while (true) {
            val x = getPrice()
            println("Short distance transport price is " + x)
            val y = getLoad()
            println("Short distance transport load is " + y + " should be 10")
            waitAndReply(1)
        }
    }
}

@lift
class Bus() extends ShortDistanceTransport {

    def override_getLoad(): Int = {
        30
    }

    def get_Name__ : String = {
        "Hello"
    }

    def __get___Name__ : String = {
        "World"
    }

    override def override_getPrice(): Int = {
        price
    }

    override def main(): Unit = {
        while (true) {
            val x = getPrice()
            println("Bus price is " + x + " should be 20")
            val y = getLoad()
            println("Bus load is " + y + " should be 30")
            val z = get_Name__
            println("Bus name is " + z + " should be Hello")
            val f = __get___Name__
            println("Bus other name is " + f + " should be World")
            waitAndReply(1)
        }
    }
}

@lift
class Van() extends Vehicle {

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

        compileSims(List(
        vehicleClass, 
        shortDistanceClass, 
        busClass, vanClass
        ), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.inheritance3"),
            destFolder = "gen-core/src/main/scala/inheritance3/")
    }
}