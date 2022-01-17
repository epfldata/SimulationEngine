package meta.runtime
package simulation

object util {
  def displayTime(turn: Int): String = {
    "(Turn " + turn + ")"
  }

  def bench(code: => Unit ): Long = {
    val start: Long = System.currentTimeMillis()
    code
    val end: Long = System.currentTimeMillis()
    val total = end - start
    println(s"Total time: ${total} ms")
    total
  }
}
