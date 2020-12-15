package example.lock_example

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()
    val totalVoters: Int = 5

    val consensus_object: Consensus = new Consensus()
    l.append(consensus_object)

    (1 to totalVoters).foreach(i => {
      l.append(new Voter(consensus_object))
    })
    l.toList
  }
}
