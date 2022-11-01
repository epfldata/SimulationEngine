package example
package piccolo

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import scala.collection.mutable.{Map => MutMap}
import squid.lib.transparencyPropagating

// Represent shared mutable states as partitioned tables
// Partitioned graph stored as edge lists
@lift
class GraphTable(val partition: MutMap[Int, List[Int]]) extends Actor {
    // All partition operations are atomic
    def contains(key: Int): Boolean = {
        partition.contains(key)
    }

    def getIterator(): Map[Int, List[Int]] = {
        partition.toMap
    }

    def get(key: Int): Option[List[Int]] = {
        partition.get(key)
    }

    def put(key: Int, value: List[Int]): Boolean = {
        partition.update(key, value)
        true
    }

    // write-write policy, overwrite
    def update(key: Int, value: List[Int]): Boolean = {
        partition.update(key, value)
        true
    }

    def clear(): Boolean = {
        partition.clear()
        true
    }

    def main(): Unit = {
        while (true) {
            handleRPC()
            waitRounds(1)
        }
    }
}