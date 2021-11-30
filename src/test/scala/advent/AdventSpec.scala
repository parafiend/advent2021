package advent

import org.scalatest._
import flatspec._
import matchers._

class AdventSpec extends AnyFlatSpec with should.Matchers {
  "The Advent object" should "be day 0" in {
    Advent.day shouldEqual 0
  }
}
