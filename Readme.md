
# Test Automation Framework

A robust, scalable, and Selenium Java Automation Framework built using Java 17, TestNG, Page Object Model, and Maven.
This framework supports parallel execution, data-driven testing, LambdaTest cloud execution, headless mode, runtime fake data generation, and rich reporting with Extent Reports + Log4j logs.


## Authors

- [@niyazhashmi1105](https://github.com/niyazhashmi1105/seleniumJavaAutomationFramework)

- EmailAddress: niyazhashmi161921@gmail.com

## 🚀 About Me
Hi, My name is MD. Niyaz Hashmi and have 10 years of experience in Automation Testing using Selenium WebDriver, Playwright and Rest Assured

My major expertise are in Java, Javascript and Typescript Programming Langugages.
## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/niyazhashmi1105/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/md-niyaz-hashmi-4327b614/)

## Prerequisites
Before running the framework, ensure the following are installed:
- **Java 17** or higher
- **Apache Maven 3.6+** 
- An IDE (**IntelliJ / Eclipse / VS Code**)
- (Optional) **LambdaTest account + credentials** in environment variables:
- Download Link: (https://maven.apache.org/download.cgi)

## Features

- ✅ **Selenium WebDriver (Java 17)**

        Supports Chrome, Edge, Firefox, and Safari (non-headless).

- ✅ **TestNG Framework**

        Parallel execution

        Data-driven testing

        Test lifecycle management

        Listeners for reporting + logging

- ✅ **Cloud Execution on LambdaTest**

        Run tests on cloud browsers using:

        -DisLambdaTest=true

- ✅ **Headless Mode Support**

        Fast execution locally or on CI:

        -DisHeadLess=true

- ✅ **Data-Driven Testing**

        Uses:

        OpenCSV for CSV test data

        Apache POI for Excel

        GSON for JSON

- ✅ **Java Faker for real-time fake data**

- ✅ **Custom Reporting with Extent Reports**

        HTML report → report.html

        Screenshots attached for failures

        Parallel test support (ThreadLocal)

- ✅ **Log4j Logging**

        Logs generated under logs/automation.log

        Console + file logging enable


## Tech Stack

| Component       | Technology                |
| --------------- | ------------------------- |
| Language        | Java 17                   |
| Test Runner     | TestNG                    |
| Build Tool      | Maven                     |
| Cloud Execution | LambdaTest                |
| Reporting       | Extent Reports            |
| Logging         | Log4j2                    |
| Data Providers  | OpenCSV, GSON, Apache POI |
| Fake Data       | Java Faker                |



## How to Run Tests

To run tests, run the following command

## 1. Clone the Repository
        git clone https://github.com/<your-repo>/seleniumJavaAutomationFramework.git
        cd seleniumJavaAutomationFramework

## 2. Run Tests Locally
        Run using Chrome locally (headed):
        mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadLess=false

        Run in headless mode:
        mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadLess=true

        Run parallel tests (TestNG manages automatically):
        mvn test -Dbrowser=chrome

## ☁️ 3. Run Tests on LambdaTest

        Make sure your LambdaTest credentials are configured:
        export LT_USERNAME="your_username"
        export LT_ACCESS_KEY="your_access_key"

## 4. Run tests:

        mvn test -Dbrowser=chrome -DisLambdaTest=true -DisHeadLess=false
        LambdaTest dashboard will show all executed sessions.

        📊 Reports & Logs
        📄 HTML Report (Extent Reports)

        After execution, the framework generates:
        report.html  →  located at project root

        Includes:
        Steps logged
        Screenshots for failures
        Parallel test execution logs

## 5. 📝 Log Files (Log4j2)

        Logs are stored under:
        logs/automation.log
        Log levels: INFO, DEBUG, ERROR
        Used for debugging failures and execution flow.

## 6. 📁 Test Data Handling

        You can add test data inside:
        src/test/resources/testdata/

## 7. The framework supports data driven testing using:
        CSV reading using OpenCSV
        JSON parsing using GSON
        Excel (.xlsx) using Apache POI

## 8. Runtime data generation using Java Faker
## 9. Command-Line Parameters (Maven Surefire)

## 10. The framework uses 3 runtime parameters:
        Parameter	        Values	                
        browser	            chrome, edge, firefox, safari	
        isLambdaTest	    true / false
        isHeadLess	        true / false	
        Example:
            mvn test -Dbrowser=edge -DisLambdaTest=true -DisHeadLess=true

## 11. Github Actions Integration
       This framework is integrated with github actionss. 
       The tests are executed at 11:30 PM IST daily job.The reports are archived at gh-pages branch

