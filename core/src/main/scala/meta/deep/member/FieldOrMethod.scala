package meta.deep.member

import scala.collection.mutable.ListBuffer

abstract class FieldOrMethod {
    val modifiers: ListBuffer[String] = ListBuffer[String]()
    val compileTimeModifiers: ListBuffer[String] = ListBuffer[String]()
    val name: String
}