// package meta.runtime

// import scala.collection.mutable.{ListBuffer, Map => MutMap}
// import scala.util.Random
// import meta.runtime.Actor.AgentId
// import meta.API.{SimulationSnapshot, SimulationConfig}

// class BaseWithEval(c: SimulationConfig) extends Base(c.actors, c.totalTurn) {
//   def run[T](filter:(List[Actor], List[Message]) => T): List[T] = {
//       val ans: ListBuffer[T] = new ListBuffer[T]()
//       while (currentTurn < totalTurn) {
//         // println(util.displayTime(currentTurn))
//         val mx = collectedMessages.groupBy(_.receiverId)
//         val res = actors.filterNot(_.deleted).map(a => {
//           val targetMessages: List[Message] = a.proxyIds.flatMap(id => mx.getOrElse(id, List()))
//           a.run(targetMessages)
//         }).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
//         collect()
//         collectedMessages = res._1
//         proceed(res._2)
//         ans.append(filter(actors, collectedMessages))
//       }

//       // Actor.reset
//       ans.toList
//     }
// }