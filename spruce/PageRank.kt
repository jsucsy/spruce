import java.io.File

/** Next step: work out the line parser - return array of values
 *
 *fun lineParse(line: String): Array<String> {
 * return 
 *}
*/

fun main(args: Array<String>) {
  File("log.csv").forEachLine { println(it) }
}
