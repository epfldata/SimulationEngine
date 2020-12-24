package meta.runtime
package simulation

import SimRuntime._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, broadcast}

object SimulationSpark extends Simulation {

  @transient private lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("ECONOMIC_SIMULATION")
  //        .set("spark.driver.allowMultipleContexts", "true")

  @transient private lazy val sc: SparkContext = new SparkContext(conf)

  sc.setLogLevel("ERROR")
//      sc.setCheckpointDir("checkpoint/")


  def apply(c: SimulationConfig): SimulationSnapshot = {
    run(c)
  }

  def run(c: SimulationConfig): SimulationSnapshot = {
    initLabelVals()

    var actors: RDD[Actor] = sc.parallelize(c.actors)
    var currentTurn: Int = c.startTurn
    var currentTime: Double = c.startTime

    def collect(): Unit = {
      newActors.map(i => i.currentTurn = currentTurn)
      actors = actors ++ sc.parallelize(newActors)
      newActors.clear()
    }

    def proceed(): Unit = {
      proceedGroups()
      currentTurn = currentTurn + proceedLabel("turn").asInstanceOf[Int]
      currentTime = currentTime + proceedLabel("time")

      actors.map(i => {
        i.currentTime = currentTime
        i.currentTurn = currentTurn
        i
      })
    }

    val start = System.nanoTime()
    waitLabels("time") = c.actors.length

    while (currentTurn <= c.totalTurn && currentTime <= c.totalTime) {
      println(util.displayTime(currentTurn, currentTime))

//       Checkpoint actors object, so that it does not get too big
//       see: https://stackoverflow.com/questions/36421373/java-lang-stackoverflowerror-and-checkpointing-on-spark
//       Result in non-serializable error

//      if (currentTurn % 10 == 0) {
//        actors.checkpoint()
//      }

//      collect()
//      waitLabels("time") = actors.collect.length
//      Execute all actors
      actors = actors.map(x => x.cleanSendMessage)
        .map(x => x.addInterrupts(currentTime))
        .map(x => x.run_until(currentTurn))
        .cache()

//       Collect all messages from the round
//       leftOuterJoin results in closure bug: it captures agentId as a class variable, not an object variable
//       Use broadcast variable to share read-only collected messages. Can't have transformations within a transformation

      val messageMap: scala.collection.Map[Actor.AgentId, List[Message]] = actors
        .flatMap(x => x.getSendMessages)
        .map(x => (x.receiverId, x))
        .combineByKey(
          (message: Message) => List(message),
          (l: List[Message], message: Message) => message :: l,
          (l1: List[Message], l2: List[Message]) => l1 ::: l2).collectAsMap()

      val dMessages = sc.broadcast(messageMap)

      actors = actors.map(actor => {
        actor.addReceiveMessages(dMessages.value.getOrElse(actor.id, List()))
      })

      proceed()
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
    SimulationSnapshot(actors.collect.toList, currentTurn, currentTime)
  }
}
