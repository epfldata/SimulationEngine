package example.muddy_children

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Parent(val env: List[MuddyChild]) extends Actor{

    def remark(): Boolean = {
        env.exists(e => (e.isMuddy && !e.isForward))
    }

    def command(): Unit = {
        println("There is at least one child with muddy forehead!")
        env.foreach(e => asyncMessage(() => e.reflect()))
    }

    def main(): Unit = {
        while (true) {
            if (remark()){
                command()
            } else {
                println("All muddy children are forwarded!")
            }
            waitTurns(2)
        }
    }
}
