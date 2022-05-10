package meta.deep.member

import scala.collection.mutable.ListBuffer

abstract class FieldOrMethod {
    val modifiers: ListBuffer[String]
}