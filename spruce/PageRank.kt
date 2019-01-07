package spruce.logmetrics
import java.io.File
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

data class Hit(
    val path: String,
    val user: String,
    val timestamp: ZonedDateTime,
    val date: ZonedDateTime
)

fun openLog(logfile: String): List<String> {
    // read file, run error checks, and drop header
    val allLines = File(logfile).bufferedReader().readLines()
    val header = allLines[0]
    // error checks - I would expect to expand this based on testing
    if (header != "Path,User,Timestamp") {
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("Expected format is (Path,User,Timestamp), got $header")
        // val filepath = File(logfile).getPath()
        println("Please confirm that log file $logfile is correct")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****")
        println("***** WARNING: UNEXPECTED LOG FORMAT *****\n\n")
    }
    return allLines.drop(1)
}

fun lineParse(line: String): Hit {
    val fields = line.split(",")
    val ts = ZonedDateTime.parse(fields[2])
    val date = ts.truncatedTo(ChronoUnit.DAYS)
    val hit = Hit(fields[0], fields[1], ts, date)
    return hit
}

fun calcMetrics(hitList: List<Hit>) {
    // produce by-day rankings of:
    // - pages by number of users
    // - users by unique page views
    val groupHitsByDate = hitList.groupBy { it -> it.date }
    val hitCountMap = hitList.groupingBy { it.user }.eachCount()
    val dateKeys = groupHitsByDate.keys
    for (key in dateKeys) {
        println("Rankings for $key")
        val userByViews = hitList
            .filter { it.date == key }
            .groupingBy { it.user }.eachCount()
        val pagesByUsers = hitList
            .filter { it.date == key }
            .groupingBy { it.path }.eachCount()
        println(userByViews)
        println(pagesByUsers)
    }
}

fun main(args: Array<String>) {
    val logfile = "log.csv"
    val lines = openLog(logfile)
    val hits = lines.map { lineParse(it) }
    // println(hits)
    calcMetrics(hits)
}
