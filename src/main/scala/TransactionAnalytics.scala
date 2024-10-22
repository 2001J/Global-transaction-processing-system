class TransactionAnalytics {
  def generateReport(transactions: List[Transaction]): Map[String, Any] = {
    val totalTransactionCount = transactions.size
    val totalTransactionValue = calculateTotalTransactionValue(transactions)
    val averageTransactionValue = calculateAverageTransactionValue(totalTransactionCount, totalTransactionValue)
    val transactionsByCurrency = groupTransactionsByCurrency(transactions)

    Map(
      "totalTransactionCount" -> totalTransactionCount,
      "totalTransactionValue" -> totalTransactionValue,
      "averageTransactionValue" -> averageTransactionValue,
      "transactionsByCurrency" -> transactionsByCurrency
    )
  }

  private def calculateTotalTransactionValue(transactions: List[Transaction]): Double = {
    transactions.map(_.amount).sum
  }

  private def calculateAverageTransactionValue(totalTransactionCount: Int, totalTransactionValue: Double): Double = {
    if (totalTransactionCount > 0) totalTransactionValue / totalTransactionCount else 0.0
  }

  private def groupTransactionsByCurrency(transactions: List[Transaction]): Map[String, Int] = {
    transactions.groupBy(_.currency).view.mapValues(_.size).toMap
  }
}