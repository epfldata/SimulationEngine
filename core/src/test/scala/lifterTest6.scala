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
class CommunicatingVehicle(val n: ShortDistanceTransport) extends Vehicle {

    override def main(): Unit = {
        while (true) {
            if (n != null) {
                val x = n.getPrice()
                println("The price for a short distance transport vehicle is " + x + " should change")
            }
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

        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val s = new ShortDistanceTransport()
                val c = new CommunicatingVehicle(s)
                List(s, c)
            }
        }

        compileSims(List(
            vehicleClass, 
            shortDistanceClass, 
            cVehicleClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()+".inheritance4"),
            destFolder = "core/src/test/scala/generated/inheritance4/")
    }

    // "Calling a remote overriden method" should "invoke the child method" in {
    //     val agents = generated.meta.test.inheritance4.InitData()
    //     val c = new SimulationConfig(agents, 10)
    //     val r = StartSimulation[BaseMessagingLayer.type](c)
    // }
}