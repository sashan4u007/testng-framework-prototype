# "testng-framework-prototype"

Currently supports chrome and firefox

Run Automation in chrome + headed : mvn clean test
Run Automation in chrome + headless : mvn clean test -Dheadless=true
Run Automation in firefox + headed : mvn clean test -Dbrowser=firefox
Run Automation in chrome + headless : mvn clean test -Dheadless=true -Dbrowser=firefox

Run Automation on different url in chrome : mvn clean test -Durl="https://www.google.com/" ( url is hardcoded in pom.xml, we can add environment instead of url such as test, stage, pre-production. And there should be mapping available in automation to fetch particular environment URL)

