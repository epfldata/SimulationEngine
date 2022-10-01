package example
package transportation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating
import scala.collection.mutable.{Map => MutMap}

@lift 
class Location() extends Actor {

    var vehiclesArrived: List[Vehicle] = List()
    var waitingPassengers: List[Passenger] = List()
    var dashboard: MutMap[Vehicle, List[Location]] = MutMap()
    private var futures: List[Future[Location]] = List()
    private var futures2: List[Future[Boolean]] = List()
    private var futures3: List[Future[Unit]] = List()
    private var destinations: MutMap[Passenger, Location] = MutMap()

    // Vehicles arrive
    @transparencyPropagating
    def arrive(v: Vehicle): Unit = {
        // println("vehicle " + v.id + " is at " + name)
        vehiclesArrived = v :: vehiclesArrived
    }

    // Passengers join the waiting list
    @transparencyPropagating
    def waitForVehicle(p: Passenger, dest: Location): Unit = {
        // println("Passenger " + p.id + " waits at location " + name)
        if (!waitingPassengers.contains(p)){
            // println("Passenger " + p.id + " added to waiting queue at " + id)
            waitingPassengers = p :: waitingPassengers
            destinations(p) = dest
        }
    }

    // All vehicles register with the locations about their routes
    @transparencyPropagating
    def register(v: Vehicle, route: List[Location]): Unit = {
        // println("Register vehicle " + v.id + " at loc " + id)
        dashboard(v) = route
    }

    def main(): Unit = {
        markPrivate("futures", "futures2", "futures3", "destinations")
        while (true) {
            // Wait some time for passengers before departure
            waitAndReply(1)

            // For each passenger, inform her about possible vehicles
            futures2 = waitingPassengers.map(p => {
                val possibleVehicles = vehiclesArrived.filter(v => dashboard(v).contains(destinations(p)))
                asyncCall(p.boardVehicle(possibleVehicles), 1)
            })
            // Wait until all passengers have either boarded or learned not to board
            while (!futures2.forall(x => x.isCompleted)){
                waitAndReply(1)
            }
            // // Remove passengers who have boarded from the waiting list
            waitingPassengers = waitingPassengers.zip(futures2).filterNot(i => i._2.popValue.get).map(j => j._1)
            // println(id + " has " + waitingPassengers.length + " waiting passengers")
            // println(id + " has " + vehiclesArrived.length + " waiting vehicles")
            // After all passengers have boarded, inform vehicles to continue
            futures3 = vehiclesArrived.map(x => {
                asyncCall(x.continue(), 1)
            })
            // Wait until all waiting vehicles have left
            while (!futures3.forall(x => x.isCompleted)){
                waitAndReply(1)
            }
            vehiclesArrived = List()
        }
    }
}