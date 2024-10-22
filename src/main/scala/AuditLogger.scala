import java.io.{BufferedWriter, FileWriter, IOException}

class AuditLogger(logFilePath: String = "transaction_audit.log") {

  // Method to log the transaction and action (e.g., create, update, delete)
  def logTransaction(transaction: Transaction, action: String): Unit = {
    val logEntry = createLogEntry(transaction, action)
    writeLogEntry(logEntry)
  }

  // Private method to create the log entry
  private def createLogEntry(transaction: Transaction, action: String): String = {
    val currentTimestamp = System.currentTimeMillis() // Log entry creation time
    s"Action: $action, Transaction ID: ${transaction.id}, Amount: ${transaction.amount}, Currency: ${transaction.currency}, " +
      s"Transaction Time: ${transaction.timestamp}, IP Address: ${transaction.ipAddress}, Log Time: $currentTimestamp"
  }

  // Private method to write the log entry to the specified log file
  private def writeLogEntry(logEntry: String): Unit = {
    val fileWriter = new BufferedWriter(new FileWriter(logFilePath, true)) // Append mode
    try {
      fileWriter.write(logEntry)
      fileWriter.newLine()
    } catch {
      case e: IOException =>
        println(s"Error writing log entry: ${e.getMessage}") // Handle any IOExceptions
    } finally {
      fileWriter.close() // Ensure the file is closed
    }
  }
}
