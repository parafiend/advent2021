package advent.day2

import advent.DayEntry


class Day2Entry extends DayEntry {
  def processInput(lines: Iterator[String]): String = {
      val inputDirections = lines.toSeq.map(calculateDirection(_))
      val (part1x, _, part1y) = inputDirections.foldLeft((0, 0, 0))((x, y) => (x._1 + y._1, 0, x._3 + y._3))
      val (part2x, part2y, part2aim) = inputDirections.foldLeft((0, 0, 0))((x, y) => {
        //logger.info(s"$x, $y")
        (x._1 + y._1, x._2 + (x._3 * y._1), x._3 + y._3)
      })
      //logger.info(s"$part2x, $part2y, $part2aim")
      (part1x * part1y).toString + ", " + (part2x * part2y).toString
  }

  def calculateDirection(line: String): (Int, Int, Int) = line match {
      case s"forward $distVal" => (distVal.toInt, 0, 0)
      case s"up $distVal" => (0, 0, -1 * distVal.toInt)
      case s"down $distVal" => (0, 0, distVal.toInt)
      case _ => 
          logger.info(s"$line")
          (0, 0, 0)
  }

  
}