package meta.runtime

/**
  * An asynchronous call returns a future object
  * @param id: a unique id, used to distinguish different future obj in the same turn
  * @param value: the return value of the future object, when completed
  * @tparam T: the return type
  */
case class Future[+T](val id: String, 
                      val value: Option[T] = None){

  def setValue[U >: T](y: U): Future[U] ={
    Future(id, Some(y))
  }

  def isCompleted: Boolean = {
    SimRuntime.isCompleted(this)
  }

  // Single-entry 
  def popValue: Option[T] = {
    SimRuntime.popFutureValue(this) 
  }
}