package advent.day1

import advent.DayEntry
import org.apache.logging.log4j.scala.Logging

class Day1Entry extends DayEntry {
    def processInput(lines: Iterator[String]): String = {
        countIncreasing(lines.toSeq).toString
    }

  def countIncreasing(lines: Seq[String]): Int = lines match {
      case Nil => 0
      case first :: second :: remain => {
        logger.debug(s"Got parts of a line! ${first} > ${second} with ${remain}")
        (if (first.toInt < second.toInt) 1 else 0) + countIncreasing(second :: remain)
      }
      case _ => 0
  }
}
