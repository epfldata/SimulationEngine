package example
package transportation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Vehicle() extends Actor {
    var income: Double = 0
    var capacity: Int = 100

    var nextStop: Location = null
    var route: List[Location] = List()

    var fare: Double = 3
    var availableSeats: Int = 100
    var avgTimePerLocation: Int = 3
    var resume: Boolean = false

    var passengers: List[Passenger] = List()

    // The vehicle can continue after all passengers have got on, up to capacity
    @transparencyPropagating
    def continue(): Unit = {
        resume = true
    }

    // @transparencyPropagating
    def passengerEnter(p: Passenger): Boolean = {
        // println("Passenger " + p.id + " enters bus " + id)
        if (availableSeats > 0) {
            income = fare + income
            passengers = p :: passengers
            availableSeats = availableSeats - 1
            true
        } else {
            false
        }
    }

    // @transparencyPropagating
    def passengerLeave(p: Passenger): Unit = {
        passengers = passengers.filterNot(_==p)
        availableSeats = availableSeats + 1
    }
}

@lift 
class Bus() extends Vehicle {
    private var nextLoc: Int = 0
    private var futures: List[Future[Unit]] = List()
    private var forward: Boolean = true

    def main(): Unit = {
        fare = 3
        availableSeats = 30
        avgTimePerLocation = 10
        // Register the bus with all relevant locations
        route.foreach(r => {
            asyncCall(r.register(this, route), 1)
        })

        // Initially the bus is at source
        asyncCall(route(0).arrive(this), 1)
        waitAndReply(1)

        while (true) {
            // println("Vehicle "+ id + " waits for passengers!")
            // Wait for passengers at the source location
            while (!resume) {
                waitAndReply(1)
            }
            resume = false
            // println(id + " all passengers boarded. Availabel seats: " + availableSeats)

            // Once reaching the destination, go back
            if (forward){
                nextLoc = nextLoc + 1
            } else {
                nextLoc = nextLoc - 1
            }

            nextStop = route(nextLoc)
            
            if (nextLoc >= route.length-1){
                forward = false
            }
            if (nextLoc <= 0){
                forward = true
            }

            // Passengers can not get on or off bus while driving
            // waitRounds(avgTimePerLocation)
            // Inform the location that the vehicle has arrived 
            val f = asyncCall(nextStop.arrive(this), avgTimePerLocation)
            // Inform passengers about the nextStop stop
            futures = passengers.map(p => {
                asyncCall(p.nowArrive(nextStop), avgTimePerLocation)
            })
            // Wait until all passengers who have arrived leave
            while(!(f::futures).forall(i => i.isCompleted)){
                waitAndReply(1)
            }
        }
    }
}

