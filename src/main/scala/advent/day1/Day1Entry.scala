package advent.day1

import advent.DayEntry
import org.apache.logging.log4j.scala.Logging

class Day1Entry extends DayEntry {
    def processInput(lines: Iterator[String]): String = {
      val linesSeq = lines.map(_.toInt).toSeq
        countIncreasing(linesSeq).toString + ", " + countWindowed(linesSeq)
    }

  def countIncreasing(lines: Seq[Int]): Int = lines match {
      case Nil => 0
      case first :: second :: Nil => 
        if (first < second) 1 else 0
      case first :: second :: remain => {
        logger.debug(s"Got parts of a line! ${first} > ${second} with ${remain}")
        (if (first < second) 1 else 0) + countIncreasing(second :: remain)
      }
      case _ => 0
  }

  def countWindowed(lines: Seq[Int]): Int = lines match {
    case first :: second :: third :: fourth :: Nil => {
      val leftSum = first + second + third
      val rightSum = second + third + fourth
      if (leftSum < rightSum) 1 else 0
    }
    case first :: second :: third :: fourth :: remain => {
      val leftSum = first + second + third
      val rightSum = second + third + fourth
      (if (leftSum < rightSum) 1 else 0) + countWindowed(second :: third :: fourth :: remain)
    }
    case _ => 0
  }
}
