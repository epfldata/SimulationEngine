package example.muddy_children

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

trait Child extends Actor{
    val isMuddy: Boolean
    var isForward: Boolean = false

    override def toString(): String = {
        "Child " + id + " is muddy: " + isMuddy + " is forward: " + isForward
    }
}

@lift
class MuddyChild(val isMuddy: Boolean) extends Child {

    var env: List[MuddyChild] = Nil
    var counter = 0
    var shouldMove: Boolean = false

    // children inspect whether they have mud when called on by parents 
    def reflect(): Unit = {
        counter = counter + 1

        val totalMuddy: Int = env.foldLeft(0)((x, y) => if (y.isMuddy && !y.isForward) x+1 else x)

        if (totalMuddy < counter && !isForward) {
            shouldMove = true
        }
    }

    def stepForward(): Unit = {
        if (shouldMove){
            isForward = true
            shouldMove = false
            println("Step forward! " + toString())
        }
    }

    def main(): Unit = {
        while (true) {
            handleMessages()
            waitTurns(1)
            stepForward()
//            println(toString())
        }
    }
}
