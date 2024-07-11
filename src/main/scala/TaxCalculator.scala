class TaxCalculator {

  // Tax bands (simplified to make testing a bit easier)
  private val personalAllowance: Int = 10000
  private val basicRateLimit: Int = 50000
  private val higherRateLimit: Int = 125000

  // Tax rates
  private val personalAllowanceRate: Double = 0
  private val basicRate: Double = 0.2
  private val higherRate: Double = 0.4
  private val additionalRate: Double = 0.45

  // capital gains shares
  private val minTaxableAmountForShares = 3000
  private val capitalGainsRateForBasicRate = 0.1
  private val capitalGainsRateForHigherRate = 0.2

  // A method to calculate the total amount of tax to be paid, returned as a double
  def calculateTax(income: Double): Double = {
    if (income <= personalAllowance) {
      income * personalAllowanceRate
    } else if (income <= basicRateLimit) {
      income * basicRate
    } else if (income <= higherRateLimit) {
      income * higherRate
    } else {
      0.00
    }

  }

  // A method which can tell you if someone is a higher rate taxpayer
  def isHigherRateTaxpayer(income: Double): Boolean = {
    if (income <= higherRateLimit && income > basicRateLimit) {
      true
    } else if (income > higherRateLimit) {
      true
    } else {
      false
    }
  }

  // A method that will return a string with the income limit of their current tax band.
  // The return will also be formatted, E.g: "£12,500" or "No limit"

  def formatDoubleToCurrencyString(num:Double): String = {
    val result:String = f"£$num%,.0f"
    result
  }


  def formattedCurrentTaxAllowance(income: Double): String = {
    if (income <= personalAllowance) {
      formatDoubleToCurrencyString(personalAllowance)
    } else if (income <= basicRateLimit) {
      formatDoubleToCurrencyString(basicRateLimit)
    } else if (income <= higherRateLimit) {
      formatDoubleToCurrencyString(higherRateLimit)
    } else if (income > higherRateLimit ){
      "No limit"
    } else {
      "error handling income"
    }
  }

  def calculateCapitalGainsFromShares(income: Double, sharesValue:Double): String = {
    if (income <= personalAllowance && sharesValue >= minTaxableAmountForShares) {
      formatDoubleToCurrencyString(sharesValue * capitalGainsRateForBasicRate)
    } else if (income <= basicRateLimit && sharesValue >= minTaxableAmountForShares) {
      formatDoubleToCurrencyString(sharesValue * capitalGainsRateForBasicRate)
    } else if (income <= higherRateLimit && sharesValue >= minTaxableAmountForShares) {
      formatDoubleToCurrencyString(sharesValue * capitalGainsRateForHigherRate)
    } else if (income > higherRateLimit && sharesValue >= minTaxableAmountForShares){
      formatDoubleToCurrencyString(sharesValue * capitalGainsRateForHigherRate)
    } else if (sharesValue < minTaxableAmountForShares){
      "shares are below taxable threshold"
    } else {
      "error handling values"
    }
  }
}
