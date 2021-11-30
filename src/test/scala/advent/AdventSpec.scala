package advent

import org.scalatest._

class AdventSpec extends FlatSpec with Matchers {
  "The Advent object" should "be day 0" in {
    Advent.day shouldEqual 0
  }
}
