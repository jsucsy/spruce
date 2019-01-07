package spruce.logmetrics
import java.io.File
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

/**
 * Data class for page hits from HTTP log
 * @param path: HTTP path of page visited
 * @param user: USERID visiting page
 * @param timestamp: ZonedDateTime timestamp of page visit
 * @param date: ZonedDateTime truncated date-only timestamp to speed up date grouping/filtering
 */
data class Hit(
    val path: String,
    val user: String,
    val timestamp: ZonedDateTime,
    val date: ZonedDateTime
)

/**
 * Opens HTTP log
 * Reads file and runs error checks
 * @param logfile: path to log file
 * @return a List<String> of all lines except 1-line header
 */
fun openLog(logfile: String): List<String> {
    // read file, run error checks, and drop header
    val allLines = File(logfile).bufferedReader().readLines()
    val header = allLines[0]
    // error checks - I would expect to expand/modify this based on testing
    if (header != "Path,User,Timestamp") {
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("Expected format is (Path,User,Timestamp), got $header")
        println("Please confirm that log file $logfile is correct")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****\n\n")
    }
    return allLines.drop(1)
}

/**
 * Parses line from HTTP log into Hit data class
 * @param line: line from HTTP log
 * @return hit: page visit data in Hit class format
 */
fun lineParse(line: String): Hit {
    val fields = line.split(",")
    val ts = ZonedDateTime.parse(fields[2])
    val date = ts.truncatedTo(ChronoUnit.DAYS)
    val hit = Hit(fields[0], fields[1], ts, date)
    return hit
}

/**
 * Calculate and print metrics of HTTP log
 * produce by-day rankings of:
 * - pages by number of users: counts total hits for each page regardless of duplicates hits by user
 * - users by unique page views: ERROR:: should count unique views, currently counts duplicates
 * @param hitList: HTTP log in List<Hit> format
 * @return currently returns nothing, all output is printed directly
 * A full production implementation would account for the intended use of this data
 */
fun calcMetrics(hitList: List<Hit>) {
    val groupHitsByDate = hitList.groupBy { it -> it.date }
    val dateKeys = groupHitsByDate.keys
    for (key in dateKeys) {
        println("Rankings for $key")
        val pagesByUsers = hitList
            .filter { it.date == key }
            .groupingBy { it.path }.eachCount()
        val userByViews = hitList
            .filter { it.date == key }
            .groupingBy { it.user }.eachCount()
        for (pg in pagesByUsers) { println(pg) }
        for (us in userByViews) { println(us) }
        println()
    }
}

/** Main program entry */
fun main(args: Array<String>) {
    val logfile = "log.csv"
    val lines = openLog(logfile)
    val hits = lines.map { lineParse(it) }
    calcMetrics(hits)
}
