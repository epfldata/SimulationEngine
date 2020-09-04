package generated.simulation

import meta.deep.runtime.Actor.{AgentId, initLabelVals, minTurn, proceedGroups, proceedLabel, waitLabels, waitTurnList}
import meta.deep.runtime.{Actor, Message, Monitor}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, broadcast}

object SimulationSpark extends App {

  def run(config: SimulationConfig): Unit = {

    @transient lazy val conf: SparkConf =
      new SparkConf().setMaster("local").setAppName("ECONOMIC_SIMULATION")
    @transient lazy val sc: SparkContext = new SparkContext(conf)

    var actors: RDD[Actor] = sc.parallelize(config.actors)
    var currentTurn: Int = config.startTurn
    var currentTime: Double = config.startTime
    val totalTurn: Int = config.totalTurn
    val totalTime: Double = config.totalTime
    val monitor_enabled: Boolean = config.monitor_enabled

    sc.setLogLevel("ERROR")
//    sc.setCheckpointDir("checkpoint/")

    def proceed(): Unit = {
      proceedGroups()
      currentTurn += minTurn()
      currentTime += proceedLabel("time")

      actors = actors.map(i => {
        i.currentTime = currentTime
        i.currentTurn = currentTurn
        i
      })

      waitTurnList.clear()
    }

    initLabelVals()
    val start = System.nanoTime()
    waitLabels("time") = actors.count().toInt

    println("Monitor is enabled: " + monitor_enabled)
    while (currentTurn <= totalTurn && currentTime <= totalTime) {
      println("(Time " + BigDecimal(currentTime).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        + " Turn " + currentTurn + ")" )

      // Checkpoint actors object, so that it does not get too big
      // see: https://stackoverflow.com/questions/36421373/java-lang-stackoverflowerror-and-checkpointing-on-spark
      // Result in non-serializable error

//      if (currentTurn % 50 == 0) {
//        actors.checkpoint()
//      }

      //Execute all actors
      actors = actors.map(x => SparkSims.cleanSendMessage(x))
          .map(x => SparkSims.checkInterrupts(x, currentTime))
          .map(x => SparkSims.run_until(x, currentTurn))
          .cache()    // cache to persist it, otherwise Sims get different ids across runs

      // Collect all messages from the round
      // leftOuterJoin results in closure bug: it captures agentId as a class variable, not an object variable
      // Use broadcast variable to share read-only collected messages. Can't have transformations within a transformation
      val messageMap: scala.collection.Map[AgentId, List[Message]] = actors
        .flatMap(SparkSims.getSendMessages)
        .map(x => (x.receiverId, x))
        .combineByKey(
          (message: Message) => {
            List(message)
          },
          (l: List[Message], message: Message) => {
            message :: l
          },
          (l1: List[Message], l2: List[Message]) => {
            l1 ::: l2
          }).collectAsMap()

      val dMessages = sc.broadcast(messageMap)

      actors = actors.map(actor => {
        SparkSims.addReceiveMessages(actor, dMessages)
      })

      if (monitor_enabled) Monitor.eachIteration(()=>())
      proceed()
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }
}
