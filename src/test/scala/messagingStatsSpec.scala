package meta.test 

import org.scalatest.FlatSpec

import meta.runtime.simulation.MessagingStats 

class messagingStatsSpec extends FlatSpec {
    "Merging" should "group frequently communicating agents" in {
        MessagingStats.setMergeThreshold(3)

        MessagingStats.recordMessage(0, 1)
        MessagingStats.recordMessage(1, 0)
        MessagingStats.recordMessage(1, 0)
        (1 to 3).foreach(x => {
            MessagingStats.recordMessage(2, 3)
        })
        assert(MessagingStats.getMergeCandidates == List(Set(1, 0), Set(2, 3)))

        MessagingStats.recordMessage(1, 2)
        assert(MessagingStats.getMergeCandidates == List(Set(0, 1), Set(2, 3)))

        (1 to 3).foreach(x => {
            MessagingStats.recordMessage(2, 1)
        })

        assert(MessagingStats.getMergeCandidates == List(Set(0, 1, 2, 3)))
    }
}