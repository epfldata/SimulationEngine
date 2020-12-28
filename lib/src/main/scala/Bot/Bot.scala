package lib

package object Bot {
  type Actor = meta.runtime.Actor
  val Actor = meta.runtime.Actor
  type Future[T] = meta.runtime.Future[T]
  val Future = meta.runtime.Future
  val SimRuntime = meta.runtime.SimRuntime
}
