package example
package piccolo

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import scala.collection.mutable.{Map => MutMap}

import squid.lib.transparencyPropagating

// Represent shared mutable states as partitioned tables
// All table entries in a given partition are stored on the same machine
@lift
class Table(val partition: MutMap[Int, Double]) extends Actor {
    // All partition operations are atomic
    def contains(key: Int): Boolean = {
        partition.contains(key)
    }

    def get(key: Int): Option[Double] = {
        partition.get(key)
    }

    def getIterator(): Map[Int, Double] = {
        partition.toMap
    }

    def put(key: Int, value: Double): Boolean = {
        partition.update(key, value)
        true
    }

    // Resolve write-write conflict with user-defined accumulation
    // In pageRank, update increments the value by key
    def update(key: Int, value: Double): Boolean = {
        var modified: Boolean = false
        if (partition.get(key).isDefined){
            partition(key) =  value + partition(key)
        } else {
            partition.update(key, value)
        }
        true
    }

    def clear(): Boolean = {
        partition.clear()
        true
    }

    def main(): Unit = {
        while (true) {
            println("Distributed page rank table " + id + " partition " + partition)
            handleRPC()
            waitRounds(1)
        }
    }
}