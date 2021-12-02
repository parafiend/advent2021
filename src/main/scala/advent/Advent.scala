package advent

import day0.Day0Entry

import scala.io.StdIn.readLine

object Advent {
  val day = 0

  def motd(day: Int): String = {
    val motd = "Hello, world! It's day %d"
    motd.format(day)
  }

  def day0Context(): Iterator[String] = {
      io.Source.fromFile("inputs/day0.txt").getLines()
  }

  def main(args: Array[String]): Unit = {
    println(motd(day));
    //val input = "inputs/day0.txt"
    val className = "advent.day0.Day%dEntry".format(day)
    //val entry = Class.forName(className).newInstance.asInstanceOf[DayEntry]
    val dayIn = readLine("What day am I?")
    val input = s"inputs/day${dayIn}.txt"
    val entry = DayEntry(dayIn.toInt)
    println(entry.enter(input))
  }
}
