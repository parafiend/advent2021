package advent.day2

import advent.DayEntry


class Day2Entry extends DayEntry {
  def processInput(lines: Iterator[String]): String = {
      val (x, y) = lines.map(calculateDirection(_)).reduce((x, y) => (x._1 + y._1, x._2 + y._2))
      (x * y).toString
  }

  def calculateDirection(line: String): (Int, Int) = line match {
      case s"forward $distVal" => (distVal.toInt, 0)
      case s"up $distVal" => (0, -1 * distVal.toInt)
      case s"down $distVal" => (0, distVal.toInt)
      case _ => 
          logger.info(s"$line")
          (0, 0)
  } 
  
}