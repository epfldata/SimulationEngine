package meta.test 

import org.scalatest.FlatSpec

import meta.runtime.ChattyAgents

class messagingStatsSpec extends FlatSpec {
    "Merging" should "group frequently communicating agents" in {
        val recordHotMessageTest = new ChattyAgents(3)

        recordHotMessageTest.recordMessage(0, 1)
        recordHotMessageTest.recordMessage(1, 0)
        recordHotMessageTest.recordMessage(1, 0)
        (1 to 3).foreach(x => {
            recordHotMessageTest.recordMessage(2, 3)
        })
        assert(recordHotMessageTest.getMergeCandidates == List(Set(1, 0), Set(2, 3)))

        recordHotMessageTest.recordMessage(1, 2)
        assert(recordHotMessageTest.getMergeCandidates == List(Set(0, 1), Set(2, 3)))

        (1 to 3).foreach(x => {
            recordHotMessageTest.recordMessage(2, 1)
        })

        assert(recordHotMessageTest.getMergeCandidates == List(Set(0, 1, 2, 3)))
    }
}