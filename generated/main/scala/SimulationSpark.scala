import Simulation.{actors, currentTime, currentTurn}
import meta.deep.runtime.Actor.{AgentId, initLabelVals, minTurn, proceedGroups, proceedLabel, waitLabels, waitTurnList}
import meta.deep.runtime.{Actor, Message}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SimulationSpark extends App {

  @transient lazy val conf: SparkConf =
    new SparkConf().setMaster("local").setAppName("ECONOMIC_SIMULATION")
  @transient lazy val sc: SparkContext = new SparkContext(conf)

  var actors: RDD[(AgentId, Actor)] = _
  var currentTurn: Int = 0
  var totalTurn: Int = 100
  var currentTime: Double = 0
  var totalTime: Double = 10
//  var timer = 0
//  var until = 100

  def init(): Unit = {
    actors = sc.parallelize(generated.InitData.initActors).map(x => (x.id, x))
    initLabelVals()
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn += minTurn()
    currentTime += proceedLabel("time")

    // update the turn counter for Sims
    actors.mapValues(i => {
      i.currentTime = currentTime
      i.currentTurn = currentTurn
    })

    waitTurnList.clear()
  }

  def main(): Unit = {
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")

    init()
    val start = System.nanoTime()

    while (currentTurn <= totalTurn) {

      // Checkpoint actors object, so that it does not get too big
      // see: https://stackoverflow.com/questions/36421373/java-lang-stackoverflowerror-and-checkpointing-on-spark
      println("TIMER", currentTurn)
      if (currentTurn % 50 == 0) {
        actors.checkpoint()
      }

      waitLabels("time") = actors.count().toInt

      //Execute all actors
      actors = actors.mapValues(a => {
        a.cleanSendMessage.run_until(currentTurn)
      })

      //Collect all messages from the round
      val dMessages: RDD[(AgentId, List[Message])] = actors
        .flatMap(_._2.getSendMessages)
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
          }
        )
        .cache()

      //Distribute messages for the next round
      actors = actors
        .leftOuterJoin(dMessages)
        .mapValues { x =>
          x._1.checkInterrupts(currentTime).addReceiveMessages(x._2.getOrElse(List()))
//                    x._1.addReceiveMessages(x._2.getOrElse(List()))
          x._1
        }
        .cache()

      proceedGroups()
//      timer += 1
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
