package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.ListBuffer
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import meta.classLifting.SpecialInstructions.Time
import scala.util.Random 

class SimulationSpark(val config: SimulationConfig) extends Simulation {

  @transient private lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("ECONOMIC_SIMULATION")
      .set("spark.driver.memory", "2g")
      .set("spark.executor.memory", "512m")
  //        .set("spark.driver.allowMultipleContexts", "true")

  @transient private lazy val sc: SparkContext = new SparkContext(conf)

  @transient private var actors: RDD[(Actor.AgentId, Actor)] = sc.parallelize(config.actors).map(x => (x.id, x))
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

    actors.count()
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
      val messageMap: RDD[(Actor.AgentId, List[Message])] = actors
        .flatMap(_._2.getSendMessages)
        .map(x => (x.receiverId, x))
        .combineByKey(
          (message: Message) => List(message),
          (l: List[Message], message: Message) => message :: l,
          (l1: List[Message], l2: List[Message]) => l1 ::: l2
        )

      actors = actors
        .leftOuterJoin(messageMap)
        .mapValues { x =>
          x._1.addReceiveMessages(Random.shuffle(x._1.getProxyIds.flatMap(id => x._2.getOrElse(List()))))
            .cleanSendMessage
            .run()
        }.cache()
      actors.count()
    })
    events.append(() => proceed())
    events.toList
  }

  def collect(): Unit = {
    actors = (actors ++ sc.parallelize(newActors).map(x => (x.id, x)))
    newActors.clear()
  }

  def run(): SimulationSnapshot = {

    val events: List[() => Unit] = init()

    util.bench {
      while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
        events.foreach(_())
      }
    }

    val updatedActors: List[Actor] = actors.values.collect.toList

    SimulationSnapshot(updatedActors, currentTurn, currentTime)
  }
}
