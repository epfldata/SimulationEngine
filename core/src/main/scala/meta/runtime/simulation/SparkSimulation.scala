package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

class SparkRun(var actors: List[Actor], val totalTurn: Int, val messages: List[Message]) {

  @transient protected lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("TickTalk")
      .set("spark.driver.memory", "30g")
      .set("spark.executor.memory", "5g")
      .set("spark.executor.cores", "48")
      .set("spark.default.parallelism", "96")
      .set("spark.hadoop.dfs.replication", "1")
      // .set("spark.driver.allowMultipleContexts", "true")

  @transient protected lazy val sc: SparkContext = new SparkContext(conf)

  sc.setLogLevel("INFO")
  sc.setCheckpointDir("checkpoint/")
  
  @transient protected var actorRDD: RDD[Actor] = sc.parallelize(actors, 48)

  var currentTurn: Int = 0
  var collectedMessages: List[Message] = messages

  def collect(): Unit = {
    actorRDD = actorRDD ++ sc.parallelize(newActors, 2)
    newActors.clear()
  }

  def proceed(elapsedTurn: Int = 1) = {
    currentTurn += elapsedTurn
  }

  def run(): SimulationSnapshot = {

    while (currentTurn < totalTurn ) {
        println(util.displayTime(currentTurn))

        // if (currentTurn % 10 == 0) {
        //   actorRDD.checkpoint()
        // }

        val mx = collectedMessages.groupBy(_.receiverId)
        val res = actorRDD.map(a => {
          val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
          (a.run(targetMessages), a)
        })
        res.cache()
        val materializeRes = res.collect().toList
        
        actorRDD = res.map(_._2)
        collect()
        actorRDD.cache()
        collectedMessages = materializeRes.flatMap(_._1._1)
        proceed(materializeRes.map(_._1._2.asInstanceOf[Int]).max)
    }

    val updatedActors: List[Actor] = actorRDD.collect.toList
    val snapshot = SimulationSnapshot(updatedActors, collectedMessages)
    sc.stop()
    snapshot
  }
}