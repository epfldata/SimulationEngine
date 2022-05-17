package example
package transportation

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(vehicles: Int): List[Actor] = {

            val EPFL = new Location("EPFL")
            val LausanneGare = new Location("Lausanne Gare")
            val RenensGare = new Location("Renens Gare")
            val Nyon = new Location("Nyon")
            val Geneve = new Location("Geneve")

            val passenger1: Actor = new Passenger(100, List(EPFL, RenensGare, Geneve))
            val passenger2: Actor = new Passenger(100, List(Geneve, EPFL))
            val passenger3: Actor = new Passenger(100, List(RenensGare, EPFL))

            val vehicle1 = new Bus()
            vehicle1.route = List(EPFL, RenensGare, LausanneGare, Geneve)

            List(EPFL, LausanneGare, RenensGare, Nyon, Geneve, vehicle1, passenger1, passenger2, passenger3)
        }
    }
    
    val cls1: ClassWithObject[Vehicle] = Vehicle.reflect(IR)
    val cls4: ClassWithObject[Bus] = Bus.reflect(IR)
    val cls2: ClassWithObject[Passenger] = Passenger.reflect(IR)
    val cls3: ClassWithObject[Location] = Location.reflect(IR)

    compileSims(List(cls1, cls2, cls3, cls4), Some(liftedMain))
}