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
    val deployOption = Option(System.getProperty("sparkDeploy")).getOrElse("local")
    
    @transient lazy val conf: SparkConf = new SparkConf()
      .setAppName("TickTalk")
      .set("spark.driver.memory", "50g")
      .set("spark.driver.maxResultSize", "10g")
      .set("spark.executor.memory", "5g")
      .set("spark.executor.cores", "48")
      .set("spark.default.parallelism", "96")
      .set("spark.hadoop.dfs.replication", "1")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // .set("spark.driver.allowMultipleContexts", "true")
    
    if (deployOption == "local") {
      conf.setMaster("local[*]")
    } 

    conf.registerKryoClasses(Array(classOf[meta.runtime.Message]))
    @transient lazy val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")

  def apply(actors: List[Actor], totalTurn: Int): SimulationSnapshot = {

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
    var t3: Long = 0

    var totalTime: Long = 0
    var elapsedRound: Int = 0
    var time_seq: List[Long] = List()

    var actorRDD: RDD[Actor] = sc.parallelize(actors)

    while (currentTurn < totalTurn ) {
        println(meta.runtime.util.displayTime(currentTurn))

        if (currentTurn % 70 == 0) {
          actorRDD.checkpoint()
        }

        t1 = System.currentTimeMillis()

        collectedMessages = actorRDD.flatMap(a => {
          a.sendMessages.map(i => (i._1, i._2.toList))
        }).combineByKey((msgs: List[Message]) => msgs,
          (l: List[Message], message: List[Message]) => message ::: l,
          (l1: List[Message], l2: List[Message]) => l1 ::: l2).collectAsMap()

        // println(collectedMessages)
        val dMessages = sc.broadcast(collectedMessages)
        t2 = System.currentTimeMillis()
        // println(f"Iteration ${currentTurn} send new messages takes ${t2-t1} ms")

        actorRDD = actorRDD.map(x => {
          x.receivedMessages.addAll(dMessages.value.getOrElse(x.id, Buffer[Message]()).asJava)
          x.time += elapsedRound
          x.run()
          x
        })
        actorRDD.cache()
        elapsedRound = actorRDD.map(i => i.proposeInterval).collect.min
        currentTurn += elapsedRound
        t3 = System.currentTimeMillis()
        // println(f"Iteration ${currentTurn} update vertices takes ${t3-t2} ms")

        time_seq = time_seq ::: List(t3-t1)
    }

    val updatedActors: List[Actor] = actorRDD.collect.toList
    val snapshot = SimulationSnapshot(updatedActors, collectedMessages.flatMap(i => i._2).toList)
    // val average = time_seq.sum / time_seq.length
    val average = time_seq.sum / totalTurn
    // val stdev = Math.sqrt(time_seq.map(i => (i-average)*(i-average)).sum/time_seq.length)
    // println(f"Time sequence ${time_seq}")
    println(f"Average time per round ${average}")
    // println(f"Average time per round ${average} StdDev ${stdev}")
    sc.stop()
    snapshot
  }
}