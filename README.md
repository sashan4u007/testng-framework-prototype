# "testng-framework-prototype"

Currently supports chrome and firefox

1. Run Automation in chrome + headed : mvn clean test

2. Run Automation in chrome + headless : mvn clean test -Dheadless=true

3. Run Automation in firefox + headed : mvn clean test -Dbrowser=firefox

4. Run Automation in chrome + headless : mvn clean test -Dheadless=true -Dbrowser=firefox

5. Run Automation on different url in chrome : mvn clean test -Durl="https://www.google.com/" ( url is hardcoded in pom.xml, we can add environment instead of url such as test, stage, pre-production. And there should be mapping available in automation to fetch particular environment URL)

