//package meta.example.TwoPL_example
//import meta.deep.runtime.Actor
//import squid.quasi.lift
//
//import scala.collection.mutable.ListBuffer
//
//@lift
//class MainInit {
//  def main(): List[Actor] = {
//    val l = ListBuffer[Actor]()
////    val secretWords: List[String] = List("China", "Switzerland", "France", "Spain")
//    def secretWords: List[String] = List("China", "Switzerland", "France", "Spain")
////    val secrets: Array[SharedObject] = Array[SharedObject]()
//
//    secretWords.foreach(word => {
//      val shared_object: SharedObject = new SharedObject()
//      shared_object.secret = word
////      secrets :+ shared_object
//      l += shared_object
//    })
//
////    (1 to 3).foreach(_ => {
////      val shared_object: SharedObject = new SharedObject()
////      shared_object.secret = shared_object.id.toString()
//////      secrets += shared_object
////      l += shared_object
////    })
//
////    secretWords.foreach(word => {
////      val shared_object: SharedObject = new SharedObject()
////      shared_object.secret = word
////      secrets :+ shared_object
////    })
//
////    l ++= secrets.toList
//    l.toList
//  }
//}