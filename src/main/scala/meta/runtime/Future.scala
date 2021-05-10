package meta.runtime

import java.util.UUID

/**
  * An asynchronous call returns a future object
  * @param id: a unique id, used to distinguish different future obj in the same turn
  * @param value: the return value of the future object, when completed
  * @tparam T: the return type
  */
case class Future[T](val id: String = UUID.randomUUID().toString, 
                    var value: Option[T] = None){

  // The call back (message handler) of an asynchronous message will call setValue to update the value                       
  def setValue(y: T): Unit ={
    value = Some(y)
  }

  // Check whether the future object has received its value, allow repeated checking  
  def isCompleted: Boolean = {
    value.isDefined
  }

  // Return the value of the future object. If None, then hasn't completed; otherwise, return Some(value). 
  def popValue: Option[T] = {
    value
  }
}