# APIDEMO


Demonstration api tests project.
Technologies used: Java, Gradle, Junit 5, Allure Reporting, Github Pages. Concurrent tests execution supported.

## Local execution prerequisites

- Java 21 installed and configured
- '**java -version**' terminal command returns correct 21 version
- Allure installed locally
- '**allure -version**' terminal command returns result

## Local execution
- project downloaded via command '**git clone https://github.com/garixx/apidemo.git**' or any other way
- run '**./gradlew test**' terminal command at project root for single-threaded execution
- run '**./gradlew parallel**' terminal command at project root for concurrent execution

## Local Allure report
- run '**allure serve build/allure-results**' terminal command at project root
- open web browser by link provided

## Github Actions Allure report
- after any commit wait while Actions '**test**' and '**pages build and deployment**' tasks executed
- go to https://garixx.github.io/apidemo/ GitHub Actions page
  
