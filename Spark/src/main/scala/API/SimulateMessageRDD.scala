package simulation.spark.API

import scala.collection.mutable.{Buffer, Map => MutMap}

import meta.runtime.{Actor, Message}
import meta.API.{SimulationSnapshot, SimulationConfig}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.RangePartitioner
import org.apache.spark.HashPartitioner

import collection.JavaConverters._

// 2 RDDs, agents and messages
object SimulateMessageRDD { 
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
    var t1: Long = 0
    var t2: Long = 0

    var totalTime: Long = 0
    var elapsedRound: Int = 0
    var time_seq: List[Long] = List()

    var actorRDD: RDD[(Long, Actor)] = sc.parallelize(actors).map(i => (i.id, i))
    var messageRDD: RDD[(Long, List[Message])] = actorRDD.mapValues(i => List())

    // var actorRDD: RDD[(Actor, List[(Long, List[Message])])] = sc.parallelize(actors).map(x => (x, x.sendMessages.map(i => (i._1, i._2.toList)).toList))

    while (currentTurn < totalTurn ) {
        println(meta.runtime.util.displayTime(currentTurn))

        if (currentTurn % 70 == 0) {
          actorRDD.checkpoint()
        }

        t1 = System.currentTimeMillis()

        // (Actor, inbox) => (Actor, outbox)
        actorRDD = actorRDD.leftOuterJoin(messageRDD).mapValues(x => {
          x._1.receivedMessages.addAll(x._2.getOrElse(Buffer[Message]()).asJava)
          x._1.time += elapsedRound
          x._1.run()
          x._1
        })
        actorRDD.cache()
        messageRDD = actorRDD.flatMap(x => x._2.sendMessages.map(m => (m._1, m._2.toList))).reduceByKey((m1, m2) => m1 ::: m2)
        elapsedRound = actorRDD.map(x => x._2.proposeInterval).collect().min
        currentTurn += elapsedRound
        // shuffle messages
        messageRDD.cache()
        messageRDD.count()    
        // actorRDD.unpersist()
        // messageRDD.unpersist()    
        t2 = System.currentTimeMillis()
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