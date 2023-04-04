package simulation.spark.API

import meta.runtime.{Actor, Message}
import meta.API.{SimulationSnapshot, SimulationConfig}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.RangePartitioner
import org.apache.spark.HashPartitioner

// see https://stackoverflow.com/questions/43624401/noclassdeffounderror-could-not-initialize-xxx-class-after-deploying-on-spark-st
object SparkGlobal {
  val conf: SparkConf = 
    new SparkConf()
    // .setMaster("local[*]")
    .setAppName("TickTalk")
    // .set("spark.driver.memory", "20g")
    // .set("spark.driver.maxResultSize", "10g")
    // .set("spark.executor.memory", "20g")
    // .set("spark.executor.cores", "48")
    // .set("spark.default.parallelism", "96")
    // .set("spark.hadoop.dfs.replication", "1")
    // .set("spark.serializer", "akka.serialization.jackson.JacksonJsonSerializer")
    .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    // .set("spark.driver.allowMultipleContexts", "true")

    conf.registerKryoClasses(Array(classOf[meta.runtime.Message], classOf[meta.runtime.Actor], classOf[meta.API.SimulationSnapshot]))
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")
}