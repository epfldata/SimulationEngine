package simulation.spark.API

import scala.collection.mutable.{Buffer, Map => MutMap}

import meta.runtime.{Actor, Message}
import meta.API.{SimulationSnapshot, SimulationConfig}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.RangePartitioner
import org.apache.spark.HashPartitioner
import org.apache.spark.storage.StorageLevel

import collection.JavaConverters._

// Use two actor RDDs 
object SimulateMessageRDD2 { 
    val deployOption = Option(System.getProperty("sparkDeploy")).getOrElse("local")
    
    @transient lazy val conf: SparkConf = new SparkConf()
      .setAppName("TickTalk")
      .set("spark.driver.maxResultSize", "10g")
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
    var t1: Long = 0
    var t2: Long = 0

    var totalTime: Long = 0
    var elapsedRound: Int = 0
    var time_seq: List[Long] = List()

    var actorRDD: RDD[(Long, Actor)] = sc.parallelize(actors).map(i => (i.id, i))
    var messageRDD: RDD[(Long, List[Message])] = actorRDD.mapValues(i => List())

    while (currentTurn < totalTurn ) {
        println(meta.runtime.util.displayTime(currentTurn))

        // if (currentTurn % 70 == 0) {
        //   actorRDD.checkpoint()
        //   messageRDD.checkpoint()
        //   // updatedActorRDD.checkpoint()
        // }

        t1 = System.currentTimeMillis()

        val updatedActorRDD = actorRDD
        // .map(i => {println(currentTurn); i})
        .leftOuterJoin(messageRDD).mapValues(x => {
          x._1.receivedMessages.addAll(x._2.getOrElse(Buffer[Message]()).asJava)
          x._1.time += elapsedRound
          x._1.run()
          x._1
        }).persist(StorageLevel.MEMORY_ONLY)

        elapsedRound = updatedActorRDD.map(x => x._2.proposeInterval).collect().min

        messageRDD.unpersist()
        actorRDD.unpersist()
        messageRDD = updatedActorRDD.flatMap(x => {
          // println(x._2.sendMessages.size); 
          x._2.sendMessages.map(m => (m._1, m._2.toList))}).reduceByKey((m1, m2) => m1 ::: m2).persist(StorageLevel.MEMORY_ONLY)
        
        messageRDD.count()

        actorRDD = updatedActorRDD
        currentTurn += elapsedRound
        t2 = System.currentTimeMillis()
        println(f"Iteration ${currentTurn} takes ${t2-t1} ms")
        time_seq = time_seq ::: List(t2-t1)
    }

    val updatedActors: List[Actor] = actorRDD.map(i => i._2).collect.toList
    val snapshot = SimulationSnapshot(updatedActors, messageRDD.collect.flatMap(i => i._2).toList)
    // val average = time_seq.sum / time_seq.length
    val average = time_seq.sum / totalTurn
    val stdev = Math.sqrt(time_seq.map(i => (i-average)*(i-average)).sum/time_seq.length)
    println(f"Time sequence ${time_seq}")
    println(f"Average time per round ${average}")
    println(f"Average time per round ${average} StdDev ${stdev}")
    sc.stop()
    snapshot
  }
}