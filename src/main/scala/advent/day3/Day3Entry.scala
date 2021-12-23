package advent.day3

import advent.DayEntry

class Day3Entry extends DayEntry {
  def processInput(lines: Iterator[String]): String = {
    val binaryLines = lines.map(toBitArray).toSeq
    val (count, sums) = sumLines(binaryLines)
    logger.info(s"$count, $sums")
    val mcb = mostCommonBits(count, sums)
    val lcb = leastCommonBits(count, sums)
    logger.info(s"$mcb, $lcb")
    val part1 = (Integer.parseInt(mcb.mkString, 2) * Integer.parseInt(lcb.mkString, 2)).toString
    val mcLine = filterLines(binaryLines, mostCommonBitAt)
    val lcLine = filterLines(binaryLines, leastCommonBitAt)
    logger.info(s"$mcLine, $lcLine")
    val part2 = (Integer.parseInt(mcLine.mkString, 2) * Integer.parseInt(lcLine.mkString, 2)).toString
    part1 + ", " + part2
  }

  def toBitArray(line: String): Vector[Int] = {
      line.map(c => if (c == '1') 1 else 0).toVector
  }

  def sumLines(lines: Seq[Vector[Int]]): (Int, Vector[Int]) = {
    if (lines.isEmpty) {
      return (0, Vector.empty)
    }
    sumLines(1, lines.head, lines.tail)
  }

  def sumLines(count: Int, sums: Vector[Int], lines: Seq[Vector[Int]]): (Int, Vector[Int]) = {
    if (lines.isEmpty) {
      return (count, sums)
    }
    val newCount = count + 1
    val newSums = sumSeqs(sums, lines.head)
    sumLines(newCount, newSums, lines.tail)
  }

  def sumSeqs(l: Vector[Int], r: Vector[Int]) : Vector[Int] = {
    if (l.isEmpty) {
      return Vector.empty
    }
    (l.head + r.head) +: sumSeqs(l.tail, r.tail)
  }

  def mostCommonBits(count: Int, bits: Vector[Int]): Vector[Int] = {
    bits.map(b => if (b.toDouble / count > 0.5) 1 else 0)
  }

  def leastCommonBits(count: Int, bits: Vector[Int]): Vector[Int] = {
    bits.map(b => if (b.toDouble / count < 0.5) 1 else 0)
  }

  def mostCommonBitAt(index: Int, lines: Seq[Vector[Int]]): Int = {
    val sum = lines.map(l => l(index)).sum
    if (lines.length / sum == 2 && lines.length % sum == 0) {
      return 1
    }
    if (sum.toDouble / lines.length > 0.5) 1 else 0
  }

  def leastCommonBitAt(index: Int, lines: Seq[Vector[Int]]): Int = {
    1 - mostCommonBitAt(index, lines)
  }

  def filterLines(lines: Seq[Vector[Int]], predicate: (Int, Seq[Vector[Int]]) => Int, index: Int = 0): Vector[Int] = {
    if (lines.length == 1) {
      return lines.head
    } else if (lines.isEmpty) {
      return Vector.empty
    }
    logger.info(s"${lines.length}")
    val criteria = predicate(index, lines)
    filterLines(lines.filter(l => l(index) == criteria), predicate, index+1)
  }


}
