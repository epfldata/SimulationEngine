package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import meta.runtime.Actor.AgentId

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import meta.classLifting.SpecialInstructions.Time
import scala.util.Random 

class SparkBase(val config: SimulationConfig) extends Simulation {

  @transient protected lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("ECONOMIC_SIMULATION")
      .set("spark.driver.memory", "2g")
      .set("spark.executor.memory", "512m")
  //        .set("spark.driver.allowMultipleContexts", "true")

  @transient protected lazy val sc: SparkContext = new SparkContext(conf)

  @transient protected var actors: RDD[(AgentId, Actor)] = sc.parallelize(config.actors).map(x => (x.id, x))

  // var containerMap: Map[AgentId, List[AgentId]] = Map()

  sc.setLogLevel("ERROR")
  sc.setCheckpointDir("checkpoint/")

  var init = () => {
    initLabelVals()
  }

  var proceed = () => {
    proceedGroups()
    currentTurn = currentTurn + proceedLabel("turn").asInstanceOf[Int]
    actors.count()
  }

  override val events: ListBuffer[()=> Unit] = new ListBuffer()
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
    meta.Util.warning(s"Total agents ${actors.count}")
  })
  events.append(() => {
    val messageMap: RDD[(AgentId, List[Message])] = actors
      .flatMap(_._2.getSendMessages)
      .map(x => (x.receiverId, x))
      .combineByKey(
        (message: Message) => List(message),
        (l: List[Message], message: Message) => message :: l,
        (l1: List[Message], l2: List[Message]) => l1 ::: l2
      )

    val proxyMap: RDD[(AgentId, AgentId)] = actors.flatMap(x => {
      x._2.getProxyIds.map(p => (p, x._1))
    })

    val containerMessages: RDD[(AgentId, Set[Message])] = messageMap
      .leftOuterJoin(proxyMap)
      .map(x => (x._2._2.get, x._2._1))
      .combineByKey(
        (message: List[Message]) => message.toSet,
        (l1: Set[Message], l2: List[Message]) => l2.toSet.union(l1),
        (l1: Set[Message], l2: Set[Message]) => l1.union(l2)
      )

    actors = actors
      .leftOuterJoin(containerMessages)
      .mapValues { x =>
        x._1.addReceiveMessages(x._2.getOrElse(List()).toList)
          .cleanSendMessage
          .run()
      }.cache()

    actors.count()
  })
  events.append(() => {
    proceed()
  })

  var collect = () => {
    actors = (actors ++ sc.parallelize(newActors).map(x => (x.id, x)))
    newActors.clear()
  }

  var takeSnapshot = () => {
    val updatedActors: List[Actor] = actors.values.collect.toList
    SimulationSnapshot(updatedActors, currentTurn, currentTime)
  }
}

object SparkBase {
  def apply(s: Simulation): Simulation = {
    new SparkBase(s.config)
  }
}