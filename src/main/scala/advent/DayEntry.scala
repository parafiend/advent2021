package advent

import scala.language.reflectiveCalls
import org.apache.logging.log4j.scala.Logging

trait DayEntry extends Logging {
  def processInput(lines: Iterator[String]): String

  def enter(input: String): String = {
    applyToInput(input, processInput)
  }

  def applyToInput(input: String, f: Iterator[String] => String): String = {
      val source = io.Source.fromFile(input)
      try {
          f(source.getLines())
      } finally {
          source.close()
      }
  }
}

object DayEntry {
    def apply(day: Integer) = {
        val className = s"advent.day${day}.Day${day}Entry".format(day)
        Class.forName(className).getDeclaredConstructor().newInstance().asInstanceOf[DayEntry]
    }
}
