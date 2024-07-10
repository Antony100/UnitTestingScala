import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class TaxCalculatorSpec extends AnyWordSpec {

  val taxCalculator: TaxCalculator = new TaxCalculator

  // I've done the first test for you!
  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is below the personal tax limit" in {
        val result: Double = taxCalculator.calculateTax(5000)

        assert(result == 0)
      }
    }
  }

  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is above the personal tax limit but below or equal to the basic rate limit" in {
        val result: Double = taxCalculator.calculateTax(20000)

        assert(result == 4000)
      }
    }
  }

  "TaxCalculator.calculateTax" should {
    "return the total amount of tax to pay" when {
      "the income is above the basic tax limit but below or equal to the higher rate limit" in {
        val result: Double = taxCalculator.calculateTax(60000)

        assert(result == 24000)
      }
    }
  }


  /** is higher rate taxpayer */
  "TaxCalculator.isHigherRateTaxpayer" should {
    "return true" when {
      "when the income falls into the higher rate tax band" in {
        val result: Boolean = taxCalculator.isHigherRateTaxpayer(60000)

        assert(result)
      }
    }
  }

  "TaxCalculator.isHigherRateTaxpayer" should {
    "return false" when {
      "when the income is not in higher rate tax band" in {
        val result: Boolean = taxCalculator.isHigherRateTaxpayer(10000)

        assert(!result)
      }

    }
  }
}