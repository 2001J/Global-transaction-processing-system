class FraudDetector {
  private val suspiciousIpThreshold = 5
  private val suspiciousTimeFrame = 10000 // 10 seconds

  def detect(transactions: List[Transaction]): List[Transaction] = {
    transactions.groupBy(_.ipAddress).values.flatMap { txs =>
      if (isSuspicious(txs)) txs else List.empty[Transaction]
    }.toList
  }

  private def isSuspicious(transactions: List[Transaction]): Boolean = {
    transactions.size > suspiciousIpThreshold &&
      (transactions.last.timestamp - transactions.head.timestamp) < suspiciousTimeFrame
  }
}