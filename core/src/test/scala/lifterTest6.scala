package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test messaging for inserted methods
 */

@lift
class CommunicatingVehicleType2() extends Vehicle {
    def override_getPrice(): Int = {
        println("Received a message for get price in Commmunicating vehicle type 2!")
        30
    }

    override def main(): Unit = {
        while (true) {
            waitAndReply(1)
        }
    }
}

@lift
class CommunicatingVehicle(val neighbors: Vector[Vehicle]) extends Vehicle {
    override def main(): Unit = {
        while (true) {
            neighbors.foreach(n => asyncMessage(() => n.getPrice()))
            waitAndReply(1)
        }
    }
}


class lifterTest6 extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Sending messages to an inherited method" should "obtain the right value" in {
        
        val vehicleClass: ClassWithObject[Vehicle] = Vehicle.reflect(IR)
        val shortDistanceClass: ClassWithObject[ShortDistanceTransport] = ShortDistanceTransport.reflect(IR)
        val cVehicleClass: ClassWithObject[CommunicatingVehicle] = CommunicatingVehicle.reflect(IR)
        val cVehicleClass2: ClassWithObject[CommunicatingVehicleType2] = CommunicatingVehicleType2.reflect(IR)

        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val s = new ShortDistanceTransport()
                val c2 = new CommunicatingVehicleType2()
                val c = new CommunicatingVehicle(Vector(s, c2))
                List(s, c, c2)
            }
        }

        compileSims(List(
            vehicleClass, 
            shortDistanceClass, 
            cVehicleClass,
            cVehicleClass2), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.inheritance4"),
            destFolder = "gen-core/src/main/scala/inheritance4/")
    }
}