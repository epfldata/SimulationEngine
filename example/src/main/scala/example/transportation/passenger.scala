package example
package transportation

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import squid.lib.transparencyPropagating

@lift 
class Passenger(var budget: Int, var plan: List[Location]) extends Actor {
    
    // the current stop
    var current: Location = null
    // var waitingInLine: Boolean = false
    var currentDest: Location = null
    var currentVehicle: Vehicle = null
    // how far we have completed the plan, i.e index into plan
    var nextLoc: Int = 1

    def destination(): Location = {
        currentDest = if (nextLoc >= plan.length-1){
            plan.last
        } else {
            plan(nextLoc)
        }
        currentDest
    }

    // Select from a list of vehicles that all reach the destination
    @transparencyPropagating
    def boardVehicle(v: List[Vehicle]): Boolean = {
        if (!v.isEmpty){
            // TODO: add decision logic. 
            // Jump on the first available vehicle
            // With transparencyPropagating for passengerEnter, passengerEnter is called 3 times
            val onBoard = v.head.passengerEnter(this)
            if (onBoard){
                // println("Passenger " + id + " entered bus at location " + current.id)
                currentVehicle = v.head
                // waitingInLine = false
            }
            onBoard
        } else {
            false
        }
    }

    // TODO: add transit logic (passenger can change vehicles)
    // passengers decides whether to leave the vehicle only when the vehicle comes to a full stop
    @transparencyPropagating
    def nowArrive(location: Location): Unit = {
        // If arrived, move on to the next destination
        // println("Passenger " + id + " now arrives at " + location.id)
        if (location == currentDest) {
            currentVehicle.passengerLeave(this)
            currentVehicle = null
            // println("Passenger " + id + " leaves bus at location " +location.id)
            current = location
            nextLoc = nextLoc + 1
        }
    }

    def main(): Unit = {
        current = plan.head
        while (true) {
            if (nextLoc >= plan.length){
                // println("Passenger " + id + " has arrived at destination!")
            } else {
                if (currentVehicle==null) {
                    callAndForget(current.waitForVehicle(this, destination()), 1)
                }
            }
            waitAndReply(1)
        }
    }
}