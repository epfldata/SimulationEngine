package meta.example

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import org.slf4j.LoggerFactory
import ch.qos.logback.classic.Level._

import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer

object recordLogEvents {
  def record[T](block: => T): immutable.Seq[ILoggingEvent] = {

    /** Collects all log events that occur */
    class RecordingAppender extends AppenderBase[ILoggingEvent] {
      private val eventBuffer = ArrayBuffer[ILoggingEvent]()
      override def append(e: ILoggingEvent): Unit = synchronized {
        eventBuffer += e
      }
      def events: immutable.Seq[ILoggingEvent] = synchronized {
        eventBuffer.to[immutable.Seq]
      }
    }

    // Get the Logback root logger and attach a RecordingAppender
    val rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[Logger]
    val appender = new RecordingAppender()
    appender.setContext(rootLogger.getLoggerContext)
    appender.start()
    rootLogger.addAppender(appender)
    block
    rootLogger.detachAppender(appender)
    appender.stop()
    appender.events
  }
}

