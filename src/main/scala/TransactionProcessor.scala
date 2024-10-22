import akka.actor.ActorSystem
import scala.concurrent.duration._

class TransactionProcessor(implicit system: ActorSystem) {
  private val validator = new TransactionValidator()

  def processRealTime(transaction: Transaction): Unit = {
    processTransaction(transaction)
  }

  def scheduleBatchProcessing(transactions: List[Transaction]): Unit = {
    import system.dispatcher
    system.scheduler.scheduleWithFixedDelay(0.seconds, 5.minutes)(() => processBatch(transactions))
  }

  private def processTransaction(transaction: Transaction): Unit = {
    validateTransaction(transaction) match {
      case Right(validTransaction) =>
        println(s"Transaction processed: $validTransaction")
      case Left(error) =>
        println(s"Transaction validation failed: $error")
    }
  }

  private def processBatch(transactions: List[Transaction]): Unit = {
    transactions.foreach(processTransaction)
  }

  private def validateTransaction(transaction: Transaction): Either[String, Transaction] = {
    validator.validate(transaction)
  }
}