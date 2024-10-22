class TransactionValidator {
  private val maxTransactionAmount = 10000.0
  private val supportedCurrencies: Set[String] = Set("USD", "EUR", "GBP")

  def validate(transaction: Transaction): Either[String, Transaction] = {
    for {
      _ <- validateAmount(transaction.amount)
      _ <- validateCurrency(transaction.currency)
    } yield transaction
  }

  private def validateAmount(amount: Double): Either[String, Unit] = {
    if (amount > maxTransactionAmount) {
      Left("Transaction exceeds allowed limit.")
    } else {
      Right(())
    }
  }

  private def validateCurrency(currency: String): Either[String, Unit] = {
    if (!supportedCurrencies.contains(currency)) {
      Left("Unsupported currency.")
    } else {
      Right(())
    }
  }
}