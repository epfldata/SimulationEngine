package meta.runtime
package simulation

object util {
  def displayTime(turn: Int): String = {
    "(Turn " + turn + ")"
  }

  def bench(code: => Unit ): Unit = {
    val start: Long = System.currentTimeMillis()
    code
    val end: Long = System.currentTimeMillis()
    println(s"Total time: ${end - start} ms")
  }
}
