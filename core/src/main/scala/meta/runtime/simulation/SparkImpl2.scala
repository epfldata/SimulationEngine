package meta.runtime
package simulation

import SimRuntime._
import scala.collection.mutable.{ListBuffer, Map => MutMap}
import meta.runtime.Actor.AgentId
import meta.API.{SimulationSnapshot, SimulationConfig}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.RangePartitioner
import org.apache.spark.HashPartitioner

class SparkRun2(var actors: List[Actor], val totalTurn: Int, val messages: List[Message]) {

  @transient protected lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("TickTalk")
      .set("spark.driver.memory", "70g")
      .set("spark.executor.memory", "70g")
      .set("spark.executor.cores", "48")
      // .set("spark.default.parallelism", "96")
      .set("spark.hadoop.dfs.replication", "1")
      // .set("spark.driver.allowMultipleContexts", "true")

  @transient protected lazy val sc: SparkContext = new SparkContext(conf)

  sc.setLogLevel("INFO")
  sc.setCheckpointDir("checkpoint/")
  
  val pair_actors: RDD[(Long, Actor)] = sc.parallelize(actors).map(a => (a.id, a))
  val partitioner = new HashPartitioner(20)
  @transient protected var actorRDD: RDD[(Long, Actor)] = pair_actors.partitionBy(partitioner).persist()
  // val partitioner = new RangePartitioner(70, pair_actors)
  // @transient protected var actorRDD: RDD[(Long, Actor)] = pair_actors.partitionBy(partitioner).persist()

  // print(actorRDD.glom().collect().map(i => i.map(j => j._1).toList).toList)

  var currentTurn: Int = 0
  var collectedMessages: List[Message] = messages

  var t1: Long = 0
  var t2: Long = 0
  var t3: Long = 0
  var t4: Long = 0

  var totalTime: Long = 0
  var time_seq: List[Long] = List()

  def proceed(elapsedTurn: Int = 1) = {
    currentTurn += elapsedTurn
  }

  def run(): SimulationSnapshot = {

    while (currentTurn < totalTurn ) {
        println(util.displayTime(currentTurn))

        // if (currentTurn % 70 == 0) {
        //   actorRDD.checkpoint()
        // }

        t1 = System.currentTimeMillis()
        val mx = collectedMessages.groupBy(_.receiverId)
        val res = actorRDD.mapValues(a => {
          val targetMessages: List[Message] = a.proxyIds.flatMap(id => mx.getOrElse(id, List()))
          (a.run(targetMessages), a)
        })
        res.cache()
        t2 = System.currentTimeMillis()
        val materializeRes = res.collect().toList
        t3 = System.currentTimeMillis()
      
        actorRDD = res.map(i => (i._1, i._2._2))
        // collect()
        // actorRDD.cache()
        collectedMessages = materializeRes.flatMap(_._2._1._1)
        proceed(materializeRes.map(_._2._1._2.asInstanceOf[Int]).max)
        t4 = System.currentTimeMillis()
        time_seq = time_seq ::: List(t4-t1)
        println(f"transform ${t2-t1}, materialize ${t3-t2}, shuffle ${t4-t3}, total ${t4-t1}")
    }

    val updatedActors: List[Actor] = actorRDD.collect.toList.map(_._2)
    val snapshot = SimulationSnapshot(updatedActors, collectedMessages)
    val average = time_seq.sum / time_seq.length
    val stdev = Math.sqrt(time_seq.map(i => (i-average)*(i-average)).sum/time_seq.length)
    println(f"${time_seq}\nAverage time per round ${average} StdDev ${stdev}")
    sc.stop()
    snapshot
  }
}