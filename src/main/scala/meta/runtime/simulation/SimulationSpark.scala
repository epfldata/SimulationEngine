package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.ListBuffer
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, broadcast}
import meta.classLifting.SpecialInstructions.Time

class SimulationSpark(val config: SimulationConfig) extends Simulation {

  @transient private lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("ECONOMIC_SIMULATION")
      .set("spark.driver.memory", "2g")
      .set("spark.executor.memory", "512m")
  //        .set("spark.driver.allowMultipleContexts", "true")

  @transient private lazy val sc: SparkContext = new SparkContext(conf)

  @transient private var actors: RDD[Actor] = sc.parallelize(config.actors)
  private var currentTurn: Int = config.startTurn
  private var currentTime: Double = config.startTime

  sc.setLogLevel("ERROR")
  sc.setCheckpointDir("checkpoint/")

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn = currentTurn + proceedLabel("turn").asInstanceOf[Int]
    currentTime = currentTime + proceedLabel("time")

    actors = actors.map(i => {
      i.currentTime = currentTime
      i.currentTurn = currentTurn
      i
    })
  }

  def scheduleEvents(): List[()=> Unit] = {
    val events: ListBuffer[()=> Unit] = new ListBuffer()
    events.append(
      () => { println(util.displayTime(currentTurn, currentTime)) }
    )
    events.append(() => {
      if (currentTurn % 10 == 0) {
        actors.checkpoint()
      }
    })
    events.append(() => {
      collect()
      registerLabel(Time, actors.count())
    })
    events.append(() => {
      val messageMap: scala.collection.Map[Actor.AgentId, Set[Message]] = actors
        .flatMap(x => x.getSendMessages)
        .map(x => (x.receiverId, x))
        .combineByKey(
          (message: Message) => Set(message),
          (l: Set[Message], message: Message) => Set(message).union(l),
          (l1: Set[Message], l2: Set[Message]) => l1 ++ l2).collectAsMap()
      val dMessages = sc.broadcast(messageMap)

      actors = actors.map(x => x.addReceiveMessages(dMessages.value.getOrElse(x.id, List()).toList))
    })
    events.append(() => {
      actors = actors.map(x => x.cleanSendMessage)
        .map(x => x.addInterrupts(currentTime))
        .map(x => x.run_until(currentTurn))
        .cache()
      actors.count()
    })
    events.append(() => proceed())
    events.toList
  }

  def collect(): Unit = {
    newActors.map(i => i.currentTurn = currentTurn)
    actors = (actors ++ sc.parallelize(newActors)).cache()
    newActors.clear()
  }

  def run(): SimulationSnapshot = {

    val events: List[() => Unit] = init()
    val start = System.nanoTime()

    while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
      events.foreach(_())
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
    SimulationSnapshot(actors.collect().toList, currentTurn, currentTime)
  }
}
