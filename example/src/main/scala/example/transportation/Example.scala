package example
package transportation

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(totalVehicles: Int, totalPassengers: Int, totalLocations: Int): List[Actor] = {

            val locs = (1 to totalLocations).map(i => 
                new Location()    
            ).toList
            
            // Assume passengers make at most three stops
            val passengers = (1 to totalPassengers).map(i => {
                new Passenger(100, List(locs(Random.nextInt(totalLocations)), locs(Random.nextInt(totalLocations-5)+2), locs(Random.nextInt(totalLocations-10)+5)))
            }).toList

            // Consider full-city and regional buses
            val vehiclesFullCity = (1 to totalVehicles/4).map(i => {
                val vehicle = new Bus()
                vehicle.route = locs
                vehicle
            }).toList

            val vehiclesRegion1 = (1 to totalVehicles/4).map(i => {
                val vehicle = new Bus()
                vehicle.route = locs.slice(0, totalLocations/3)
                vehicle
            }).toList

            val vehiclesRegion2 = (1 to totalVehicles/4).map(i => {
                val vehicle = new Bus()
                vehicle.route = locs.slice(totalLocations/3 - 1, 2*totalLocations/3)
                vehicle
            }).toList

            val vehiclesRegion3 = (1 to totalVehicles/4).map(i => {
                val vehicle = new Bus()
                vehicle.route = locs.slice(2*totalLocations/3 - 1, totalLocations)
                vehicle
            }).toList

            locs ::: passengers ::: vehiclesFullCity ::: vehiclesRegion1 ::: vehiclesRegion2 ::: vehiclesRegion3
        }
    }
    
    val cls1: ClassWithObject[Vehicle] = Vehicle.reflect(IR)
    val cls4: ClassWithObject[Bus] = Bus.reflect(IR)
    val cls2: ClassWithObject[Passenger] = Passenger.reflect(IR)
    val cls3: ClassWithObject[Location] = Location.reflect(IR)

    compileSims(List(cls1, cls2, cls3, cls4), Some(liftedMain))
}