# Project Status
The "users by unique page views" metric is now correct and displays unique views as requested.  "Pages by number of users" is also correct and displays total number of hits per page.  This project should now be tested against production or production-like data, as well as stress-tested against future load expectactions.  All potential issues or errors uncovered should have a test written and automated into the build process.

# File list:
- buildpr.sh:     bash script to check code syntax and compile binary
- PageRank.jar    compiled binary
- PageRank.kt     source code
- readme.md       documentation (this file)
- log.csv         original (provided) HTTP log
- log_test.csv    log with dummy values for testing - rename to log.csv to test

# STATED ASSUMPTIONS 
- I'm simply printing my output.  In a production run, I would either consume the results with another script or add a database connection directly.
- File size is not a significant fraction of available memory.  I would likely split the logs if this was a production issue.

# Project Definition
"Treat it as production level code, upload to Github when complete, include a brief README, and ping me.

Please complete the below problem for an arbitrary production HTTP server's log.  The only restriction is that you use Kotlin as your language of choice.

Given an [HTTP server access log]
(i.e. https://gist.github.com/zvozin/88fe5e242118efba48b66c43806da6ef), 
produce by-day rankings of:
- pages by number of users
- users by unique page views


Do think through the performance/maintainability implications of the choices you're making."

# PROJECT PLAN
1.  DONE pull the file
2.  DONE get the file open with kotlin
3.  DONE parse the string
4.  DONE create data class for hits
5.  DONE produce output metrics
6.  DONE create dummy log for testing
7.  DONE write error checks
8.  DONE Document code using KDoc syntax
9.  DONE Automate syntax checks
10. DONE Automate build
11. DONE Finalize deliverables
12. DONE Upload to Github
13. DONE Deliver NLT 17:00 01-07-2019 Monday

