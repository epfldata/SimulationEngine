package meta.runtime

/**
  * An asynchronous call returns a future object
  * @param id: a unique id, used to distinguish different future obj in the same turn
  * @param value: the return value of the future object, when completed
  * @tparam T: the return type
  */
case class Future[+T](val id: String, 
                      val value: Option[T] = None){

  // The call back (message handler) of an asynchronous message will call setValue to update the value of the asynchronous call                       
  def setValue[U >: T](y: U): Future[U] ={
    Future(id, Some(y))
  }

  // Check whether the future object has received its value, allow repeated checking  
  def isCompleted: Boolean = {
    SimRuntime.isCompleted(this)
  }

  // Return the value of the future object. If None, then hasn't completed; otherwise, return Some(value). Once the value is ready, can only call popValue at most once, which will remove the future object from the internal async_message map; subsequent calls will return None   
  def popValue: Option[T] = {
    SimRuntime.popFutureValue(this) 
  }
}