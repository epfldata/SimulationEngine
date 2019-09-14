import meta.deep.runtime.Actor.AgentId
import meta.deep.runtime.{Actor, Message}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SimulationSpark extends App {

  @transient lazy val conf: SparkConf =
    new SparkConf().setMaster("local").setAppName("ECONOMIC_SIMULATION")
  @transient lazy val sc: SparkContext = new SparkContext(conf)

  var actors: RDD[(AgentId, Actor)] = _
  var timer = 0
  var until = 100

  def init(): Unit = {
    actors = sc.parallelize(generated.InitData.initActors).map(x => (x.id, x))
  }

  def main(): Unit = {
    sc.setLogLevel("ERROR")
    sc.setCheckpointDir("checkpoint/")

    init()
    val start = System.nanoTime()

    while (timer <= until) {

      // Checkpoint actors object, so that it does not get too big
      // see: https://stackoverflow.com/questions/36421373/java-lang-stackoverflowerror-and-checkpointing-on-spark
      println("TIMER", timer)
      if (timer % 50 == 0) {
        actors.checkpoint()
      }

      //Execute all actors
      actors = actors.mapValues(a => {
        a.cleanSendMessage.run_until(timer)
      })

      //Collect all messages from the round
      val dMessages: RDD[(AgentId, List[Message])] = actors
        .flatMap(_._2.getSendMessages)
        .map(x => (x.receiverId, x))
        .combineByKey(
          (message: Message) => {
            List(message)
          },
          (l: List[Message], message: Message) => {
            message :: l
          },
          (l1: List[Message], l2: List[Message]) => {
            l1 ::: l2
          }
        )
        .cache()

      //Distribute messages for the next round
      actors = actors
        .leftOuterJoin(dMessages)
        .mapValues { x =>
          x._1.addReceiveMessages(x._2.getOrElse(List()))
          x._1
        }
        .cache()

      timer += 1
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
