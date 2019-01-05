/** TODO: 
 *
 */

package spruce.logmetrics
import java.io.File

data class Hit(val path: String, val user: String, val timestamp: String) { 
}

fun lineParse(line: String): Hit {
  val fields = line.split(",")
  val hit = Hit(fields[0], fields[1], fields[2])
  return hit
}


fun main(args: Array<String>) {
  // read file, confirm format, and drop header
  val logfile = "log.csv"
  val allLines = File(logfile).bufferedReader().readLines() 
  val header = allLines[0]
  if (header != "Path,User,Timestamp") { 
    println("***** WARNING: UNEXPECTED LOG FORMAT *****")
    println("***** WARNING: UNEXPECTED LOG FORMAT *****")
    println("Expected format is (Path,User,Timestamp), got $header")
    // val filepath = File(logfile).getPath()
    println("Please confirm that log file $logfile is correct")
    println("***** WARNING: UNEXPECTED LOG FORMAT *****")
    println("***** WARNING: UNEXPECTED LOG FORMAT *****\n\n")
  }
  val lines = allLines.drop(1)

  for (line in lines) {
    val parsedLine = lineParse(line)
    println(parsedLine)
  }
}
