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

  /** format doubles */
  "TaxCalculator.formatDoubleToCurrencyString" should {
    "return a formatted currency string" when {
      "given a Double" in {
        val result: String = taxCalculator.formatDoubleToCurrencyString(40000)

        assert(result == "£40,000")
      }
    }
  }


/** formatted current tax allowance */

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return a formatted string of the basic tax rate limit" when {
      "the income falls into the basic tax band bracket" in {
        val result: String = taxCalculator.formattedCurrentTaxAllowance(40000)

        assert(result == "£50,000")
      }
    }
  }

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return a formatted string of the higher tax rate limit" when {
      "the income falls into the higher tax band bracket" in {
        val result: String = taxCalculator.formattedCurrentTaxAllowance(80000)

        assert(result == "£125,000")
      }
    }
  }

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return a formatted string of the personal tax rate limit" when {
      "the income falls into the personal tax band bracket" in {
        val result: String = taxCalculator.formattedCurrentTaxAllowance(9000)

        assert(result == "£10,000")
      }
    }
  }

  "TaxCalculator.formattedCurrentTaxAllowance" should {
    "return string 'no limit'" when {
      "the income is above higher income tax bracket" in {
        val result: String = taxCalculator.formattedCurrentTaxAllowance(125001)

        assert(result == "No limit")
      }
    }
  }

  /** capital gains tax from shares */

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return string of the amount of tax to be paid from shares" when {
      "the tax band is basic and shares are above taxable threshold" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(20000, 5000)

        assert(result == "£500")
      }
    }
  }

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return string of the amount of tax to be paid from shares" when {
      "the tax band is higher and shares are above taxable threshold" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(80000, 5000)

        assert(result == "£1,000")
      }
    }
  }

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return string of the amount of tax to be paid from shares" when {
      "the tax band is personal and shares are above taxable threshold" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(9000, 5000)

        assert(result == "£500")
      }
    }
  }

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return 'below threshold'" when {
      "the shares are worth below the taxable threshold with higher tax band" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(80000, 2999)

        assert(result == "shares are below taxable threshold")
      }
    }
  }

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return 'below threshold'" when {
      "the shares are worth below the taxable threshold with basic tax band" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(20000, 2999)

        assert(result == "shares are below taxable threshold")
      }
    }
  }

  "TaxCalculator.calculateCapitalGainsFromShares" should {
    "return 'below threshold'" when {
      "the shares are worth below the taxable threshold with personal tax band" in {
        val result: String = taxCalculator.calculateCapitalGainsFromShares(9000, 2999)

        assert(result == "shares are below taxable threshold")
      }
    }
  }

}