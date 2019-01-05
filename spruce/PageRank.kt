/** TODO: package declaration
 *  TODO: 
 *
 */


import java.io.File

data class Hit(val path: String, val user: String, val timestamp: String) { 
}

fun lineParse(line: String): List<String> {
  val fields = line.split(",")
  return fields
}


fun main(args: Array<String>) {
  //val hitlist: MutableList
  File("log.csv").forEachLine { 
    val parsedLine = lineParse(it)
    println(parsedLine)
  }
}
