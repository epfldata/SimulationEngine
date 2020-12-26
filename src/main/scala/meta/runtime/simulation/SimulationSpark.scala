package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.ListBuffer
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, broadcast}

object SimulationSpark extends Simulation {

  @transient private lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("ECONOMIC_SIMULATION")
  //        .set("spark.driver.allowMultipleContexts", "true")

  @transient private lazy val sc: SparkContext = new SparkContext(conf)

  @transient private var actors: RDD[Actor] = _
  private var currentTurn: Int = 0
  private var currentTime: Double = 0

  sc.setLogLevel("ERROR")
  sc.setCheckpointDir("checkpoint/")

  def apply(c: SimulationConfig): SimulationSnapshot = {
    actors = sc.parallelize(c.actors)
    currentTurn = c.startTurn
    currentTime = c.startTime
    waitLabels("time") = c.actors.length
    run(c)
  }

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
      if (currentTurn % 30 == 0) {
        actors.checkpoint()
      }
    })
    events.append(() => collect())
//    events.append(() => waitLabels("time") = actors.collect().length)
    events.append(() => {
      actors = actors.map(x => x.cleanSendMessage)
        .map(x => x.addInterrupts(currentTime))
        .map(x => x.run_until(currentTurn))
        .cache()})
    events.append(() => {
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
    })
    events.append(() => proceed())
    events.toList
  }

  def collect(): Unit = {
    newActors.map(i => i.currentTurn = currentTurn)
    actors = actors ++ sc.parallelize(newActors)
    newActors.clear()
  }

  def run(c: SimulationConfig): SimulationSnapshot = {
    val events: List[() => Unit] = init()
    val start = System.nanoTime()
    // Todo: update the time label when new actors added
    while (currentTurn <= c.totalTurn && currentTime <= c.totalTime) {
      events.foreach(_())
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
    SimulationSnapshot(actors.collect().toList, currentTurn, currentTime)
  }
}
