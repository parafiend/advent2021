package advent.day3

import advent.DayEntry

class Day3Entry extends DayEntry {
  def processInput(lines: Iterator[String]): String = {
      val (count, sums) = sumLines(lines.map(toBitArray))
      logger.info(s"$count, $sums")
      (Integer.parseInt(mostCommonBits(count, sums), 2) * Integer.parseInt(leastCommonBits(count, sums), 2)).toString
  }

  def toBitArray(line: String): List[Int] = {
      line.map(c => if (c == '1') 1 else 0).toList
  }

  def sumLines(lines: Iterator[List[Int]]): (Int, List[Int]) = {
    if (lines.isEmpty) {
      return (0, List.empty)
    }
    sumLines(0, lines.next, lines)
  }

  def sumLines(count: Int, sums: List[Int], lines: Iterator[List[Int]]): (Int, List[Int]) = {
    if (lines.isEmpty) {
      return (count, sums)
    }
    val newCount = count + 1
    val newSums = sumSeqs(sums, lines.next())
    sumLines(newCount, newSums, lines)
  }

  def sumSeqs(l: List[Int], r: List[Int]) : List[Int] = {
    if (l.isEmpty) {
      return List.empty
    }
    l.head + r.head :: sumSeqs(l.tail, r.tail)
  }

  def mostCommonBits(count: Int, bits: List[Int]): String = {
    bits.map(b => if (b.toDouble / count > 0.5) '1' else '0').mkString
  }

  def leastCommonBits(count: Int, bits: List[Int]): String = {
    bits.map(b => if (b.toDouble / count < 0.5) '1' else '0').mkString
  }
}
