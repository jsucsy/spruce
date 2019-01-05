Help:   kotlinc -help


Compile - runnable: kotlinc PageRank.kt -include-runtime -d PageRank.jar
Compile - library: kotlinc hello.kt -d hello.jar


Run: java -jar hello.jar

REPL: kotlinc-jvm

use ktlint to check code
# check the style of all Kotlin files inside the current dir (recursively)
# (hidden folders will be skipped)
$ ktlint --color
  src/main/kotlin/Main.kt:10:10: Unused import
  
# check only certain locations (prepend ! to negate the pattern) 
$ ktlint "src/**/*.kt" "!src/**/*Test.kt"

# auto-correct style violations
# (if some errors cannot be fixed automatically they will be printed to stderr) 
$ ktlint -F "src/**/*.kt"

# print style violations grouped by file
$ ktlint --reporter=plain?group_by_file
# print style violations as usual + create report in checkstyle format 
$ ktlint --reporter=plain --reporter=checkstyle,output=ktlint-report-in-checkstyle-format.xml

# install git hook to automatically check files for style violations on commit
# use --install-git-pre-push-hook if you wish to run ktlint on push instead
$ ktlint --install-git-pre-commit-hook
