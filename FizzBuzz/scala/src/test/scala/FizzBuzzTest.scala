import org.scalatest.{Matchers, FlatSpec}

class FizzBuzzTest extends FlatSpec with Matchers {
  it should "return fizz for multiplies of 3" in {
    FizzBuzz.calculate(3) should be ("fizz")
  }

  it should "return buzz for multiplies of 5" in {
    FizzBuzz.calculate(5) should be ("buzz")
  }

  it should "return fizzbuzz for multiplies of 3 and 5" in {
    FizzBuzz.calculate(15) should be ("fizzbuzz")
    FizzBuzz.calculate(30) should be ("fizzbuzz")
  }
}
