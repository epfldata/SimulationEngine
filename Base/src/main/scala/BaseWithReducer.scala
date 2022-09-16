// package meta.runtime

// import scala.collection.mutable.{ListBuffer, Map => MutMap}
// import scala.util.Random
// import meta.runtime.Actor.AgentId
// import meta.API.{SimulationSnapshot, SimulationConfig}

// class BaseWithReducer(c: SimulationConfig) extends Base(c.actors, c.totalTurn) {
//   def run[K, T](mapper: Actor => K, reducer: List[K] => T): List[T] = {
//       val ans: ListBuffer[T] = new ListBuffer[T]()

//       while (currentTurn < totalTurn) {
//         // println(util.displayTime(currentTurn))
//         val mx = collectedMessages.groupBy(_.receiverId)
//         val collectAll = actors.filterNot(_.deleted).map(a => {
//           val targetMessages: List[Message] = a.proxyIds.flatMap(id => mx.getOrElse(id, List()))
//           a.runAndEval[K](targetMessages, mapper)
//         })
//         val res = collectAll.map(_._1).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
//         collect()
//         collectedMessages = res._1
//         proceed(res._2)
//         ans.append(reducer(collectAll.map(_._2)))
//       }

//       // Actor.reset
//       ans.toList
//     }
// }