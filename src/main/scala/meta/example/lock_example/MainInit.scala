package meta.example.lock_example

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val consensus_object: Consensus = new Consensus()

    (1 to 5).foreach(i => {
      val voter: Voter = new Voter()
      voter.consensus_object = consensus_object
      l.append(voter)
    })

    l.append(consensus_object)
    l.toList

  }
}
