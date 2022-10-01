package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future}
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

    private val donot_copy: Int = 12
    private val another_private_var: List[Double] = List(1.2, 1.4)

    def getLoad(): Int = {
        load
    }

    // Does not support using a private var in local mtd
    def getPrice(): Int = {
        // price + private_donot_copy
        price
    }

    // Should not get copied to children
    private def local_mtd(): String = {
        "Invisible!"
    }

    def main(): Unit = {
        markPrivate("donot_copy", "another_private_var", "local_mtd")
        while (true) {
            waitAndReply(1)
        }
    }
}

@lift
class ShortDistanceTransport() extends Vehicle {
    val licensePlace: Int = 800

    private val donot_copy: Double = 521
    // price = 15
    override def getPrice(): Int = {
        // Make sure this method is called
        price + 2
    }

    override def main(): Unit = {
        // Invalid fields and main are ignored
        markPrivate("donot_copy", "non_existing_attribute")
        markOverride("getPrice", "main", "non_existing_attribute")

        price = 15
        while (true) {
            price = getPrice()
            load = getLoad()
            waitAndReply(1)
        }
    }
}

@lift
class Bus() extends ShortDistanceTransport {

    override def getLoad(): Int = {
        30
    }

    override def getPrice(): Int = {
        price
    }

    override def main(): Unit = {
        markOverride("getLoad")
        markOverride("getPrice")
        while (true) {
            price = getPrice()
            load = getLoad()
            waitAndReply(1)
        }
    }
}

@lift
class Van() extends Vehicle {

    override def main(): Unit = {
        while (true) {
            price = getPrice()
            load = getLoad()
            waitAndReply(1)
        }
    }
}

@lift
class CommunicatingVehicle(val neighbors: List[Vehicle]) extends Vehicle {
    var neighborPrices: List[Int] = List()
    
    private var f: List[Future[Int]] = null
    override def main(): Unit = {
        while (true) {
            f = neighbors.map(n => asyncCall(() => n.getPrice(), 1))
            while (f.exists(i => !i.isCompleted)){
                waitAndReply(1)
            }
            neighborPrices = f.map(i => i.popValue.get)
        }
    }
}

class InheritanceTestRootLifted extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Inherit from a lifted agent" should "invoke the right function" in {
        
        val vehicleClass: ClassWithObject[Vehicle] = Vehicle.reflect(IR)
        val shortDistanceClass: ClassWithObject[ShortDistanceTransport] = ShortDistanceTransport.reflect(IR)
        val busClass: ClassWithObject[Bus] = Bus.reflect(IR)
        val vanClass: ClassWithObject[Van] = Van.reflect(IR)
        val cVehicleClass: ClassWithObject[CommunicatingVehicle] = CommunicatingVehicle.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val v = new Vehicle()
                val s = new ShortDistanceTransport()
                val bus = new Bus()
                val van = new Van()
                val c = new CommunicatingVehicle(List(v, s, bus, van))
                List(v, s, bus, van, c)
            }
        }

        compileSims(List(
        vehicleClass, 
        shortDistanceClass, 
        busClass, vanClass, cVehicleClass
        ), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.inheritance2"),
            destFolder = "gen-core/src/main/scala/inheritance2/")
    }
}