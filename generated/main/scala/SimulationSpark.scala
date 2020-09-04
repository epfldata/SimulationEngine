import meta.deep.runtime.Actor.{AgentId, initLabelVals, minTurn, proceedGroups, proceedLabel, waitLabels, waitTurnList}
import meta.deep.runtime.{Actor, Message}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, broadcast}

object SimulationSpark extends App {

  @transient lazy val conf: SparkConf =
    new SparkConf().setMaster("local").setAppName("ECONOMIC_SIMULATION")
  @transient lazy val sc: SparkContext = new SparkContext(conf)

  var actors: RDD[Actor] = _
  var currentTurn: Int = 0
  var totalTurns: Int = 100
  var currentTime: Double = 0
  var totalTime: Double = 10

  def init(): Unit = {
    actors = sc.parallelize(generated.InitData.initActors)
    initLabelVals()
  }

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

  def main(): Unit = {
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")

    init()
    val start = System.nanoTime()
    waitLabels("time") = actors.count().toInt

    while (currentTurn <= totalTurns && currentTime <= totalTime) {

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

      proceed()
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
