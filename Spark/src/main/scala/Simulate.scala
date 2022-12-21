package simulation.spark.API

import scala.collection.mutable.{Buffer, Map => MutMap}

import meta.runtime.{Actor, Message}
import meta.API.{SimulationSnapshot, SimulationConfig}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.RangePartitioner
import org.apache.spark.HashPartitioner

import collection.JavaConverters._

object Simulate { 

  @transient protected lazy val conf: SparkConf =
    new SparkConf().setMaster("local")
      .setAppName("TickTalk")
      .set("spark.driver.memory", "50g")
      .set("spark.executor.memory", "5g")
      .set("spark.executor.cores", "48")
      .set("spark.default.parallelism", "96")
      .set("spark.hadoop.dfs.replication", "1")
      // .set("spark.serializer", "akka.serialization.jackson.JacksonJsonSerializer")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // .set("spark.driver.allowMultipleContexts", "true")

      conf.registerKryoClasses(Array(classOf[meta.runtime.Message]))

  @transient protected lazy val sc: SparkContext = new SparkContext(conf)

  sc.setLogLevel("ERROR")
  sc.setCheckpointDir("checkpoint/")

  var currentTurn: Int = 0
  var collectedMessages = scala.collection.Map[Long, List[Message]]()

  // var totalPartitions: Int = 20
  // .map(a => (a.id, a))
  // val partitioner = new HashPartitioner(70)
  // val partitioner = new RangePartitioner(totalPartitions, pair_actors)
  // print(actorRDD.glom().collect().map(i => i.map(j => j._1).toList).toList)
  // var actorRDD: RDD[(Long, Actor)] = pair_actors.partitionBy(partitioner).persist()
    

  var t1: Long = 0
  var t2: Long = 0

  var totalTime: Long = 0
  var elapsedRound: Int = 0
  var time_seq: List[Long] = List()

  def apply(actors: List[Actor], totalTurn: Int): SimulationSnapshot = {
    var actorRDD: RDD[Actor] = sc.parallelize(actors)

    while (currentTurn < totalTurn ) {
        println(meta.runtime.util.displayTime(currentTurn))

        if (currentTurn % 70 == 0) {
          actorRDD.checkpoint()
        }

        t1 = System.currentTimeMillis()
        
        actorRDD.persist()
        
        collectedMessages = actorRDD.flatMap(a => {
          a.sendMessages.map(i => (i._1, i._2.toList))
        }).combineByKey((msgs: List[Message]) => msgs,
          (l: List[Message], message: List[Message]) => message ::: l,
          (l1: List[Message], l2: List[Message]) => l1 ::: l2).collectAsMap()
        
        elapsedRound = actorRDD.map(x => {
          x.receivedMessages.addAll(collectedMessages.getOrElse(x.id, Buffer[Message]()).asJava)
          x.time += elapsedRound
          x.run()
        }).collect().min
        
        currentTurn += elapsedRound
        t2 = System.currentTimeMillis()
        time_seq = time_seq ::: List(t2-t1)
    }

    val updatedActors: List[Actor] = actorRDD.collect.toList
    val snapshot = SimulationSnapshot(updatedActors, collectedMessages.flatMap(i => i._2).toList)
    val average = time_seq.sum / time_seq.length
    val stdev = Math.sqrt(time_seq.map(i => (i-average)*(i-average)).sum/time_seq.length)
    println(f"Average time per round ${average} StdDev ${stdev}")
    sc.stop()
    snapshot
  }
}