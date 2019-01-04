code test for spruce holdings

"Treat it as production level code, upload to Github when complete, include a brief README, and ping me.

Please complete the below problem for an arbitrary production HTTP server's log.  The only restriction is that you use Kotlin as your language of choice.

Given an [HTTP server access log]
(i.e. https://gist.github.com/zvozin/88fe5e242118efba48b66c43806da6ef), 
produce by-day rankings of:
- pages by number of users
- users by unique page views

Do think through the performance/maintainability implications of the choices you're making."

**** GAME PLAN ****
1. DONE pull the file
2. DONE get the file open with kotlin
3. parse the string
4. create dictionary with date as key
4a. create dictionary with page as key
4b. create dictionary with userid as key
5. create dummy log for testing
6. write error checks
6a. total line count should match pages by num users sum
7. Document code using KDoc syntax

**** ASSUMPTIONS TO STATE ****
1. The dates in the log are sequential

