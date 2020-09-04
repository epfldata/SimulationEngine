package meta.example.latency

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

import meta.deep.runtime.Future
import meta.deep.runtime.Actor.AgentId

@lift
class Client(var server: Server, var reqTime: Double, var replyTime: Double) extends Actor {

  // Each client does some processing for 0.2 unit of time before sending a request
  // Real-time makes blocking request obsolete
  var future_req: Option[Future[Int]] = None

  var sentTime: Double = 0
  var receivedTime: Double = 0

  def makeReq(): Unit = {
    println("Client " + id + " processing; take " + reqTime)
    waitTime(reqTime)

    if (future_req==None) {
      sentTime = currentTime
      println("Client " + id + " sent msg at time " + sentTime)
      future_req = asyncMessage(()=> server.request(id))
    }

    while (!isCompleted(future_req.get)) {
//      println("Client " + id + " checking response; take 0.1 time")
      waitTime(0.1)
    }
    receivedTime = currentTime
    println("Client " + id + " received reply! " + getFutureValue[Int](future_req.get) +"  Latency: " + (receivedTime - sentTime))
    //    println("Sim " + id + " Response received! "+ getFutureValue[Int](future_req.get))
    future_req = clearFutureObj(future_req.get)
  }

  def procReply(): Unit = {
    println("Client " + id + " analyzing reply; take " + replyTime)
    waitTime(replyTime)
  }

  def main(): Unit = {
    while(true) {
      makeReq()
      procReply()
    }
  }
}